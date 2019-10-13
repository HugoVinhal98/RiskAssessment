/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacao;

import GeoReferenceAPI.GoogleMapsServices;
import GeoReferenceAPI.MicrosoftBingServices;
import java.util.List;
import org.json.simple.parser.ParseException;

/**
 *
 * @author hugov
 */
public class ObterLocalizacaoViaEnderecoController {

    public List<Double> obterLocalizacaoGPSBing(String rua, String localidade, String codigoPostal) throws ParseException {
        MicrosoftBingServices mbs = new MicrosoftBingServices();

        List<Double> lista = mbs.getCoordenadasFromJson(rua, localidade, codigoPostal);
        return lista;
    }
    
    public List<Double> obterLocalizacaoGPSGoogle(String rua, String localidade, String codigoPostal) throws ParseException{
        GoogleMapsServices gms = new GoogleMapsServices();
        
        List<Double> lista = gms.getCoordenadasFromJson(rua, localidade, codigoPostal);
        return lista;
    }
}
