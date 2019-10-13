/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacao;

import Dominio.Envolvente;
import GeoReferenceAPI.GoogleMapsServices;
import Persistencia.EnvolventeRepositorioJPAImpl;
import java.util.List;
import org.json.simple.parser.ParseException;

/**
 *
 * @author hugov
 */
public class RegistarEnvolventeController {

    public Envolvente registarEnvolvente(String tipo, String nome, String endereco) throws ParseException {
        EnvolventeRepositorioJPAImpl repo = new EnvolventeRepositorioJPAImpl();
        GoogleMapsServices instance = new GoogleMapsServices();

        Envolvente e = new Envolvente(nome, tipo, endereco);

        List<Double> coordenadas = instance.getCoordenadasFromJson(endereco, "", "");
        if (coordenadas == null) {
            return null;
        } else {
            List<String> lista = instance.getNearbyPlacesFromJson(coordenadas.get(0), coordenadas.get(1), tipo, 3000.0);
            String nomeEnvolvente = instance.getPlaceFromLista(lista);

            e.retificarNome(nomeEnvolvente);
            e.mostrarImagem();
            repo.add(e);
            return e;
        }
    }
}
