/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PedidosConcluidosController;

import DTO.BuscarPedidoDTO;
import static ExportarInfoPedido.ExportarInfo.result;
import Utils.ValidateXML;
import java.io.StringWriter;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author Diogo Rolo
 */
public class ExportarPedidosConluidosInfo {

    private static String result;

    /**
     *
     * Exporta info em formato JSON
     *
     * @param p DTO com todos os dados relativos a um pedido
     * @return
     */
    public static String exportarInfoJSON(List<BuscarPedidoDTO> listaConcluidos) {

        int cont = 1;
        JSONArray ja = new JSONArray();
        result = "";
        for (BuscarPedidoDTO p : listaConcluidos) {
            Format formatter = new SimpleDateFormat("yyyy-MM-dd");
            String s = formatter.format(p.getDataPedido());
            JSONObject sampleObject = new JSONObject();
            sampleObject.put("ID Pedido", p.getIdPedido());
            sampleObject.put("ID Objeto Seguro Relacionado", p.getIdObjetoSeguro());
            sampleObject.put("Nome Objeto Seguro Relacionado", p.getNomeOS());
            sampleObject.put("Localizacao Objeto Seguro", p.getLocalizacao());
            sampleObject.put("Data do Pedido", s);
            for (String c : p.getListaCoberturasOS()) {
                sampleObject.put("Cobertura" + cont, c);
                cont++;
            }
            sampleObject.put("ID Avaliacao Risco", p.getIdAvaliacaoRisco());
            sampleObject.put("Score Obtido", p.getSo().getScoreObtido());
            sampleObject.put("Score Maximo", p.getSm().getScoreMaximo());
            sampleObject.put("Indice Avaliacao de Risco", p.getIndAR().getIndiceAvalicaoRisco());
            cont = 0;
            ja.add(sampleObject);
        }

        result = ja.toJSONString();

        return result;
    }

    /**
     * Exporta info em formato XML
     *
     * @param listaConcluidos
     * @return
     */
    public static String exportarInfoXML(List<BuscarPedidoDTO> listaConcluidos) throws SAXException {
        try {

            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();

            // root element
            Element root = document.createElement("Pedidos");
            document.appendChild(root);

            for (BuscarPedidoDTO p : listaConcluidos) {
                //pedido element
                Element pedido = document.createElement("Pedido");

                root.appendChild(pedido);

                // set an attribute to staff element
                Attr attr = document.createAttribute("idPedido");
                attr.setValue(Long.toString(p.getIdPedido()));
                pedido.setAttributeNode(attr);

                String data = DateFormatUtils.format(p.getDataPedido(), "yyyy-MM-dd");
                Element dataPedido = document.createElement("DataPedido");
                dataPedido.appendChild(document.createTextNode(data));
                pedido.appendChild(dataPedido);

                // avaliacaoRisco element
                Element avaliacaoRisco = document.createElement("AvaliacaoRisco");
                // avaliacaoRisco.appendChild(document.createTextNode(AvaliacaoRisco));
                pedido.appendChild(avaliacaoRisco);

                Attr attr2 = document.createAttribute("idAvaliacaoRisco");
                attr2.setValue(Long.toString(p.getIdAvaliacaoRisco()));
                avaliacaoRisco.setAttributeNode(attr2);

                Element scoreMax = document.createElement("ScoreMax");
                scoreMax.appendChild(document.createTextNode(Integer.toString(p.getSm().getScoreMaximo())));
                avaliacaoRisco.appendChild(scoreMax);

                Element scoreObtido = document.createElement("ScoreObtido");
                scoreObtido.appendChild(document.createTextNode(Integer.toString(p.getSo().getScoreObtido())));
                avaliacaoRisco.appendChild(scoreObtido);

                Element indAR = document.createElement("indiceAvaliacaoRisco");
                indAR.appendChild(document.createTextNode(Float.toString(p.getIndAR().getIndiceAvalicaoRisco())));
                avaliacaoRisco.appendChild(indAR);

                // objeto seguro element
                Element os = document.createElement("ObjetoSeguro");
                //os.appendChild(document.createTextNode(ObjetoSeguro));
                pedido.appendChild(os);

                Attr attr3 = document.createAttribute("idObjetoSeguro");
                attr3.setValue(Long.toString(p.getIdObjetoSeguro()));
                os.setAttributeNode(attr3);

                Element nomeOS = document.createElement("NomeObjetoSeguro");
                nomeOS.appendChild(document.createTextNode(p.getNomeOS()));
                os.appendChild(nomeOS);

                Element localizacao = document.createElement("Localizacao");
                localizacao.appendChild(document.createTextNode(p.getLocalizacao()));
                os.appendChild(localizacao);

                Element listaCob = document.createElement("ListaCoberturas");
                os.appendChild(listaCob);

                for (String c : p.getListaCoberturasOS()) {

                    Element cob = document.createElement("Cobertura");
                    cob.appendChild(document.createTextNode(c));
                    listaCob.appendChild(cob);

                }

            }
            // create the xml file
            //transform the DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StringWriter sr = new StringWriter();
            StreamResult streamResult = new StreamResult(sr);

            transformer.transform(domSource, streamResult);

            String XMLStringFinal = sr.getBuffer().toString();
            result = XMLStringFinal;

            boolean validacao = ValidateXML.validate(result, "BuscarPedidosConcluidos.xsd");

            if (!validacao) {
                result = "XML invalido";
            }

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
        return result;
    }
}
