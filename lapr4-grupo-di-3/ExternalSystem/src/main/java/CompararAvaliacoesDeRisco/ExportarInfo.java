/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CompararAvaliacoesDeRisco;

import DTO.ComparacaoArDTO;
import static ExportarInfoPedido.ExportarInfo.result;
import Utils.ValidateXML;
import java.io.StringWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.json.simple.JSONObject;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author hugov
 */
public class ExportarInfo {

    static String exportarInfoXML(ComparacaoArDTO dto) throws SAXException {
        try {

            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            // root element
            Element root = document.createElement("ComparacaoAR");
            document.appendChild(root);
            
            Element pedido = document.createElement("Pedido");
            
            Attr attrp = document.createAttribute("idPedido");
            attrp.setValue(Long.toString(dto.getIdPedido()));
            pedido.setAttributeNode(attrp);
            root.appendChild(pedido);

            Element local = document.createElement("Local");
            local.appendChild(document.createTextNode(dto.getLocal()));
            pedido.appendChild(local);
            
            Element listaCob = document.createElement("ListaCoberturas");
            pedido.appendChild(listaCob);

            for (String c : dto.getCoberturas()) {

                Element cob = document.createElement("Cobertura");
                cob.appendChild(document.createTextNode(c));
                listaCob.appendChild(cob);

            }
            
            //matrizRisco element
            Element matriz = document.createElement("MatrizRisco");
            
            root.appendChild(matriz);

            // set an attribute to staff element
            Attr attr = document.createAttribute("idMatriz");
            attr.setValue(Long.toString(dto.getIdMatriz1()));
            matriz.setAttributeNode(attr);

            Element scoreMax = document.createElement("ScoreMax");
            scoreMax.appendChild(document.createTextNode(Integer.toString(dto.getScoreM1())));
            matriz.appendChild(scoreMax);

            Element scoreObtido = document.createElement("ScoreObtido");
            scoreObtido.appendChild(document.createTextNode(Integer.toString(dto.getScoreO1())));
            matriz.appendChild(scoreObtido);

            Element indAR = document.createElement("indiceAvaliacaoRisco");
            indAR.appendChild(document.createTextNode(Float.toString(dto.getIndiceAvaliacao1())));
            matriz.appendChild(indAR);

            // matriz 2
            Element matriz2 = document.createElement("MatrizRisco");
            root.appendChild(matriz2);
            
            Attr attr2 = document.createAttribute("idMatriz");
            attr2.setValue(Long.toString(dto.getIdMatriz2()));
            matriz2.setAttributeNode(attr2);
            
            Element scoreMax2 = document.createElement("ScoreMax");
            scoreMax2.appendChild(document.createTextNode(Integer.toString(dto.getScoreM2())));
            matriz2.appendChild(scoreMax2);

            Element scoreObtido2 = document.createElement("ScoreObtido");
            scoreObtido2.appendChild(document.createTextNode(Integer.toString(dto.getScoreO2())));
            matriz2.appendChild(scoreObtido2);

            Element indAR2 = document.createElement("indiceAvaliacaoRisco");
            indAR2.appendChild(document.createTextNode(Float.toString(dto.getIndiceAvaliacao2())));
            matriz2.appendChild(indAR2);

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

            boolean validacao = ValidateXML.validate(result, "BuscarComparacao.xsd");

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

    static String exportarInfoJSON(ComparacaoArDTO dto) {
        
        int cnt = 1;
        
        JSONObject sampleObject = new JSONObject();
        sampleObject.put("Id Pedido", dto.getIdPedido());
        sampleObject.put("Local do pedido", dto.getLocal());
        for (String c : dto.getCoberturas()) {
            sampleObject.put("Cobertura" + cnt, c);
            cnt++;
        }
        sampleObject.put("Id Matriz 1", dto.getIdMatriz1());
        sampleObject.put("Score obtido da matriz 1", dto.getScoreO1());
        sampleObject.put("Score maximo da Matriz 1", dto.getScoreM1());
        sampleObject.put("Indice de avaliacao de risco da Matriz 1", dto.getIndiceAvaliacao1());
        sampleObject.put("Id Matriz 2", dto.getIdMatriz2());
        sampleObject.put("Score obtido da matriz 2", dto.getScoreO2());
        sampleObject.put("Score maximo da Matriz 2", dto.getScoreM2());
        sampleObject.put("Indice de avaliacao de risco da Matriz 2", dto.getIndiceAvaliacao2());

        result = sampleObject.toJSONString();

        return result;
    }

}
