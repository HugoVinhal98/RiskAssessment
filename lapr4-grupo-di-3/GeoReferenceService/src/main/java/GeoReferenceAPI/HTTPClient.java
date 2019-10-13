/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeoReferenceAPI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author hugov
 */
public class HTTPClient {

    public static final String bingLocationUrl = "http://dev.virtualearth.net/REST/v1/Locations";

    public static final String bingElevationUrl = "http://dev.virtualearth.net/REST/v1/Elevation/List?points=";

    public static final String googleLocatioUrl = "https://maps.googleapis.com/maps/api/geocode/";

    public static final String googleElevationUrl = "https://maps.googleapis.com/maps/api/elevation/";

    public static final String googleNearbyPlacesUrl = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=";

    public static final String googleDistanceAPIUrl = "https://maps.googleapis.com/maps/api/distancematrix/json?";

    public static final String bingNearbyPlacesUrl = "https://dev.virtualearth.net/REST/v1/LocalSearch/?type=";

    public static final String photoOfLocationUrl = "https://maps.googleapis.com/maps/api/staticmap?center=";

    public static final String bingDistanceUrl = "https://dev.virtualearth.net/REST/v1/Routes/DistanceMatrix?";

    protected static final String EQUAL = "=";

    protected static final String AND = "&";

    protected static final String COLON = ":";

    protected static final String PERCENTAGE = "%";

    public static final String bingAuthenticationKey = "Al1EAkK3EqBNoUdCQv97MAxdU8KcxPEdxuhMOoPxG4UMbrwbfVKETUpRCdS-8RBK";

    public static final String googleAuthenticationKey = "AIzaSyCbWGXQ1jlIW2z73nF8QqD_hvUm6AEcp1Y";

    public static final String photoOfLocationKey = "AIzaSyCbWGXQ1jlIW2z73nF8QqD_hvUm6AEcp1Y";

    protected static final String PARAM_KEY = "key";

    protected static final String PARAM_OUTPUT = "output";

    protected static final String JSON = "json";

    /**
     * Recebe o url para através dele fazer o pedido à API respetiva.
     *
     * @param url
     * @return json com informação recebida pela API
     */
    public static String getResultHttpAsStream(String url) {
        //System.out.println("url = " + url);
        HttpURLConnection conn = null;
        InputStream in = null;
        BufferedReader rd = null;
        StringBuffer sb = new StringBuffer();
        try {
            URL u = new URL(url);
            conn = (HttpURLConnection) u.openConnection();
            //System.out.println(conn.getResponseCode());
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                //in = conn.getInputStream();
                // Get the response
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = rd.readLine()) != null) {
                    sb.append(line);
                }
                rd.close();
            } else {
                System.out.println("Dados inseridos inválidos!");
                return null;
            }

        } catch (Throwable e) {
            in = null;
        } finally {
            conn = null;
        }
        return sb.toString();
    }

    /**
     * Consoante os dados inseridos pelo utilizador, o método pega no url base
     * da bing e acrescente a este os dados relativos ao endereço postal a
     * procurar.
     *
     * @param rua
     * @param localidade
     * @param zipCode
     * @return url completo
     */
    public static String prepareDynamicURLBing(String rua, String localidade, String zipCode) {
        StringBuffer sb = new StringBuffer();
        //    String teste = "http://dev.virtualearth.net/REST/v1/Locations/4480/Vila%20Do%20Conde/Rua%20Joaquim%20Moreira%20Da%20Silva%20170?o=json&key=Al1EAkK3EqBNoUdCQv97MAxdU8KcxPEdxuhMOoPxG4UMbrwbfVKETUpRCdS-8RBK";
        rua = rua.trim();
        localidade = localidade.trim();
        zipCode = zipCode.trim();

        sb.append(bingLocationUrl).append("/");
        sb.append(zipCode).append("/").append(localidade.replace(" ", "%20")).append("/").append(rua.replace(" ", "%20"));
        sb.append("?o=").append("json").append("&key=").append("Al1EAkK3EqBNoUdCQv97MAxdU8KcxPEdxuhMOoPxG4UMbrwbfVKETUpRCdS-8RBK");

        //appending bing authentication key
        return sb.toString();
    }

    /**
     * Constrói o URL necessário para fazer o request à Distance API da Google
     *
     * @param location1 Um dos locais a analisar
     * @param location2 Um dos locais a analisar
     * @param travelType Tipo de viagem
     * @return URL construído
     */
    public static String prepareDynamicURLDistanceBetweenPlacesGoogle(String location1, String location2, String travelType) {

        StringBuffer sb = new StringBuffer();

        location1 = location1.trim();
        location2 = location2.trim();
        travelType = travelType.trim();

        sb.append(googleDistanceAPIUrl);
        sb.append("units=metric");
        sb.append("&origins=").append(location1);
        sb.append("&destinations=").append(location2);
        sb.append("&travelType=").append(travelType);
        sb.append("&key=").append(googleAuthenticationKey);

        return sb.toString();

    }

    /**
     * Constrói o URL necessário para fazer o request à Distance API da
     * Microsoft
     *
     * @param location1 Um dos locais a analisar
     * @param location2 Um dos locais a analisar
     * @param travelMode Tipo de viagem
     * @return URL construído
     */
    public static String prepareDynamicURLDistanceBetweenPlacesBing(String location1, String location2, String travelMode) {

        StringBuffer sb = new StringBuffer();

        location1 = location1.trim();
        location2 = location2.trim();
        travelMode = travelMode.trim();

        sb.append(bingDistanceUrl);
        sb.append("origins=").append(location1);
        sb.append("&destinations=").append(location2);
        sb.append("&distanceUnit=").append("km");
        sb.append("&travelMode=").append(travelMode);
        sb.append("&timeUnit=").append("minute");
        sb.append("&key=").append(bingAuthenticationKey);

        return sb.toString();

    }

    /**
     * Consoante os dados inseridos pelo utilizador, o método pega no url base
     * da google e acrescente a este os dados relativos ao endereço postal a
     * procurar.
     *
     * @param rua
     * @param zipCode
     * @param localidade
     * @return url completo
     */
    public static String prepareDynamicURLGoogle(String rua, String zipCode, String localidade) {

        StringBuffer sb = new StringBuffer();
        //String result = HTTPClient.getResultHttpAsStream("https://maps.googleapis.com/maps/api/geocode/json?key=AIzaSyCbWGXQ1jlIW2z73nF8QqD_hvUm6AEcp1Y&components=postal_code:5000-446%7Ccountry:PT");
//        String teste = "https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&key=AIzaSyCbWGXQ1jlIW2z73nF8QqD_hvUm6AEcp1Y";
//        String teste2 = "https://maps.googleapis.com/maps/api/geocode/json?address=Rua+Joaquim+Moreira+da+Silva,+170&key=AIzaSyCbWGXQ1jlIW2z73nF8QqD_hvUm6AEcp1Y";
//        String teste3 = "https://maps.googleapis.com/maps/api/geocode/json?address=Rua+Santa+Iria+5000&key=AIzaSyCbWGXQ1jlIW2z73nF8QqD_hvUm6AEcp1Y";
        //retirar espaços inciais e finais
        rua = rua.trim();
        zipCode = zipCode.trim();
        localidade = localidade.trim();
        sb.append(googleLocatioUrl);
        sb.append(JSON).append("?").append("address").append(EQUAL).append(rua.replace(" ", "+")).append("+").append(zipCode).append("+").append(localidade.replace(" ", "+"));
        sb.append(AND).append(PARAM_KEY).append(EQUAL).append(googleAuthenticationKey);

        String dynamicURL = sb.toString();

        return dynamicURL;
    }

    /**
     * Consoante os dados inseridos pelo utilizador, o método pega no url base
     * da google e acrescente a este os dados relativos à elevação a procurar.
     *
     * @param latitude
     * @param longitude
     * @return
     */
    public static String prepareDynamicURLElevationGoogle(Double latitude, Double longitude) {

        StringBuffer sb = new StringBuffer();
        //String result = HTTPClient.getResultHttpAsStream("https://maps.googleapis.com/maps/api/geocode/json?key=AIzaSyCbWGXQ1jlIW2z73nF8QqD_hvUm6AEcp1Y&components=postal_code:5000-446%7Ccountry:PT");
//        String teste = "https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&key=AIzaSyCbWGXQ1jlIW2z73nF8QqD_hvUm6AEcp1Y";
//        String teste2 = "https://maps.googleapis.com/maps/api/geocode/json?address=Rua+Joaquim+Moreira+da+Silva,+170&key=AIzaSyCbWGXQ1jlIW2z73nF8QqD_hvUm6AEcp1Y";
//        String teste3 = "https://maps.googleapis.com/maps/api/geocode/json?address=Rua+Santa+Iria+5000&key=AIzaSyCbWGXQ1jlIW2z73nF8QqD_hvUm6AEcp1Y";
        //retirar espaços inciais e finais
        String latitudes = latitude.toString();
        String longitudes = longitude.toString();

        sb.append(googleElevationUrl);
        sb.append(JSON).append("?").append("locations").append(EQUAL).append(latitudes).append(",").append(longitude);
        sb.append(AND).append(PARAM_KEY).append(EQUAL).append(googleAuthenticationKey);

        String dynamicURL = sb.toString();

        return dynamicURL;
    }

    /**
     * Consoante os dados inseridos pelo utilizador, o método pega no url base
     * da bing e acrescente a este os dados relativos ao endereço postal a
     * procurar.
     *
     * @param latitude
     * @param longitude
     * @return
     */
    public static String prepareDynamicURLElevationBing(Double latitude, Double longitude) {
        StringBuffer sb = new StringBuffer();
        //    String teste = "http://dev.virtualearth.net/REST/v1/Locations/4480/Vila%20Do%20Conde/Rua%20Joaquim%20Moreira%20Da%20Silva%20170?o=json&key=Al1EAkK3EqBNoUdCQv97MAxdU8KcxPEdxuhMOoPxG4UMbrwbfVKETUpRCdS-8RBK";
        String latitudes = latitude.toString();
        String longitudes = longitude.toString();

        sb.append(bingElevationUrl);
        sb.append(latitude).append(",").append(longitude).append("&key=").append(bingAuthenticationKey);

        //appending bing authentication key
        return sb.toString();
    }

    /**
     * Consoante os dados inseridos pelo utilizador, o método pega no url base
     * da google e acrescente a este os dados relativos aos locais a procurar.
     *
     * @param latitude
     * @param longitude
     * @param tipo
     * @param raio
     * @return
     */
    public static String prepareDynamicURLNearbyPlacesGoogle(Double latitude, Double longitude, String tipo, Double raio) {

        StringBuffer sb = new StringBuffer();
        //String result = HTTPClient.getResultHttpAsStream("https://maps.googleapis.com/maps/api/geocode/json?key=AIzaSyCbWGXQ1jlIW2z73nF8QqD_hvUm6AEcp1Y&components=postal_code:5000-446%7Ccountry:PT");
//        String teste = "https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&key=AIzaSyCbWGXQ1jlIW2z73nF8QqD_hvUm6AEcp1Y";
//        String teste2 = "https://maps.googleapis.com/maps/api/geocode/json?address=Rua+Joaquim+Moreira+da+Silva,+170&key=AIzaSyCbWGXQ1jlIW2z73nF8QqD_hvUm6AEcp1Y";
//        String teste3 = "https://maps.googleapis.com/maps/api/geocode/json?address=Rua+Santa+Iria+5000&key=AIzaSyCbWGXQ1jlIW2z73nF8QqD_hvUm6AEcp1Y";
        //retirar espaços inciais e finais
        String latitudes = latitude.toString();
        String longitudes = longitude.toString();
        String raios = raio.toString();

        sb.append(googleNearbyPlacesUrl);
        sb.append(latitudes).append(",").append(longitude).append(AND).append("radius=").append(raios).append(AND).append("type=").append(tipo);
        sb.append(AND).append("key").append(EQUAL).append(googleAuthenticationKey);

        String dynamicURL = sb.toString();

        return dynamicURL;
    }

    /**
     * Consoante os dados inseridos pelo utilizador, o método pega no url base
     * da bing e acrescente a este os dados relativos aos locais procurar.
     *
     * @param latitude
     * @param longitude
     * @param tipo
     * @param raio
     * @return
     */
    public static String prepareDynamicURLNearbyPlacesBing(Double latitude, Double longitude, String tipo, Double raio) {
        StringBuffer sb = new StringBuffer();
        //    String teste = "http://dev.virtualearth.net/REST/v1/Locations/4480/Vila%20Do%20Conde/Rua%20Joaquim%20Moreira%20Da%20Silva%20170?o=json&key=Al1EAkK3EqBNoUdCQv97MAxdU8KcxPEdxuhMOoPxG4UMbrwbfVKETUpRCdS-8RBK";
        String latitudes = latitude.toString();
        String longitudes = longitude.toString();
        String raios = raio.toString();

        sb.append(bingNearbyPlacesUrl);
        sb.append(tipo).append(AND).append("userCircularMapView=").append(latitude).append(",").append(longitude).append(",").append(raio).append(AND).append("key=").append(bingAuthenticationKey);

        //appending bing authentication ke
        System.out.println(sb.toString());
        return sb.toString();
    }

    public static String prepareDynamicURLPhotoPlaceMenos(String address) {
        StringBuffer sb = new StringBuffer();

        //String url = "https://maps.googleapis.com/maps/api/staticmap?center=hospital%20da%20luz%20povoa%20varzim&zoom=14&size=400x400&key=AIzaSyCbWGXQ1jlIW2z73nF8QqD_hvUm6AEcp1Y";
        sb.append(photoOfLocationUrl);
        sb.append(address.replace(" ", "%20")).append("&zoom=14&size=600x600&key=").append(photoOfLocationKey);

        String res = sb.toString();
        System.out.println(res);

        return res;
    }

    public static String prepareDynamicURLPhotoPlaceMais(String address) {
        StringBuffer sb = new StringBuffer();

        //String url = "https://maps.googleapis.com/maps/api/staticmap?center=hospital%20da%20luz%20povoa%20varzim&zoom=18&size=400x400&key=AIzaSyCbWGXQ1jlIW2z73nF8QqD_hvUm6AEcp1Y";
        sb.append(photoOfLocationUrl);
        sb.append(address.replace(" ", "%20")).append("&zoom=18&size=600x600&key=").append(photoOfLocationKey);

        String res = sb.toString();
        System.out.println(res);

        return res;
    }

    public static String getConfiguredMethodFromConfigFile(String SGUC) throws FileNotFoundException, IOException {

        File file = new File("configsGeoRef.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String line;
        String result = null;

        while ((line = br.readLine()) != null) {

            if (line.startsWith(SGUC)) {
                String[] array = line.split(":");
                result = array[1];
                result = result.trim();
            }

        }

        return result;
    }
}
