/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeoReferenceAPI;

import DTO.DistanceBetweenPlacesDTO;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.util.StringUtils;

/**
 *
 * @author hugov
 */
public class MicrosoftBingServices {

    /**
     *
     */
    public MicrosoftBingServices() {
    }

    /**
     * Metodo que transforma os dados isneridos pelo utilizador numa string em
     * formato json.
     *
     * @param rua
     * @param localidade
     * @param endrecoPostal
     * @return
     */
    public String obterJSON(String rua, String localidade, String endrecoPostal) {
        String dynamicURl = HTTPClient.prepareDynamicURLBing(rua, localidade, endrecoPostal);
        //System.out.println("dynamicURl: " + dynamicURl);
        String result = null;
        if (!StringUtils.isEmpty(dynamicURl)) {
            result = HTTPClient.getResultHttpAsStream(dynamicURl);
            System.out.println(result);
        }
        //System.out.println("JSON file: " + result);
        return result;
    }

    /**
     *
     * @param latitude
     * @param longitude
     * @return
     */
    public String obterJSONElevation(Double latitude, Double longitude) {
        String dynamicURl = HTTPClient.prepareDynamicURLElevationBing(latitude, longitude);
        //System.out.println("dynamicURl: " + dynamicURl);
        String result = null;
        if (!StringUtils.isEmpty(dynamicURl)) {
            result = HTTPClient.getResultHttpAsStream(dynamicURl);
            System.out.println(result);
        }
        //System.out.println("JSON file: " + result);
        return result;
    }

    /**
     *
     * @param latitude
     * @param longitude
     * @param tipo
     * @param raio
     * @return
     */
    public String obterJSONNearbyPlaces(Double latitude, Double longitude, String tipo, Double raio) {
        String dynamicURl = HTTPClient.prepareDynamicURLNearbyPlacesBing(latitude, longitude, tipo, raio);
        //System.out.println("dynamicURl: " + dynamicURl);
        String result = null;
        if (!StringUtils.isEmpty(dynamicURl)) {
            result = HTTPClient.getResultHttpAsStream(dynamicURl);
            System.out.println(result);
        }
        //System.out.println("JSON file: " + result);
        return result;
    }

    /**
     * Obtem o JSON com a informação sobre a distância entre dois locais
     *
     * @param location1 Um dos locais a analisar
     * @param location2 Um dos locais a analisar
     * @param travelType Tipo de viagem
     * @return JSON com as informações desejadas
     * @throws ParseException
     */
    public String obterJSONDistanceBetweenPlaces(String location1, String location2, String travelType) throws ParseException {

        String dynamicURl = HTTPClient.prepareDynamicURLDistanceBetweenPlacesBing(location1, location2, travelType);
        String jsonInfo = null;
        if (!StringUtils.isEmpty(dynamicURl)) {
            jsonInfo = HTTPClient.getResultHttpAsStream(dynamicURl);
        }
        return jsonInfo;
    }

    /**
     * Atraves de uma string json, o metodo percorre a mesma de forma a
     * encontrar as coordenadas GPS relativas aos dados inseridos
     *
     * @param rua
     * @param localidade
     * @param endereco
     * @return
     * @throws ParseException
     */
    public List<Double> getCoordenadasFromJson(String rua, String localidade, String endereco) throws ParseException {

        String jsonText = obterJSON(rua, localidade, endereco);
        if (jsonText == null) {
            return null;
        }

        System.out.println("Json obtido: " + jsonText);
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(jsonText);

        String status = (String) json.get("statusDescription");
        System.out.println(status);

        List<Double> listaCoordenadas = new ArrayList<>();

        if (status.equals("OK")) {
            JSONArray arrayResourceSets = (JSONArray) json.get("resourceSets");

            Iterator<Object> iterator1 = arrayResourceSets.iterator();
            while (iterator1.hasNext()) {
                Object it = iterator1.next();
                JSONObject data = (JSONObject) it;

                Long total = (Long) data.get("estimatedTotal"); // total de localizações encontradas
                //System.out.println(total);
                if (total <= 0) {
                    System.out.println("Localização inválida!");
                    return null;
                } else {
                    JSONObject json2 = (JSONObject) arrayResourceSets.get(0);
                    JSONArray arrayResources = (JSONArray) json2.get("resources");

                    Iterator<Object> iterator2 = arrayResources.iterator();
                    int i = 0;
                    while (iterator2.hasNext()) {
                        Object it2 = iterator2.next();
                        JSONObject data2 = (JSONObject) it2;

                        String name = (String) data2.get("name"); // nome da localização encontrada
                        //System.out.println(name);

                        JSONObject json3 = (JSONObject) arrayResources.get(i);
                        JSONObject points = (JSONObject) json3.get("point");

                        JSONArray coordinates = (JSONArray) points.get("coordinates");

                        Iterator<Object> iterator3 = coordinates.iterator();
                        i++;
                        while (iterator3.hasNext()) {
                            Object it3 = iterator3.next();
                            Double data3 = (Double) it3;

                            //System.out.println(data3);
                            listaCoordenadas.add(data3);
                        }
                    }
                }
            }
        } else {
            System.out.println("Localização inválida!");
            return null;
        }
        return listaCoordenadas;
    }

    /**
     * Obtem a distância entre dois locais e o tempo médio de viagem baseado no
     * tipo de viagem
     *
     * @param location1 Um dos locais a analisar
     * @param location2 Um dos locais a analisar
     * @param travelType Tipo de viagem
     * @return Distância entre os dois locais em metros e
     * @throws ParseException
     */
    public DistanceBetweenPlacesDTO getDistanceBetweenPlacesFromJson(String location1, String location2, String travelType) throws ParseException {

        JSONParser parser = new JSONParser();

        Double distance = null;
        Double duration = null;

        String js = obterJSONDistanceBetweenPlaces(location1, location2, travelType);

        if (js == null) {
            return null;
        }

        JSONObject json = (JSONObject) parser.parse(js);

        JSONArray array = (JSONArray) json.get("resourceSets");

        Iterator<Object> iterator = array.iterator();
        Object it = iterator.next();
        JSONObject data = (JSONObject) it;

        JSONArray array1 = (JSONArray) data.get("resources");

        System.out.println(array1);

        Iterator<Object> iterator1 = array1.iterator();

        Object it1 = iterator1.next();
        JSONObject data1 = (JSONObject) it1;
        JSONArray resultsArray = (JSONArray) data1.get("results");

        Iterator<Object> iterator2 = resultsArray.iterator();

        while (iterator2.hasNext()) {

            Object it2 = iterator2.next();
            JSONObject data2 = (JSONObject) it2;
            distance = (double) data2.get("travelDistance");
            duration = (double) data2.get("travelDuration");

        }

        Long finalDistance = Math.round(distance);
        Long finalDuration = Math.round(duration);

        DistanceBetweenPlacesDTO dto = new DistanceBetweenPlacesDTO(finalDistance, finalDuration);

        return dto;

    }

    /**
     *
     * @param enderecoPostal
     * @param rua
     * @param localidade
     * @return
     * @throws ParseException
     */
    public List<String> enriquecerDadosDeEnderecoByBing(String enderecoPostal, String rua, String localidade) throws ParseException {

        String jsonText = obterJSON(rua, localidade, enderecoPostal);

        if (jsonText == null) {
            return null;
        }
        //System.out.println("Json obtido: " + jsonText);
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(jsonText);

        String status = (String) json.get("statusDescription");

        List<String> lista = new ArrayList<>();

        if (status.equals("OK")) {
            JSONArray arrayResourceSets = (JSONArray) json.get("resourceSets");

            Iterator<Object> iterator1 = arrayResourceSets.iterator();
            while (iterator1.hasNext()) {
                Object it = iterator1.next();
                JSONObject data = (JSONObject) it;

                Long total = (Long) data.get("estimatedTotal"); // total de localizações encontradas

                //se total for menor ou igual que 0 não foram encontradas
                if (total <= 0) {
                    System.out.println("Localização inválida!");
                    return null;
                } else {
                    JSONObject json2 = (JSONObject) arrayResourceSets.get(0);
                    JSONArray arrayResources = (JSONArray) json2.get("resources");

                    Iterator<Object> iterator2 = arrayResources.iterator();

                    while (iterator2.hasNext()) {
                        Object it2 = iterator2.next();
                        JSONObject data2 = (JSONObject) it2;

                        JSONObject address = (JSONObject) data2.get("address");

                        //informação do distrito e pais
                        String distrito = (String) address.get("adminDistrict");
                        String pais = (String) address.get("countryRegion");

                        lista.add(distrito);
                        lista.add(pais);

                    }
                }

            }

        }
        return lista;

    }

    /**
     * Método que retorna a elevação de um determinado local de x
     * coordenadas.(Bing)
     *
     * @param latitude
     * @param longitude
     * @return
     * @throws ParseException
     */
    public Long getElevation(Double latitude, Double longitude) throws ParseException {

        List<Long> listaElevations = new ArrayList();
        String jsonText = obterJSONElevation(latitude, longitude);
        if (jsonText == null) {
            return null;
        }

        System.out.println("Json obtido: " + jsonText);
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(jsonText);

        String status = (String) json.get("statusDescription");
        System.out.println(status);

        if (status.equals("OK")) {
            JSONArray arrayResourceSets = (JSONArray) json.get("resourceSets");

            Iterator<Object> iterator1 = arrayResourceSets.iterator();
            while (iterator1.hasNext()) {
                Object it = iterator1.next();
                JSONObject data = (JSONObject) it;

                Long total = (Long) data.get("estimatedTotal"); // total de localizações encontradas
                //System.out.println(total);
                if (total <= 0) {
                    System.out.println("Localização inválida!");
                    return null;
                } else {
                    JSONObject json2 = (JSONObject) arrayResourceSets.get(0);
                    JSONArray arrayResources = (JSONArray) json2.get("resources");

                    Iterator<Object> iterator2 = arrayResources.iterator();
                    int i = 0;
                    while (iterator2.hasNext()) {
                        Object it2 = iterator2.next();

                        JSONObject json3 = (JSONObject) arrayResources.get(i);

                        JSONArray coordinates = (JSONArray) json3.get("elevations");

                        Iterator<Object> iterator3 = coordinates.iterator();
                        i++;
                        while (iterator3.hasNext()) {
                            Object it3 = iterator3.next();
                            Long elevation = (Long) it3;

                            //System.out.println(data3);
                            listaElevations.add(elevation);
                        }

                    }
                }
            }
            return listaElevations.get(0);
        } else {
            System.out.println("Localização inválida!");
            return null;
        }
    }

    /**
     * Método que retorna a diferença de elevação entre duas localizações
     * utilizando Bing
     *
     * @param rua1
     * @param localidade1
     * @param endereco1
     * @param rua2
     * @param localidade2
     * @param endereco2
     * @return
     * @throws ParseException
     */
    public double getDifferenceBing(String rua1, String localidade1, String endereco1, String rua2, String localidade2, String endereco2) throws ParseException {
        double diff = 0;

        List<Double> coordenadas1 = getCoordenadasFromJson(rua1, localidade1, endereco1);
        List<Double> coordenadas2 = getCoordenadasFromJson(rua2, localidade2, endereco2);

        Long elevation1 = getElevation(coordenadas1.get(0), coordenadas1.get(1));
        Long elevation2 = getElevation(coordenadas2.get(0), coordenadas2.get(1));

        diff = elevation1.doubleValue() - elevation2.doubleValue();
        Math.abs(diff);

        return diff;
    }

    /**
     * Método que devolve uma lista de locais de um dado tipo que estão a uma
     * distancia de um determinado raio de uma localização(bing).
     *
     * @param latitude
     * @param longitude
     * @param tipo
     * @param raio
     * @return
     * @throws ParseException
     */
    public List<String> getNearbyPlacesFromJson(Double latitude, Double longitude, String tipo, Double raio) throws ParseException {
        String jsonText = obterJSONNearbyPlaces(latitude, longitude, tipo, raio);
        if (jsonText == null) {
            return null;
        }

        System.out.println("Json obtido: " + jsonText);
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(jsonText);

        String status = (String) json.get("statusDescription");
        System.out.println(status);

        List<String> listaPlaces = new ArrayList<>();
        List<Double> listaCoordenadas = new ArrayList<>();
        if (status.equals("OK")) {
            JSONArray arrayResourceSets = (JSONArray) json.get("resourceSets");

            Iterator<Object> iterator1 = arrayResourceSets.iterator();
            while (iterator1.hasNext()) {
                Object it = iterator1.next();
                JSONObject data = (JSONObject) it;

                Long total = (Long) data.get("estimatedTotal"); // total de localizações encontradas
                //System.out.println(total);
                if (total <= 0) {
                    System.out.println("Localização inválida!");
                    return null;
                } else {
                    JSONObject json2 = (JSONObject) arrayResourceSets.get(0);
                    JSONArray arrayResources = (JSONArray) json2.get("resources");

                    Iterator<Object> iterator2 = arrayResources.iterator();
                    int i = 0;
                    while (iterator2.hasNext()) {
                        Object it2 = iterator2.next();
                        JSONObject data2 = (JSONObject) it2;

                        String name = (String) data2.get("name"); // nome da localização encontrada
                        System.out.println(name);

                        JSONObject json3 = (JSONObject) arrayResources.get(i);
                        JSONObject points = (JSONObject) json3.get("point");

                        JSONArray coordinates = (JSONArray) points.get("coordinates");

                        Iterator<Object> iterator3 = coordinates.iterator();
                        i++;
                        while (iterator3.hasNext()) {
                            Object it3 = iterator3.next();
                            Double data3 = (Double) it3;

                            //System.out.println(data3);
                            listaCoordenadas.add(data3);
                        }
                        StringBuffer sb = new StringBuffer();
                        sb.append(name);
                        sb.append("/");
                        for (int e = 0; e < listaCoordenadas.size(); e++) {
                            sb.append(listaCoordenadas.get(e));

                        }

                        listaPlaces.add(sb.toString());
                    }
                }
            }
        } else {
            System.out.println("Localização inválida!");
            return null;
        }
        return listaPlaces;
    }
}
