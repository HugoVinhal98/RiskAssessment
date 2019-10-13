/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Dominio.AvaliacaoRisco;
import Dominio.CoberturaOS;
import Dominio.EnderecoPostal;
import Dominio.ObjetoSeguro;
import Dominio.Pedido;
import Persistencia.ObjetoSeguroRepositorio;
import Persistencia.ObjetoSeguroRepositorioJPAImpl;
import Persistencia.PedidoRepositorio;
import Persistencia.PedidoRepositorioJPAImpl;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Diogo Rolo
 */
public class FicheirosJSON {

    /**
     * Ler objetos seguros a partir de um ficheiro JSON
     *
     * @param nomeFicheiro Nome do ficheiro JSON de onde os objetos seguros
     * serao lidos
     * @throws Exception
     */
    public void lerObjetosSeguroJSON(String nomeFicheiro) throws Exception {

//        JSONParser parser = new JSONParser();
//        Object obj = parser.parse(new FileReader(nomeFicheiro));
//        JSONObject jsonObject = (JSONObject) obj;
        JSONObject jsonObject = (JSONObject) readJsonSimpleDemo(nomeFicheiro);
        ObjetoSeguroRepositorio repo = new ObjetoSeguroRepositorioJPAImpl();

        JSONArray array = (JSONArray) jsonObject.get("listaSeguros");
        Iterator<Object> iterator = array.iterator();

        while (iterator.hasNext()) {
            Object it = iterator.next();
            JSONObject data = (JSONObject) it;

            String nome = (String) data.get("nome");
            String local = (String) data.get("localizacao");
            
            EnderecoPostal ep = new EnderecoPostal();

            List<String> lista = (List<String>) data.get("coberturas");

            ObjetoSeguro os = new ObjetoSeguro(nome, lista, ep);
            repo.add(os);

        }

    }

    public static void exportarAvaliacaoRiscoJSON(Long id, String nomeFicheiro) throws IOException {

        PedidoRepositorio prepo = new PedidoRepositorioJPAImpl();

        Pedido p = prepo.findById(id);

        AvaliacaoRisco ar = p.getAvaliacaoRisco();

        JSONObject sampleObject = new JSONObject();
        sampleObject.put("ID Avaliação de Risco", ar.getId());
        sampleObject.put("Objeto Seguro Relacionado:", ar.getObjetoSeguro().getNomeObjeto());
        sampleObject.put("Score Obtido:", ar.getScoreObtido().getScoreObtido());
        sampleObject.put("Score Maximo:", ar.getScoreMaximo().getScoreMaximo());
        sampleObject.put("Indice Avaliacão de Risco:", ar.getIndiceAvalicaoRisco().getIndiceAvalicaoRisco());

        try (FileWriter file = new FileWriter(nomeFicheiro)) {
            file.write(sampleObject.toJSONString());
            System.out.println("Successfully Copied JSON Object to File...");
        }

    }

    /**
     * Read JSON simple demo
     *
     * @param filename Nome do ficheiro JSON de onde sera feita a leitura
     * @return
     * @throws Exception
     */
    public static Object readJsonSimpleDemo(String filename) throws Exception {
        FileReader reader = new FileReader(filename);
        JSONParser jsonParser = new JSONParser();
        return jsonParser.parse(reader);
    }
}
