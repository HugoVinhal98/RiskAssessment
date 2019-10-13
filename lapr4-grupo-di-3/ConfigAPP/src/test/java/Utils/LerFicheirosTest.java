/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Bootstrap.Bootstrapper;
import Dominio.Cobertura;
import Dominio.Envolvente;
import Dominio.EscalaRisco;
import Dominio.EstadoMatriz;
import Dominio.FatorRisco;
import Dominio.LinhaMatrizBase;
import Dominio.LinhaMatrizCaraterizada;
import Dominio.LinhaMatrizDetalhada;
import Dominio.MatrizRisco;
import Dominio.Metrica;
import Dominio.Peso;
import Dominio.Peso_;
import Persistencia.EnvolventeRepositorio;
import Persistencia.EnvolventeRepositorioJPAImpl;
import Persistencia.FatorDeRiscoRepositorio;
import Persistencia.FatorDeRiscoRepositorioJPAImpl;
import Persistencia.MatrizRiscoRepositorio;
import Persistencia.MatrizRiscoRepositorioJPAImpl;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hugov
 */
public class LerFicheirosTest {

    public LerFicheirosTest() {
    }

    /**
     * Test of lerFatoresDeRiscoDeFicheiro method, of class FatorRisco.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testLerFatoresDeRiscoDeFicheiro() throws Exception {
        System.out.println("lerFatoresDeRiscoDeFicheiro");

        EnvolventeRepositorio repo = new EnvolventeRepositorioJPAImpl();
        Envolvente e1 = new Envolvente("bombeiros");
        Envolvente e2 = new Envolvente("policia");
        Envolvente e3 = new Envolvente("floresta");
        Envolvente e4 = new Envolvente("hospital");
        repo.add(e1);
        repo.add(e2);
        repo.add(e3);
        repo.add(e4);

        ArrayList<FatorRisco> expResult = new ArrayList<>();
        expResult.add(new FatorRisco(e1, new Metrica("distancia minima")));
        expResult.add(new FatorRisco(e1, new Metrica("tempo minimo")));
        expResult.add(new FatorRisco(e2, new Metrica("tempo minimo")));
        expResult.add(new FatorRisco(e4, new Metrica("quantidade")));
        expResult.add(new FatorRisco(e3, new Metrica("area")));

        String nomeFicheiro = "fatoresDeRisco.csv";

        ArrayList<FatorRisco> result = LerFicheiros.lerFatoresDeRiscoDeFicheiro(nomeFicheiro);
        assertEquals(expResult, result);

    }

    /**
     * Test of fatoresNaoExistentes method, of class MatrizRisco.
     */
    @Test
    public void testFatoresNaoExistentes() {
        System.out.println("fatoresNaoExistentes");
        MatrizRiscoRepositorio mrrepo = new MatrizRiscoRepositorioJPAImpl();
        EnvolventeRepositorio en = new EnvolventeRepositorioJPAImpl();
        LerFicheiros instance = new LerFicheiros();
        int i = 0;
        Bootstrapper b = new Bootstrapper();
        b.executeBootstrap();

        FatorDeRiscoRepositorio mrr = new FatorDeRiscoRepositorioJPAImpl();
        List<FatorRisco> expResult = new ArrayList<>();
        expResult.add(mrr.findById(1L));
        expResult.add(mrr.findById(2L));
        expResult.add(mrr.findById(3L));
        expResult.add(mrr.findById(4L));
        expResult.add(mrr.findById(5L));

        MatrizRisco mr1 = new MatrizRisco();

        mrrepo.add(mr1);
        HashSet<FatorRisco> result1 = instance.fatoresNaoExistentes(mr1);
        List<FatorRisco> result = new ArrayList<>();
        int j = 0;
        for (i = 0; i <= result1.size(); i++) {
            for (FatorRisco fr : result1) {
                if (fr.getId() == j) {
                    result.add(fr);
                }
            }
            j++;
        }

        System.out.println("");

        assertEquals(expResult, result);

    }

    /**
     * Test of verificarIdMatrizRisco method, of class LerFicheiros.
     */
    @Test
    public void testVerificarIdMatrizRisco() {
        System.out.println("verificarIdMatrizRisco");
        MatrizRiscoRepositorio mrr = new MatrizRiscoRepositorioJPAImpl();
        LerFicheiros instance = new LerFicheiros();
        Long id = 1L;

        Bootstrapper b = new Bootstrapper();
        b.executeBootstrap();

        MatrizRisco expResult = mrr.findById(id);


        MatrizRisco result = instance.verificarIdMatrizRisco(id);

        System.out.println("");

        assertEquals(expResult, result);
    }

}
