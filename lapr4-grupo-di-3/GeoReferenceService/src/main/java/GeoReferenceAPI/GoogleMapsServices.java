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
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.util.StringUtils;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author hugov
 */
public class GoogleMapsServices {

    /**
     *
     */
    public GoogleMapsServices() {
    }

    /**
     * Recebe um endereço postal e acede à API para retornar a informação em
     * formato JSON.
     *
     * @param rua
     * @param endrecoPostal
     * @param localidade
     * @return
     * @throws ParseException
     */
    public String obterJSON(String rua, String endrecoPostal, String localidade) throws ParseException {
        
        String dynamicURl = HTTPClient.prepareDynamicURLGoogle(rua, endrecoPostal, localidade);
        //System.out.println("dynamicURl: " + dynamicURl);
        String jsonInfo = null;
        if (!StringUtils.isEmpty(dynamicURl)) {
            jsonInfo = HTTPClient.getResultHttpAsStream(dynamicURl);
            //System.out.println("Result: " + jsonInfo);
            //String dynamicURl1 = HTTPClient.getResultHttpAsStream("https://maps.googleapis.com/maps/api/place/details/json?key=AIzaSyCbWGXQ1jlIW2z73nF8QqD_hvUm6AEcp1Y&placeid=ChIJiSUPRNmc44kRN2FiAO7bwt4");
        }
        return jsonInfo;
    }

    /**
     *
     * @param latitude
     * @param longitude
     * @return
     * @throws ParseException
     */
    public String obterJSONElavation(double latitude, double longitude) throws ParseException {
        
        String dynamicURl = HTTPClient.prepareDynamicURLElevationGoogle(latitude, longitude);
        //System.out.println("dynamicURl: " + dynamicURl);
        String jsonInfo = null;
        if (!StringUtils.isEmpty(dynamicURl)) {
            jsonInfo = HTTPClient.getResultHttpAsStream(dynamicURl);
            //System.out.println("Result: " + jsonInfo);
            //String dynamicURl1 = HTTPClient.getResultHttpAsStream("https://maps.googleapis.com/maps/api/place/details/json?key=AIzaSyCbWGXQ1jlIW2z73nF8QqD_hvUm6AEcp1Y&placeid=ChIJiSUPRNmc44kRN2FiAO7bwt4");
        }
        return jsonInfo;
    }

    /**
     *
     * @param latitude
     * @param longitude
     * @param tipo
     * @param raio
     * @return
     * @throws ParseException
     */
    public String obterJSONNearbyPlaces(Double latitude, Double longitude, String tipo, Double raio) throws ParseException {
        
        String dynamicURl = HTTPClient.prepareDynamicURLNearbyPlacesGoogle(latitude, longitude, tipo, raio);
        //System.out.println("dynamicURl: " + dynamicURl);
        String jsonInfo = null;
        if (!StringUtils.isEmpty(dynamicURl)) {
            jsonInfo = HTTPClient.getResultHttpAsStream(dynamicURl);
            //System.out.println("Result: " + jsonInfo);
            //String dynamicURl1 = HTTPClient.getResultHttpAsStream("https://maps.googleapis.com/maps/api/place/details/json?key=AIzaSyCbWGXQ1jlIW2z73nF8QqD_hvUm6AEcp1Y&placeid=ChIJiSUPRNmc44kRN2FiAO7bwt4");
        }
        return jsonInfo;
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
        
        String dynamicURl = HTTPClient.prepareDynamicURLDistanceBetweenPlacesGoogle(location1, location2, travelType);
        String jsonInfo = null;
        if (!StringUtils.isEmpty(dynamicURl)) {
            jsonInfo = HTTPClient.getResultHttpAsStream(dynamicURl);
        }
        return jsonInfo;
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
        
        List<Long> lista = new ArrayList<>();
        JSONParser parser = new JSONParser();
        
        Long distance = null;
        Long duration = null;
        
        String js = obterJSONDistanceBetweenPlaces(location1, location2, travelType);
        
        if (js == null) {
            return null;
        }
        
        JSONObject json = (JSONObject) parser.parse(js);
        
        String status = (String) json.get("status");
        
        if (!status.equals("OK")) {
            System.out.println("Pelo menos uma das localizacões inseridas está incorreta!");
            return null;
        }
        
        JSONArray array = (JSONArray) json.get("rows");
        
        Iterator<Object> iterator = array.iterator();
        Object it = iterator.next();
        JSONObject data = (JSONObject) it;
        
        JSONArray array1 = (JSONArray) data.get("elements");
        
        Iterator<Object> iterator1 = array1.iterator();
        
        while (iterator1.hasNext()) {
            
            Object it1 = iterator1.next();
            JSONObject data1 = (JSONObject) it1;
            JSONObject distanceObj = (JSONObject) data1.get("distance");
            JSONObject durationObj = (JSONObject) data1.get("duration");
            
            distance = (Long) distanceObj.get("value");
            duration = (Long) durationObj.get("value");
            
        }
        
        double dis = (double) distance / 1000;
        double dur = (double) duration / 60;
        
        Long finalDistance = (Long) Math.round(dis);
        Long finalDuration = (Long) Math.round(dur);
        
        DistanceBetweenPlacesDTO dto = new DistanceBetweenPlacesDTO(finalDistance, finalDuration);
        
        return dto;
        
    }

    /**
     * Através da string json, este metodo percorre a mesma para obter as
     * coordenadas gps relativas aos dados inseridos pelo utilizador.
     *
     * @param rua
     * @param localidade
     * @param endereco
     * @return
     * @throws ParseException
     */
    public List<Double> getCoordenadasFromJson(String rua, String localidade, String endereco) throws ParseException {
        List<Double> lista = new ArrayList<>();
        JSONParser parser = new JSONParser();
        
        String js = obterJSON(rua, endereco, localidade);
        if (js == null) {
            return null;
        }
        JSONObject json = (JSONObject) parser.parse(js);

        //verifica que se o ficehiro json retornou de facto alguma coisa 
        String status = (String) json.get("status");
        if (!status.equals("OK")) {
            System.out.println("Localização inválida!");
            return null;
        }
        
        JSONArray array = (JSONArray) json.get("results");
        Iterator<Object> iterator = array.iterator();
        Object it = iterator.next();
        JSONObject data = (JSONObject) it;
        JSONObject location = (JSONObject) array.get(0);
        JSONObject geo = (JSONObject) location.get("geometry");
        JSONObject locationC = (JSONObject) geo.get("location");
        Double lat = (Double) locationC.get("lat");
        Double lng = (Double) locationC.get("lng");
        
        System.out.println("");
        
        lista.add(lat);
        lista.add(lng);
        return lista;
    }

    /**
     * Método que devolve uma lista de locais de um dado tipo que estão a uma
     * distancia de um determinado raio de uma localização(google).
     *
     * @param latitude
     * @param longitude
     * @param tipo
     * @param raio
     * @return
     * @throws ParseException
     */
    public List<String> getNearbyPlacesFromJson(Double latitude, Double longitude, String tipo, Double raio) throws ParseException {
        
        JSONParser parser = new JSONParser();
        List<String> lista2 = new ArrayList<>();
        String js = obterJSONNearbyPlaces(latitude, longitude, tipo, raio);
        if (js == null) {
            return null;
        }
        JSONObject json = (JSONObject) parser.parse(js);

        //verifica que se o ficehiro json retornou de facto alguma coisa 
        String status = (String) json.get("status");
        if (!status.equals("OK")) {
            System.out.println("Localização inválida!");
            return null;
        }
        
        JSONArray array = (JSONArray) json.get("results");
        Iterator<Object> iterator = array.iterator();
        Object it = iterator.next();
        JSONObject data = (JSONObject) it;
        for (int i = 0; i < array.size(); i++) {
            
            JSONObject location = (JSONObject) array.get(i);
            JSONObject geo = (JSONObject) location.get("geometry");
            JSONObject locationC = (JSONObject) geo.get("location");
            Double lat = (Double) locationC.get("lat");
            Double lng = (Double) locationC.get("lng");
            
            String nome = (String) location.get("name");
            StringBuilder sb = new StringBuilder();
            sb.append(nome).append("/").append(lat).append("/").append(lng);
            String frase = sb.toString();
            lista2.add(frase);
            
        }
        System.out.println("");
        
        return lista2;
    }
    
    public String getPlaceFromLista(List<String> lista) {
        Scanner in = new Scanner(System.in);
        if (lista.size() == 1) {
            return lista.get(0).split("/")[0];
        } else if (lista.size() > 1) {
            System.out.println("\n Atendendo ao tipo e à localização foram encontradas as respetivas envolventes:");
            
            for (int i = 0; i < lista.size(); i++) {
                if (i == 3) {
                    break;
                } else {
                    System.out.println(i + 1 + ": " + lista.get(i).split("/")[0]);
                }
            }
            System.out.println("Seleciona uma das envolventes encontradas:");
            String input = in.nextLine();
            if (input.equalsIgnoreCase("1")) {
                return lista.get(0).split("/")[0];
            } else if (input.equalsIgnoreCase("2")) {
                return lista.get(1).split("/")[0];
            } else if (input.equalsIgnoreCase("3")) {
                if (lista.size() == 2) {
                    return lista.get(0).split("/")[0];
                } else {
                    return lista.get(2).split("/")[0];
                }
            } else {
                return lista.get(0).split("/")[0];
            }
        }
        return null;
    }

    /**
     *
     * @param endereco
     * @param rua
     * @param localidade
     * @return
     * @throws ParseException
     */
    public List<String> enriquecerDadosDeEnderecoByGoogle(String endereco, String rua, String localidade) throws ParseException {
        List<String> lista = new ArrayList<>();
        JSONParser parser = new JSONParser();
        
        String js = obterJSON(rua, endereco, localidade);
        JSONObject json = (JSONObject) parser.parse(js);

        //verifica que se o ficehiro json retornou de facto alguma coisa 
        String status = (String) json.get("status");
        if (!status.equals("OK")) {
            return null;
        }
        
        JSONArray array = (JSONArray) json.get("results");
        Iterator<Object> iterator = array.iterator();
        Object it = iterator.next();
        JSONObject data = (JSONObject) it;
        JSONArray array1 = (JSONArray) data.get("address_components");
        
        Iterator<Object> iterator1 = array1.iterator();
        while (iterator1.hasNext()) {
            Object it1 = iterator1.next();
            JSONObject data1 = (JSONObject) it1;
            JSONArray array2 = (JSONArray) data1.get("types");
            //verifica o distrito e adiciona 
            if (array2.get(0).equals("administrative_area_level_1")) {
                String long_name = (String) data1.get("long_name");
                lista.add(long_name);
                //verifica o pais e adiciona
            } else if (array2.get(0).equals("country")) {
                String long_name = (String) data1.get("long_name");
                lista.add(long_name);
            }
        }
        
        return lista;
    }

    /**
     * Método que retorna a elevação de um determinado local de x
     * coordenadas.(Google)
     *
     * @param latitude
     * @param longitude
     * @return
     * @throws ParseException
     */
    public Double getElevation(double latitude, double longitude) throws ParseException {
        Double elevation;
        JSONParser parser = new JSONParser();
        
        String js = obterJSONElavation(latitude, longitude);
        if (js == null) {
            return null;
        }
        JSONObject json = (JSONObject) parser.parse(js);

        //verifica que se o ficehiro json retornou de facto alguma coisa 
        String status = (String) json.get("status");
        if (!status.equals("OK")) {
            System.out.println("Localização inválida!");
            return null;
        }
        
        JSONArray array = (JSONArray) json.get("results");
        Iterator<Object> iterator = array.iterator();
        Object it = iterator.next();
        JSONObject data = (JSONObject) it;
        JSONObject location = (JSONObject) array.get(0);
        elevation = (Double) location.get("elevation");
        
        return elevation;
    }

    /**
     * Método que retorna a diferença de elevação entre duas localizações
     * utilizando Google
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
    public double getDifferenceGoogle(String rua1, String localidade1, String endereco1, String rua2, String localidade2, String endereco2) throws ParseException {
        double diff = 0;
        
        List<Double> coordenadas1 = getCoordenadasFromJson(rua1, localidade1, endereco1);
        List<Double> coordenadas2 = getCoordenadasFromJson(rua2, localidade2, endereco2);
        
        double elevation1 = getElevation(coordenadas1.get(0), coordenadas1.get(1));
        double elevation2 = getElevation(coordenadas2.get(0), coordenadas2.get(1));
        
        diff = elevation1 - elevation2;
        Math.abs(diff);
        
        return diff;
    }
    
}
