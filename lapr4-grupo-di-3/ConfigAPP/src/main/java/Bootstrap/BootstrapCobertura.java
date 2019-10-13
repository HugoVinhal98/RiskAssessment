/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bootstrap;

import Dominio.Cobertura;
import Persistencia.CoberturaRepositorio;
import Persistencia.CoberturaRepositorioJPAImpl;

/**
 *
 * @author Francisco Negrão
 */
public class BootstrapCobertura {

    public void registerCobertura() {

        final String nome1 = "incendio";
        final String nome2 = "inundacao";
        final String nome3 = "sismo";
        final String nome4 = "tempestade";
        final String nome5 = "tsunami";
        final String nome6 = "infestacao";

        final CoberturaRepositorio coberturaPersistencia = new CoberturaRepositorioJPAImpl();
        final Cobertura cobertura1 = new Cobertura(nome1);
        final Cobertura cobertura2 = new Cobertura(nome2);
        final Cobertura cobertura3 = new Cobertura(nome3);
        final Cobertura cobertura4 = new Cobertura(nome4);
        final Cobertura cobertura5 = new Cobertura(nome5);
        final Cobertura cobertura6 = new Cobertura(nome6);

        coberturaPersistencia.add(cobertura1);
        coberturaPersistencia.add(cobertura2);
        coberturaPersistencia.add(cobertura3);
        coberturaPersistencia.add(cobertura4);
        coberturaPersistencia.add(cobertura5);
        coberturaPersistencia.add(cobertura6);
        
     

    }
}
