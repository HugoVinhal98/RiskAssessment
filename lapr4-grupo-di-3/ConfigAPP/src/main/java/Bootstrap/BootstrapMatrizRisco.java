/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bootstrap;

import Dominio.EstadoMatriz;
import Dominio.LinhaMatrizDetalhada;
import Dominio.MatrizRisco;
import Persistencia.LinhaMatrizDetalhadaRepositorio;
import Persistencia.LinhaMatrizDetalhadaRepositorioJPAImpl;
import Persistencia.MatrizRiscoRepositorio;
import Persistencia.MatrizRiscoRepositorioJPAImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jmbosg
 */
public class BootstrapMatrizRisco {

    public void registerMatrizRisco() {

        final LinhaMatrizDetalhadaRepositorio mdr = new LinhaMatrizDetalhadaRepositorioJPAImpl();
        final MatrizRiscoRepositorio mRepo = new MatrizRiscoRepositorioJPAImpl();

        List<LinhaMatrizDetalhada> listMdr = mdr.findAll();

        List<LinhaMatrizDetalhada> lista = new ArrayList<>();

        long id = 1;

        for (LinhaMatrizDetalhada l : listMdr) {
            if (l.getIdMatriz() == id) {
                lista.add(l);
            }

        }

        final MatrizRisco mr = new MatrizRisco("MR1", new Date(), new EstadoMatriz(0), lista);
        mRepo.add(mr);

        List<LinhaMatrizDetalhada> lista2 = new ArrayList<>();

        long id2 = 2;

        for (LinhaMatrizDetalhada l : listMdr) {
            if (l.getIdMatriz() == id2) {
                lista2.add(l);
            }

        }

        final MatrizRisco mr2 = new MatrizRisco("MR2", new Date(), new EstadoMatriz(0), lista2);
        mRepo.add(mr2);

    }

}
