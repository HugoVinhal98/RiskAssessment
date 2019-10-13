/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import CompararAvaliacoesDeRisco.CompararARController;
import DTO.ImprimirResultadoDTO;
import ExportarInfoPedido.ExportarInfoPedidoController;
import ImportarMultiplosPedidos.ImportarMultiplosPedidosController;
import ImportarPedido.ImportarPedidoController;
import PedidosConcluidosController.PedidosConcluidosController;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
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

/**
 *
 * @author GilTrindade
 */
public class HTTPRequestMultiplos implements Runnable{


    private Socket s;
    private DataOutputStream sOut;
    private DataInputStream sIn;
    private String baseFolder;

    public HTTPRequestMultiplos(Socket cli_s, String folder) {
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

        
        
        if (request.startsWith("POST /submeterMutiplosPedidos")) {
            try {
                processPostSubmeterMultiplosPedidos();
            } catch (IOException | SAXException | ParserConfigurationException | ParseException ex) {
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

    
      void processPostSubmeterMultiplosPedidos() throws IOException, SAXException, ParserConfigurationException, ParseException {

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

        ImportarMultiplosPedidosController ipc = new ImportarMultiplosPedidosController();

        if (contentType.equalsIgnoreCase("text/xml")) {

            String pedidoSubmeter2 = pedidoSubmeter.replaceAll("\\s+", "");
            pedidoSubmeterTratado = pedidoSubmeter2.trim();
            status = ipc.importarMultiplosPedidoController(pedidoSubmeterTratado, contentType);
            content = fillContentBasedOnStatus(status);

        } else if (contentType.equalsIgnoreCase("application/json")) {

            pedidoSubmeterTratado = pedidoSubmeter.trim();
            status = ipc.importarMultiplosPedidoController(pedidoSubmeterTratado, contentType);
            content = fillContentBasedOnStatus(status);

        } else {
            status = "404";
            content = fillContentBasedOnStatus(status);
        }

        HTTP.sendHttpStringResponse(sOut, status, contentType, content);

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


