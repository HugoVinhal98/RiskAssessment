/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bootstrap;

import Dominio.Envolvente;
import Persistencia.EnvolventeRepositorio;
import Persistencia.EnvolventeRepositorioJPAImpl;

/**
 *
 * @author GilTrindade
 */
public class BootstrapEnvolvente {

    public void registerEnvolvente() {
        
        final String nome1 = "bombeiros";
        final String nome2 = "policia";
        final String nome3 = "floresta";
        final String nome4 = "hospital";

        final EnvolventeRepositorio envolventePersis = new EnvolventeRepositorioJPAImpl();
        final Envolvente envolvente1 = new Envolvente(nome1);
        final Envolvente envolvente2 = new Envolvente(nome2);
        final Envolvente envolvente3 = new Envolvente(nome3);
        final Envolvente envolvente4 = new Envolvente(nome4);

        envolventePersis.add(envolvente1);
        envolventePersis.add(envolvente2);
        envolventePersis.add(envolvente3);
        envolventePersis.add(envolvente4);

    }
}
