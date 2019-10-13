/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacao;

import GeoReferenceAPI.GoogleMapsServices;
import GeoReferenceAPI.MicrosoftBingServices;
import org.json.simple.parser.ParseException;

/**
 *
 * @author GilTrindade
 */
public class ObterElevacaoViaEnderecoController {

    public double obterElevacao(Double get, Double get0) throws ParseException {
        GoogleMapsServices google = new GoogleMapsServices();
        MicrosoftBingServices bing = new MicrosoftBingServices();
        double elevation1 = google.getElevation(get, get0);
        double elevation2 = bing.getElevation(get, get0);
        
        return (elevation1 + elevation2) / 2;
    }
    
}
