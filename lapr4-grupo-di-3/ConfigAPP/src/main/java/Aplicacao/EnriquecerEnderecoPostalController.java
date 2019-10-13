/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacao;

import Dominio.EnderecoPostal;
import Dominio.ObjetoSeguro;
import java.util.List;
import GeoReferenceAPI.GoogleMapsServices;
import GeoReferenceAPI.MicrosoftBingServices;
import Persistencia.ObjetoSeguroRepositorio;
import Persistencia.ObjetoSeguroRepositorioJPAImpl;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Diogo Rolo
 */
public class EnriquecerEnderecoPostalController {

    public List<String> obterDadosGoogle(String enderecoPostal, String rua, String localidade) throws Exception {
        
        GoogleMapsServices qms = new GoogleMapsServices();
        
        List<String> l = qms.enriquecerDadosDeEnderecoByGoogle(enderecoPostal,rua,localidade);
       
        return  l;
    }

    public List<String> obterDadosBing(String rua, String localidade, String enderecoPostal) throws ParseException {
 
        MicrosoftBingServices mbs = new MicrosoftBingServices();
    
        List<String> l = mbs.enriquecerDadosDeEnderecoByBing(enderecoPostal,rua,localidade);
        
        return l;
    }

    public void persistirObjetoSeguro(String cod, String rua, String local, String distrito, String pais) throws ParseException {
        
        ObjetoSeguroRepositorio osr = new ObjetoSeguroRepositorioJPAImpl();
        MicrosoftBingServices mbs = new MicrosoftBingServices();
        
        //Cria objeto endereco postal
        EnderecoPostal ep = new EnderecoPostal(distrito, pais, rua, cod, local);
       
        
//        Double lati = null;
//        Double longi = null;
//        
//        //Procurar coordenadas
//        List<Double> list = mbs.getCoordenadasFromJson(rua, local, cod);
//        if(list.size() == 2){
//        lati = list.get(0);
//        longi = list.get(1);
//        }
        
        //Cria objeto seguro
        ObjetoSeguro os = new ObjetoSeguro(ep);

        osr.add(os);
        
    }

}