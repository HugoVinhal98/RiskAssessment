/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bootstrap;

import Dominio.Envolvente;
import Dominio.FatorRisco;
import Dominio.Metrica;
import Persistencia.EnvolventeRepositorioJPAImpl;
import Persistencia.FatorDeRiscoRepositorioJPAImpl;
import java.util.List;

/**
 *
 * @author Francisco Negrão
 */
public class BootstrapFatorRisco {

    public void registerFatorRisco() {

        final FatorDeRiscoRepositorioJPAImpl fatorRiscoPersis = new FatorDeRiscoRepositorioJPAImpl();
        final EnvolventeRepositorioJPAImpl envolventePersis = new EnvolventeRepositorioJPAImpl();
        
        List<Envolvente> envolventeList = envolventePersis.findAll();
        
        final Metrica metrica1 = new Metrica("distancia minima");
        final Metrica metrica2 = new Metrica("tempo minimo");
        final Metrica metrica3 = new Metrica("tempo minimo");
        final Metrica metrica4 = new Metrica("quantidade");
        final Metrica metrica5 = new Metrica("area");

        final FatorRisco fatorRisco1 = new FatorRisco(envolventeList.get(0), metrica1); //Bombeiros
        final FatorRisco fatorRisco2 = new FatorRisco(envolventeList.get(0), metrica2); //Bombeiros
        final FatorRisco fatorRisco3 = new FatorRisco(envolventeList.get(1), metrica3); //Policia
        final FatorRisco fatorRisco4 = new FatorRisco(envolventeList.get(3), metrica4); //Hospital
        final FatorRisco fatorRisco5 = new FatorRisco(envolventeList.get(2), metrica5); //Floresta

        fatorRiscoPersis.add(fatorRisco1);
        fatorRiscoPersis.add(fatorRisco2);
        fatorRiscoPersis.add(fatorRisco3);
        fatorRiscoPersis.add(fatorRisco4);
        fatorRiscoPersis.add(fatorRisco5);

    }
}
