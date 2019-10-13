/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeoReferenceAPI;

import DTO.DistanceBetweenPlacesDTO;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hugov
 */
public class GoogleMapsServicesTest {

    public GoogleMapsServicesTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getCoordenadasFromJson method, of class GoogleMapsServices.
     */
    @Test
    public void testGetCoordenadasFromJson() throws Exception {
        System.out.println("getCoordenadasFromJson");
        GoogleMapsServices instance = new GoogleMapsServices();

        String rua = "rua joaquim moreira da silva";
        String localidade = "vila do conde";
        String endereco = "4480";

        String rua2 = "...";
        String localidade2 = "...";
        String endereco2 = "...";

        List<Double> expResult = new ArrayList<>();
        List<Double> expResult2 = null;

        expResult.add(41.3442806);
        expResult.add(-8.7476959);

        List<Double> result = instance.getCoordenadasFromJson(rua, localidade, endereco);
        List<Double> result2 = instance.getCoordenadasFromJson(rua2, localidade2, endereco2);

        assertEquals(expResult, result);
        assertEquals(expResult2, result2);
    }

    /**
     * Test of EnriquecerDadosDeEnderecoByGoogle method, of class
     * GoogleMapsServices.
     */
    @Test
    public void testEnriquecerDadosDeEnderecoByGoogle() throws Exception {
        System.out.println("EnriquecerDadosDeEnderecoByGoogle");
        String endereco = "4480";
        String rua = "rua joaquim moreira da silva";
        String localidade = "vila do conde";
        GoogleMapsServices instance = new GoogleMapsServices();
        List<String> expResult = new ArrayList<>();
        List<String> result = instance.enriquecerDadosDeEnderecoByGoogle(endereco, rua, localidade);
        expResult.add("Porto");
        expResult.add("Portugal");
        assertEquals(expResult, result);
        endereco = "..";
        rua = "";
        localidade = "";
        List<String> expResult1 = null;
        List<String> result1 = instance.enriquecerDadosDeEnderecoByGoogle(endereco, rua, localidade);
        assertEquals(expResult1, result1);
    }

    /**
     * Test of getNearbyPlacesFromJson method, of class GoogleMapsServices.
     */
    @Test
    public void testGetNearbyPlacesFromJson() throws Exception {
        System.out.println("getNearbyPlacesFromJson");
        Double latitude = -33.8670522;
        Double longitude = 151.1957362;
        String tipo = "restaurant";
        Double raio = 30.0;
        GoogleMapsServices instance = new GoogleMapsServices();
        String string1 = "Biaggio Cafe/-33.8669667/151.1958862";

        List<String> expResult = new ArrayList();
        expResult.add(string1);
        List<String> result = instance.getNearbyPlacesFromJson(latitude, longitude, tipo, raio);
        assertEquals(expResult, result);

    }

    /**
     * Test of getElevation method, of class GoogleMapsServices.
     */
    @Test
    public void testGetElevation() throws Exception {
        System.out.println("getElevation");
        double latitude = 20;
        double longitude = 10;
        GoogleMapsServices instance = new GoogleMapsServices();
        Double expResult = 538.543212890625;
        Double result = instance.getElevation(latitude, longitude);
        assertEquals(expResult, result);

    }

    /**
     * Test of getDifferenceGoogle method, of class GoogleAndBingServices.
     */
    @Test
    public void testGetDifferenceGoogle() throws Exception {
        System.out.println("getDifferenceGoogle");
        String rua1 = "Avenida dos Aliados";
        String localidade1 = "Porto";
        String endereco1 = "4000-064";
        String rua2 = "Rua Jornal de Noticias";
        String localidade2 = "Porto";
        String endereco2 = "4100-294";
        GoogleMapsServices instance = new GoogleMapsServices();
        double expResult = 16.034458160400398;
        double result = instance.getDifferenceGoogle(rua1, localidade1, endereco1, rua2, localidade2, endereco2);
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of getDistanceBetweenPlacesFromJson, of class GoogleMapsServices.
     */
    @Test
    public void testGetDistanceBetweenPlacesFromJson() throws Exception {

        System.out.println("getDistanceBetweenPlacesFromJson");
        GoogleMapsServices instance = new GoogleMapsServices();

        long distance = 313;
        long duration = 177;

        String location1 = "Porto,PT";
        String location2 = "Lisbon,PT";
        String travelType = "driving";

        DistanceBetweenPlacesDTO expResult = new DistanceBetweenPlacesDTO(distance, duration);

        DistanceBetweenPlacesDTO result = instance.getDistanceBetweenPlacesFromJson(location1, location2, travelType);

        System.out.println("Result Distance = " + result.getDistance());
        System.out.println("Result Duration = " + result.getDuration());
        System.out.println("ExpResult Distance = " + expResult.getDistance());
        System.out.println("ExpResult Duration = " + expResult.getDuration());

        assertEquals(expResult, result);

    }
}
