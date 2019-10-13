/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImportarMultiplosPedidos;

import DTO.ImportarPedidoDTO;
import static ImportarPedido.InterpretarFicheiro.getCharacterDataFromElement;
import Utils.ValidateXML;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author GilTrindade
 */
public class InterpretarMultiplosPedidos {
    
    /**
     *Método para ler um xml que contém a informação de um pedido.
     * @param pedidoSubmeter
     * @return
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public static ArrayList<ImportarPedidoDTO> lerPedidoDeXML(String pedidoSubmeter) throws SAXException, IOException, ParserConfigurationException {

        ArrayList<ImportarPedidoDTO> dtoList = new ArrayList<>();
        
        boolean validacao = ValidateXML.validate(pedidoSubmeter, "ImportarMultiplosPedidos.xsd");
        
        if (!validacao) {
            return null;
        }

        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource is = new InputSource();

        is.setCharacterStream(new StringReader(pedidoSubmeter));
        Document doc = db.parse(is);
        NodeList nodes = doc.getElementsByTagName("Pedido");
        for(int i = 0 ; i < nodes.getLength(); i++){
            
        
        Element element3 = (Element) nodes.item(i);
        NodeList objetoSeguro = element3.getElementsByTagName("ObjetoSeguro");

        for (int e = 0; e < objetoSeguro.getLength(); e++) {

            Element element = (Element) objetoSeguro.item(e);

            NodeList nome = element.getElementsByTagName("Nome");
            Element line = (Element) nome.item(0);
            String nomeDef = getCharacterDataFromElement(line);
            
            NodeList necessidadeAnalista = element.getElementsByTagName("NecessidadeAnalista");
            Element line4 = (Element) necessidadeAnalista.item(0);
            String necessidadeAnalistaDef = getCharacterDataFromElement(line4);

            NodeList listaCoberturas = element.getElementsByTagName("ListaCoberturas");

            Element line2 = (Element) listaCoberturas.item(0);

            NodeList coberturas = line2.getElementsByTagName("Cobertura");

            ArrayList<String> lCoberturas = new ArrayList<>();

            for (int k = 0; k < coberturas.getLength(); k++) {

                NodeList cobertura = element.getElementsByTagName("Cobertura");
                Element line3 = (Element) cobertura.item(k);
                lCoberturas.add(getCharacterDataFromElement(line3));

            }

            ImportarPedidoDTO dto = new ImportarPedidoDTO(nomeDef, lCoberturas, necessidadeAnalistaDef);

            dtoList.add(dto);

        }
        }
        

        return dtoList;

    }
    
   
    public static String getCharacterDataFromElement(Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return "";
    }

    /**
     *Método para ler um json que contém a informação de um pedido.
     * @param pedidoSubmeter
     * @return
     * @throws ParseException
     */
    public static ArrayList<ImportarPedidoDTO> lerPedidoDeJSON(String pedidoSubmeter) throws ParseException {

     ArrayList<ImportarPedidoDTO> dtoList = new ArrayList<>();

        Object obj = new JSONParser().parse(pedidoSubmeter);

        JSONObject jo = (JSONObject) obj;

        JSONArray array = (JSONArray) jo.get("pedido");
        Iterator<Object> iterator = array.iterator();

        while (iterator.hasNext()) {

            Object it = iterator.next();
            JSONObject data = (JSONObject) it;

            String nome = (String) data.get("nome");
            String necessidadeAnalista = (String) data.get("necessidadeAnalista");

            List<String> lista = (List<String>) data.get("coberturas");

            ArrayList<String> lista2 = new ArrayList<>();

            for (String l : lista) {
                lista2.add(l);
            }

            ImportarPedidoDTO dto = new ImportarPedidoDTO(nome, lista2, necessidadeAnalista);
            dtoList.add(dto);

        }

        return dtoList;

    }

      

    
}
