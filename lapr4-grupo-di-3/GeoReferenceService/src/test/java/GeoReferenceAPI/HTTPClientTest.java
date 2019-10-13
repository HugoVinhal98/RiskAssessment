/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeoReferenceAPI;

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
public class HTTPClientTest {

    public HTTPClientTest() {
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
     * Test of prepareDynamicURLBing method, of class HTTPClient.
     */
    @Test
    public void testPrepareDynamicURLBing() {
        System.out.println("prepareDynamicURLBing");
        String rua = "rua santa iria";
        String localidade = "vila real";
        String zipCode = "5000-446";
        String expResult = "http://dev.virtualearth.net/REST/v1/Locations/5000-446/vila%20real/rua%20santa%20iria?o=json&key=Al1EAkK3EqBNoUdCQv97MAxdU8KcxPEdxuhMOoPxG4UMbrwbfVKETUpRCdS-8RBK";
        String result = HTTPClient.prepareDynamicURLBing(rua, localidade, zipCode);
        assertEquals(expResult, result);
    }

    /**
     * Test of prepareDynamicURLGoogle method, of class HTTPClient.
     */
    @Test
    public void testPrepareDynamicURLGoogle() {
        System.out.println("prepareDynamicURLGoogle");
        String rua = "rua santa iria";
        String zipCode = "5000-446";
        String localidade = "vila real";
        String expResult = "https://maps.googleapis.com/maps/api/geocode/json?address=rua+santa+iria+5000-446+vila+real&key=AIzaSyCbWGXQ1jlIW2z73nF8QqD_hvUm6AEcp1Y";
        String result = HTTPClient.prepareDynamicURLGoogle(rua, zipCode, localidade);
        assertEquals(expResult, result);
    }

    /**
     * Test of prepareDynamicURLElevationGoogle method, of class HTTPClient.
     */
    @Test
    public void testPrepareDynamicURLElevationGoogle() {
        System.out.println("prepareDynamicURLElevationGoogle");
        Double latitude = 20.0;
        Double longitude = 10.0;
        String expResult = "https://maps.googleapis.com/maps/api/elevation/json?locations=20.0,10.0&key=AIzaSyCbWGXQ1jlIW2z73nF8QqD_hvUm6AEcp1Y";
        String result = HTTPClient.prepareDynamicURLElevationGoogle(latitude, longitude);
        assertEquals(expResult, result);

    }

    /**
     * Test of prepareDynamicURLElevationBing method, of class HTTPClient.
     */
    @Test
    public void testPrepareDynamicURLElevationBing() {
        System.out.println("prepareDynamicURLElevationBing");
        Double latitude = 35.89431;
        Double longitude = -110.72522;
        String expResult = "http://dev.virtualearth.net/REST/v1/Elevation/List?points=35.89431,-110.72522&key=Al1EAkK3EqBNoUdCQv97MAxdU8KcxPEdxuhMOoPxG4UMbrwbfVKETUpRCdS-8RBK";
        String result = HTTPClient.prepareDynamicURLElevationBing(latitude, longitude);
        assertEquals(expResult, result);

    }

    /**
     * Test of prepareDynamicURLNearbyPlacesGoogle method, of class HTTPClient.
     */
    @Test
    public void testPrepareDynamicURLNearbyPlacesGoogle() {
        System.out.println("prepareDynamicURLNearbyPlacesGoogle");
        Double latitude = -33.8670522;
        Double longitude = 151.1957362;
        String tipo = "restaurant";
        Double raio = 30.0;
        String expResult = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=30.0&type=restaurant&key=AIzaSyCbWGXQ1jlIW2z73nF8QqD_hvUm6AEcp1Y";
        String result = HTTPClient.prepareDynamicURLNearbyPlacesGoogle(latitude, longitude, tipo, raio);
        assertEquals(expResult, result);

    }

    /**
     * Test of prepareDynamicURLNearbyPlacesBing method, of class HTTPClient.
     */
    @Test
    public void testPrepareDynamicURLNearbyPlacesBing() {
        System.out.println("prepareDynamicURLNearbyPlacesBing");
        Double latitude = 47.668979;
        Double longitude = -122.387562;
        String tipo = "MovieTheaters,KoreanRestaurants";
        Double raio = 200.0;
        String expResult = "https://dev.virtualearth.net/REST/v1/LocalSearch/?type=MovieTheaters,KoreanRestaurants&userCircularMapView=47.668979,-122.387562,200.0&key=Al1EAkK3EqBNoUdCQv97MAxdU8KcxPEdxuhMOoPxG4UMbrwbfVKETUpRCdS-8RBK";
        String result = HTTPClient.prepareDynamicURLNearbyPlacesBing(latitude, longitude, tipo, raio);
        assertEquals(expResult, result);

    }

    /**
     * Test of prepareDynamicURLDistanceBetweenPlacesGoogle method, of class
     * HTTPClient.
     */
    @Test
    public void testPrepareDynamicURLDistanceBetweenPlacesGoogle() {
        System.out.println("prepareDynamicURLDistanceBetweenPlacesGoogle");
        String location1 = "Porto,PT";
        String location2 = "Lisbon,PT";
        String travelType = "driving";
        String expResult = "https://maps.googleapis.com/maps/api/distancematrix/json?units=metric&origins=Porto,PT&destinations=Lisbon,PT&travelType=driving&key=AIzaSyCbWGXQ1jlIW2z73nF8QqD_hvUm6AEcp1Y";
        String result = HTTPClient.prepareDynamicURLDistanceBetweenPlacesGoogle(location1, location2, travelType);
        System.out.println(result);
        assertEquals(expResult, result);

    }

    /**
     * Test of prepareDynamicURLDistanceBetweenPlacesBing method, of class
     * HTTPClient.
     */
    @Test
    public void testprepareDynamicURLDistanceBetweenPlacesBing() {

        System.out.println("prepareDynamicURLDistanceBetweenPlacesBing");
        String location1 = "37.77916,-122.42";
        String location2 = "32.71568,-117.16172";
        String travelType = "driving";
        String expResult = "https://dev.virtualearth.net/REST/v1/Routes/DistanceMatrix?origins=37.77916,-122.42&destinations=32.71568,-117.16172&distanceUnit=km&travelMode=driving&timeUnit=minute&key=Al1EAkK3EqBNoUdCQv97MAxdU8KcxPEdxuhMOoPxG4UMbrwbfVKETUpRCdS-8RBK";
        String result = HTTPClient.prepareDynamicURLDistanceBetweenPlacesBing(location1, location2, travelType);
        System.out.println(result);
        assertEquals(expResult, result);

    }

}
