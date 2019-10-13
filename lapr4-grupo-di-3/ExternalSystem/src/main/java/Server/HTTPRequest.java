package Server;

import CompararAvaliacoesDeRisco.CompararARController;
import ExportarInfoPedido.ExportarInfoPedidoController;
import DTO.ImprimirResultadoDTO;
import ImportarMultiplosPedidos.ImportarMultiplosPedidosController;
import ImportarPedido.ImportarPedidoController;
import PedidosConcluidosController.PedidosConcluidosController;
import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

public class HTTPRequest implements Runnable {

    private Socket s;
    private DataOutputStream sOut;
    private DataInputStream sIn;
    private String baseFolder;

    public HTTPRequest(Socket cli_s, String folder) {
        s = cli_s;
        baseFolder = folder;
    }

    @Override
    public void run() {

        try {
            sOut = new DataOutputStream(s.getOutputStream());
            sIn = new DataInputStream(s.getInputStream());
        } catch (IOException ex) {
            System.out.println("Data Stream IOException");
        }

        String request = HTTP.readLineCRLF(sIn);
        System.out.println("Request: " + request);

        if (request.startsWith("GET /resultadopedido")) {
            try {
                processGetResultadoPedido(request);
            } catch (SAXException ex) {
                Logger.getLogger(HTTPRequest.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (request.startsWith("POST /submeterpedido")) {
            try {
                processPostSubmeterPedido();
            } catch (IOException | SAXException | ParserConfigurationException | ParseException ex) {
                Logger.getLogger(HTTPRequest.class.getName()).log(Level.SEVERE, null, ex);
            }
       
        } else if (request.startsWith("GET /comparacaoMatrizes")) {
            try {
                processGetComparacaoMatrizes(request);
            } catch (SAXException ex) {
                Logger.getLogger(HTTPRequest.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (request.startsWith("GET /resultadoPedConcluidosData")) {
            try {
                processGetResultadoPedConcluidosData(request);
            } catch (java.text.ParseException ex) {
                Logger.getLogger(HTTPRequest.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                Logger.getLogger(HTTPRequest.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (request.startsWith("GET /resultadoPedConcluidosLocal")) {
            try {
                processGetResultadoPedConcluidosLocal(request);
            } catch (SAXException ex) {
                Logger.getLogger(HTTPRequest.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (request.startsWith("GET /resultadoPedConcluidosAmbos")) {
            try {
                try {
                    processGetResultadoPedConcluidosAmbos(request);
                } catch (SAXException ex) {
                    Logger.getLogger(HTTPRequest.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (java.text.ParseException ex) {
                Logger.getLogger(HTTPRequest.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            processGet(request);
        }

        try {
            s.close();
        } catch (IOException ex) {
            System.out.println("Close IOException");
        }

    }

    void processGet(String req) {
        String line, fileName, filePath;

        do { // Read and ignore the header lines
            line = HTTP.readLineCRLF(sIn);
        } while (line.length() > 0);

        fileName = req.split(" ")[1];
        if (fileName.compareTo("/") == 0) {
            fileName = "/index.html";
        }
        filePath = baseFolder + fileName;
        HTTP.sendHttpFileResponse(sOut, null, filePath);
    }

    void processPostSubmeterPedido() throws IOException, SAXException, ParserConfigurationException, ParseException {

        String line, content = null, status = null, contentType = null, pedidoSubmeterTratado = null;
        StringBuilder sb = new StringBuilder();
        int done, readNow, len = 0;
        byte[] data = new byte[300];

        do {
            line = HTTP.readLineCRLF(sIn);
            if (line.startsWith("content-length:")) {
                len = Integer.parseInt(line.split(" ")[1]);
            }
            if (line.startsWith("Content-Type:")) {
                contentType = line.split(" ")[1];
            }
        } while (line.length() > 0);

        if (len == 0) {
            replyPostError("Content-Length: expected and not found");
            return;
        }

        try {
            do {
                if (len > 300) {
                    readNow = 300;
                } else {
                    readNow = len;
                }
                done = sIn.read(data, 0, readNow);
                String x = new String(data);
                sb.append(x);
                len = len - done;
                Arrays.fill(data, (byte) 0);
            } while (len > 0);
        } catch (IOException ex) {
            System.out.println("IOException");
        }

        String pedidoSubmeter = sb.toString();

        ImportarPedidoController ipc = new ImportarPedidoController();

        if (contentType.equalsIgnoreCase("text/xml")) {

            String pedidoSubmeter2 = pedidoSubmeter.replaceAll("\\s+", "");
            pedidoSubmeterTratado = pedidoSubmeter2.trim();
            status = ipc.importarPedidoController(pedidoSubmeterTratado, contentType);
            content = fillContentBasedOnStatus(status);

        } else if (contentType.equalsIgnoreCase("application/json")) {

            pedidoSubmeterTratado = pedidoSubmeter.trim();
            status = ipc.importarPedidoController(pedidoSubmeterTratado, contentType);
            content = fillContentBasedOnStatus(status);

        } else {
            status = "404";
            content = fillContentBasedOnStatus(status);
        }

        HTTP.sendHttpStringResponse(sOut, status, contentType, content);

    }
     

    /**
     * Processa request do tipo Get
     *
     * @param req
     */
    void processGetResultadoPedido(String req) throws SAXException {

        String line, content = null, status = null, contentType = null;

        do { // Read and ignore the header lines
            line = HTTP.readLineCRLF(sIn);
        } while (line.length() > 0);

        String indexRequest = req.split(" ")[1];
        contentType = indexRequest.split("/")[2];
        String pedido = indexRequest.split("/")[3];

        ExportarInfoPedidoController controller = new ExportarInfoPedidoController();

        ImprimirResultadoDTO irDTO = controller.exportarInfoPedido(pedido, contentType);
        status = irDTO.getStatus();
        content = irDTO.getContent();
        HTTP.sendHttpStringResponse(sOut, status, contentType, content);

    }

    /**
     * Processa request do tipo Get
     *
     * @param req
     */
    void processGetComparacaoMatrizes(String req) throws SAXException {

        String line, content = null, status = null, contentType = null;

        do { // Read and ignore the header lines
            line = HTTP.readLineCRLF(sIn);
        } while (line.length() > 0);

        String indexRequest = req.split(" ")[1];
        contentType = indexRequest.split("/")[2];
        String idMatriz1 = indexRequest.split("/")[3];
        String idMatriz2 = indexRequest.split("/")[4];
        String idPedido = indexRequest.split("/")[5];

        CompararARController controller = new CompararARController();

        ImprimirResultadoDTO irDTO = controller.exportarInfoComparacao(Long.parseLong(idMatriz1), Long.parseLong(idMatriz2), Long.parseLong(idPedido), contentType);

        status = irDTO.getStatus();
        content = irDTO.getContent();
        HTTP.sendHttpStringResponse(sOut, status, contentType, content);

    }

    /**
     * Processa request do tipo Get entre um determinado periodo
     *
     * @param request
     */
    private void processGetResultadoPedConcluidosData(String req) throws java.text.ParseException, SAXException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String line, content = null, status = null, contentType = null;

        do { // Read and ignore the header lines
            line = HTTP.readLineCRLF(sIn);
        } while (line.length() > 0);

        String indexRequest = req.split(" ")[1];
        contentType = indexRequest.split("/")[2];
        String dataInicio = indexRequest.split("/")[3];
        String dataFinal = indexRequest.split("/")[4];

        Date dataIni = sdf.parse(dataInicio);
        Date dataFim = sdf.parse(dataFinal);

        PedidosConcluidosController controller = new PedidosConcluidosController();

        ImprimirResultadoDTO irDTO = controller.exportarPedConcluidosData(dataIni, dataFim, contentType);

        if (irDTO == null) {
            System.out.println("Não existem pedidos com esses dados!");

        } else {

            status = irDTO.getStatus();
            content = irDTO.getContent();
            HTTP.sendHttpStringResponse(sOut, status, contentType, content);
        }
    }

    /**
     * Processa request do tipo Get pela localidade
     *
     * @param request
     */
    private void processGetResultadoPedConcluidosLocal(String req) throws SAXException {

        String line, content = null, status = null, contentType = null;

        do { // Read and ignore the header lines
            line = HTTP.readLineCRLF(sIn);
        } while (line.length() > 0);

        String indexRequest = req.split(" ")[1];
        contentType = indexRequest.split("/")[2];

        String[] dividido = indexRequest.split("/");
        List<String> locais = new ArrayList<>();
        for (int i = 3; i < dividido.length; i++) {
            dividido[i] = dividido[i].replaceAll("-", " ");
            locais.add(dividido[i]);
        }

        PedidosConcluidosController controller = new PedidosConcluidosController();

        ImprimirResultadoDTO irDTO = controller.exportarPedConcluidosLocal(locais, contentType);

        if (irDTO == null) {
            System.out.println("Não existem pedidos com esses dados!");

        } else {

            status = irDTO.getStatus();
            content = irDTO.getContent();
            HTTP.sendHttpStringResponse(sOut, status, contentType, content);

        }
    }

    /**
     * Processa request do tipo Get pela localidade e data
     *
     * @param request
     */
    private void processGetResultadoPedConcluidosAmbos(String req) throws java.text.ParseException, SAXException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String line, content = null, status = null, contentType = null;

        do { // Read and ignore the header lines
            line = HTTP.readLineCRLF(sIn);
        } while (line.length() > 0);

        String indexRequest = req.split(" ")[1];
        contentType = indexRequest.split("/")[2];

        String[] dividido = indexRequest.split("/");
        contentType = dividido[2];

        String dataInicio = dividido[3];
        String dataFinal = dividido[4];

        Date dataIni = sdf.parse(dataInicio);
        Date dataFim = sdf.parse(dataFinal);

        List<String> locais = new ArrayList<>();
        for (int i = 5; i < dividido.length; i++) {
            dividido[i] = dividido[i].replaceAll("-", " ");
            locais.add(dividido[i]);
        }

        PedidosConcluidosController controller = new PedidosConcluidosController();

        ImprimirResultadoDTO irDTO = controller.exportarPedConcluidosAmbos(dataIni, dataFim, locais, contentType);

        if (irDTO == null) {
            System.out.println("Não existem pedidos com esses dados!");
        } else {

            status = irDTO.getStatus();
            content = irDTO.getContent();
            HTTP.sendHttpStringResponse(sOut, status, contentType, content);
        }
    }

    /**
     *
     */
    void processPostUpload() {

        String line, boundary, filename, filePath;
        int done, readNow, len;
        String cDisp = "Content-Disposition: form-data; name=\"filename\"; filename=\"";
        File f;
        FileOutputStream fOut;
        byte[] data = new byte[300];

        len = 0;
        boundary = null;
        do { // First Header
            line = HTTP.readLineCRLF(sIn);
            if (line.startsWith("Content-Length: ")) {
                len = Integer.parseInt(line.split(" ")[1]);
            } else if (line.startsWith("Content-Type: multipart/form-data; boundary=")) {
                boundary = line.split("=")[1];
            }
        } while (line.length() > 0);

        if (len == 0) {
            replyPostError("Content-Length: expected and not found");
            return;
        }
        if (boundary == null) {
            replyPostError("Content-Type: multipart/form-data; expected and not found");
            return;
        }

        line = HTTP.readLineCRLF(sIn);
        if (!line.endsWith(boundary)) {
            replyPostError("Multipart separator expected and not found");
            return;
        }
        len = len - line.length() - 2;
        filename = "";

        do { // Second Header
            line = HTTP.readLineCRLF(sIn);
            len = len - line.length() - 2;
            if (line.startsWith(cDisp)) {
                filename = line.split("=")[2];
                filename = filename.substring(1, filename.length() - 1);
            }
        } while (line.length() > 0);

        try {
            if (filename.length() == 0) {
                do {
                    done = sIn.read(data, 0, 300);
                    len = len - done;
                } while (len > 0);
                replyPostError("Content-Disposition: form-data; expected and not found (NO FILENAME)");
                return;
            }
            filePath = baseFolder + "/" + filename;
            f = new File(filePath);
            fOut = new FileOutputStream(f);

            len = len - boundary.length() - 6;

            do {
                if (len > 300) {
                    readNow = 300;
                } else {
                    readNow = len;
                }
                done = sIn.read(data, 0, readNow);
                fOut.write(data, 0, done);
                len = len - done;
            } while (len > 0);
            fOut.close();
            line = HTTP.readLineCRLF(sIn);
        } catch (IOException ex) {
            System.out.println("IOException");
        }
        replyPostList();
    }

    void processPostList() {
        String line;
        do { // Read and ignore the header lines
            line = HTTP.readLineCRLF(sIn);
        } while (line.length() > 0);
        replyPostList();
    }

    void replyPostList() {
        String s1 = "<html><head><title>File List</title></head><body><h1>File List:</h1><big><ul>";
        String s2 = "</ul></big><hr><p><a href=/>BACK</a></body></html>";
        String list;
        File d;

        d = new File("www/");
        list = s1;
        File[] filesList = d.listFiles();
        for (File f : filesList) {
            if (f.isFile()) {
                list = list + "<li><a href=/" + f.getName() + ">" + f.getName() + "</a>";
            }
        }
        list = list + s2;
        HTTP.sendHttpStringResponse(sOut, "200 Ok", "text/html", list);
    }

    void replyPostError(String error) {
        HTTP.sendHttpStringResponse(sOut, "500 Internal Server Error", "text/html",
                "<html><head><title>Server Error</title></head><body><center><img src=500.png><br>(500.png)</center>"
                + "<h1>Server error on POST</h1><p>ERROR: " + error
                + "<hr><p><a href=/>BACK</a></body></html>");
    }

    String fillContentBasedOnStatus(String status) {

        String content = "Codigo de estado HTTP desconhecido.";

        if (status.equalsIgnoreCase("201")) {
            content = "O pedido foi submetido e importado com sucesso!";
        }

        if (status.equalsIgnoreCase("400")) {
            content = "A informacao submetida nao e válida devido a sintaxe incorreta. Reverifique o ficheiro a enviar.";
        }

        if (status.equalsIgnoreCase("404")) {
            content = "O recurso requisitado nao foi encontrado.";
        }

        if (status.equalsIgnoreCase("400")) {
            content = "O pedido nao pode ser entregue devido a sintaxe incorreta.";
        }

        return content;

    }

}
