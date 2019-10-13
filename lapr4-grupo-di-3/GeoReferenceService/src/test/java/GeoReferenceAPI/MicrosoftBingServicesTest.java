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
public class MicrosoftBingServicesTest {

    public MicrosoftBingServicesTest() {
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
     * Test of getCoordenadasFromJson method, of class MicrosoftBingServices.
     */
    @Test
    public void testGetCoordenadasFromJson() throws Exception {
        System.out.println("getCoordenadasFromJson");
        MicrosoftBingServices instance = new MicrosoftBingServices();

        String rua = "rua joaquim moreira da silva";
        String localidade = "vila do conde";
        String endereco = "4480";

        String rua2 = "...";
        String localidade2 = "...";
        String endereco2 = "...";

        List<Double> expResult = new ArrayList<>();
        List<Double> expResult2 = null;

        expResult.add(41.34431);
        expResult.add(-8.74794);

        List<Double> result = instance.getCoordenadasFromJson(rua, localidade, endereco);
        List<Double> result2 = instance.getCoordenadasFromJson(rua2, localidade2, endereco2);

        assertEquals(expResult, result);
        assertEquals(expResult2, result2);

    }

    /**
     * Test of EnriquecerDadosDeEnderecoByBing method, of class
     * MicrosoftBingServices.
     */
    @Test
    public void testEnriquecerDadosDeEnderecoByBing() throws Exception {
        System.out.println("EnriquecerDadosDeEnderecoByBing");
        String enderecoPostal = "4480";
        String rua = "rua joaquim moreira da silva";
        String localidade = "vila do conde";
        MicrosoftBingServices instance = new MicrosoftBingServices();
        List<String> expResult = new ArrayList<>();
        expResult.add("Porto");
        expResult.add("Portugal");
        List<String> result = instance.enriquecerDadosDeEnderecoByBing(enderecoPostal, rua, localidade);
        //em caso de erro
        assertEquals(expResult, result);
        enderecoPostal = "..";
        rua = "";
        localidade = "";
        List<String> expResult1 = null;
        List<String> result1 = instance.enriquecerDadosDeEnderecoByBing(enderecoPostal, rua, localidade);
        assertEquals(expResult1, result1);
    }

    /**
     * Test of getNearbyPlacesFromJson method, of class MicrosoftBingServices.
     */
    @Test
    public void testGetNearbyPlacesFromJson() throws Exception {
        System.out.println("getNearbyPlacesFromJson");
        Double latitude = 47.668979;
        Double longitude = -122.387562;
        String tipo = "MovieTheaters";
        Double raio = 200.0;
        MicrosoftBingServices instance = new MicrosoftBingServices();
        List<String> expResult = new ArrayList<>();
        expResult.add("Careys Cinema and More/47.66808319091797-122.3854751586914");
        List<String> result = instance.getNearbyPlacesFromJson(latitude, longitude, tipo, raio);
        assertEquals(expResult, result);

    }

    /**
     * Test of getElevation method, of class MicrosoftBingServices.
     */
    @Test
    public void testGetElevation() throws Exception {
        System.out.println("getElevation");
        Double latitude = 35.89431;
        Double longitude = -110.72522;
        MicrosoftBingServices instance = new MicrosoftBingServices();
        long expResult = 1776;
        long result = instance.getElevation(latitude, longitude);
        assertEquals(expResult, result);
    }

    /**
     * Test of getDifferenceBing method, of class GoogleAndBingServices.
     */
    @Test
    public void testGetDifferenceBing() throws Exception {
        System.out.println("getDifferenceBing");
        String rua1 = "Rua Nova das Perlinhas";
        String localidade1 = "Rio Tinto";
        String endereco1 = "4435-353";
        String rua2 = "Rua Nova das Perlinhas";
        String localidade2 = "Rio Tinto";
        String endereco2 = "4435-353";
        MicrosoftBingServices instance = new MicrosoftBingServices();
        double expResult = 0.0;
        double result = instance.getDifferenceBing(rua1, localidade1, endereco1, rua2, localidade2, endereco2);
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of getDistanceBetweenPlacesFromJson, of class MicrosoftBingServices.
     */
    @Test
    public void testGetDistanceBetweenPlacesFromJson() throws Exception {

        System.out.println("getDistanceBetweenPlacesFromJson");
        MicrosoftBingServices instance = new MicrosoftBingServices();

        long distance = 808;
        long duration = 432;

        String location1 = "37.77916,-122.42";
        String location2 = "32.71568,-117.16172";
        String travelMode = "driving";

        DistanceBetweenPlacesDTO expResult = new DistanceBetweenPlacesDTO(distance, duration);

        DistanceBetweenPlacesDTO result = instance.getDistanceBetweenPlacesFromJson(location1, location2, travelMode);
        
        System.out.println("Result Distance = " + result.getDistance());
        System.out.println("Result Duration = " + result.getDuration());
        System.out.println("ExpResult Distance = " + expResult.getDistance());
        System.out.println("ExpResult Duration = " + expResult.getDuration());

        assertEquals(expResult, result);

    }

}
