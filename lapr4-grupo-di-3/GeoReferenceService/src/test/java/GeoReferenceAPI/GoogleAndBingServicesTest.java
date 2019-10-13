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
 * @author Francisco Negrão
 */
public class GoogleAndBingServicesTest {

    public GoogleAndBingServicesTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of mediaElevation method, of class GoogleAndBingServices.
     */
    @Test
    public void testMediaElevation() throws Exception {
        System.out.println("mediaElevation");
        double latitude = 27.0;
        double longitude = 86.0;
        GoogleAndBingServices instance = new GoogleAndBingServices();
        double expResult = 358.64666748046875;
        double result = instance.mediaElevation(latitude, longitude);
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of listaFinalSemDuplicados method, of class GoogleAndBingServices.
     */
    @Test
    public void testListaFinalSemDuplicados() throws Exception {
        System.out.println("listaFinalSemDuplicados");
        Double latitude = 47.668979;
        Double longitude = -122.387562;
        String tipo = "MovieTheaters";
        String tipo2 = "movie_theater";
        Double raio = 5000.0;
        GoogleAndBingServices instance = new GoogleAndBingServices();
        String string = "Majestic Bay Theatres/47.6689429/-122.3840766";
        String string1 = "Zigogo/47.6688724/-122.3893912";
        String string2 = "AMC Oak Tree 6/47.70186996459961-122.34248352050781";
        String string3 = "Fremont Outdoor Movies/47.70186996459961-122.3424835205078147.65167999267578-122.35448455810547";
        String string4 = "Careys Cinema and More/47.70186996459961-122.3424835205078147.65167999267578-122.3544845581054747.66808319091797-122.3854751586914";
        String string5 = "Fremont Original Outdoor Cinema/47.70186996459961-122.3424835205078147.65167999267578-122.3544845581054747.66808319091797-122.385475158691447.61935043334961-122.33849334716797";
        List<String> expResult = new ArrayList();
        expResult.add(string);
        expResult.add(string1);
        expResult.add(string2);
        expResult.add(string3);
        expResult.add(string4);
        expResult.add(string5);
        List<String> result = instance.listaFinalSemDuplicados(latitude, longitude, tipo2, tipo, raio);
        assertEquals(expResult, result);

    }

    /**
     * Test of getAverageDistanceBetweenPlaces method, of class
     * GoogleAndBingServices.
     */
    @Test
    public void testGetAverageDistanceBetweenPlaces() throws Exception {

        System.out.println("getAverageDistanceBetweenPlaces");

        long averageDis = 26;
        long averageDur = 22;

        String location1 = "Seattle,WA";
        String location2 = "Redmond,WA";
        String coordinates1 = "47.6044,-122.3345";
        String coordinates2 = "47.6731,-122.1185";
        String travelType = "driving";

        GoogleAndBingServices instance = new GoogleAndBingServices();

        DistanceBetweenPlacesDTO expResult = new DistanceBetweenPlacesDTO(averageDis, averageDur);

        DistanceBetweenPlacesDTO result = instance.getAverageDistanceBetweenPlaces(location1, location2, coordinates1, coordinates2, travelType);
        
        System.out.println("Result Distance = " + result.getDistance());
        System.out.println("Result Duration = " + result.getDuration());
        System.out.println("ExpResult Distance = " + expResult.getDistance());
        System.out.println("ExpResult Duration = " + expResult.getDuration());

        assertEquals(expResult, result);

    }

    /**
     * Test of getDifferences method, of class GoogleAndBingServices.
     */
    @Test
    public void testGetDifferences() throws Exception {
        System.out.println("getDifferences");
        String rua1 = "Avenida dos Aliados";
        String localidade1 = "Porto";
        String endereco1 = "4000-064";
        String rua2 = "Rua Jornal de Noticias";
        String localidade2 = "Porto";
        String endereco2 = "4100-294";
        GoogleAndBingServices instance = new GoogleAndBingServices();
        double expResult = 16.034458160400398;
        double result = instance.getDifferences(rua1, localidade1, endereco1, rua2, localidade2, endereco2);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getDifferenceCombination method, of class GoogleAndBingServices.
     */
    @Test
    public void testGetDifferenceCombination() {
        System.out.println("getDifferenceCombination");
        double diffBing = 10;
        double diffGoogle = 20;
        GoogleAndBingServices instance = new GoogleAndBingServices();
        double expResult = 15;
        double result = instance.getDifferenceCombination(diffBing, diffGoogle);
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of getDistanceBetweenPlacesDefault method, of class
     * GoogleAndBingServices.
     */
    @Test
    public void testGetDistanceBetweenPlacesDefault() throws Exception {

        System.out.println("getDistanceBetweenPlacesDefault");

        long distance = 26;
        long duration = 21;

        String location1 = "Seattle,WA";
        String location2 = "Redmond,WA";
        String coordinates1 = "47.6044,-122.3345";
        String coordinates2 = "47.6731,-122.1185";
        String travelType = "driving";

        GoogleAndBingServices instance = new GoogleAndBingServices();

        DistanceBetweenPlacesDTO expResult = new DistanceBetweenPlacesDTO(distance, duration);

        DistanceBetweenPlacesDTO result = instance.getDistanceBetweenPlacesDefault(location1, location2, coordinates1, coordinates2, travelType);
        
        System.out.println("Result Distance = " + result.getDistance());
        System.out.println("Result Duration = " + result.getDuration());
        System.out.println("ExpResult Distance = " + expResult.getDistance());
        System.out.println("ExpResult Duration = " + expResult.getDuration());

        assertEquals(expResult, result);

    }

}
