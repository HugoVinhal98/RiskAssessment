/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bootstrap;

import Dominio.*;
import Persistencia.*;

/**
 *
 * @author GilTrindade
 */
public class BootstrapAnalistaRisco {

    public void bootstrap() {

        final AnalistaRiscoRepositorio pedidoPersistencia = new AnalistaRiscoRepositorioJPAImpl();

        final AnalistaRisco analista1 = new AnalistaRisco(new NomeUtilizador("RuiBarbosa123"), new Password("RuiBarbosa123"), new Email("rui@gmail.com"));
        final AnalistaRisco analista2 = new AnalistaRisco(new NomeUtilizador("PedroBarbosa123"), new Password("PedroBarbosa123"), new Email("pedro@gmail.com"));
        pedidoPersistencia.add(analista1);
        pedidoPersistencia.add(analista2);
    }

}
