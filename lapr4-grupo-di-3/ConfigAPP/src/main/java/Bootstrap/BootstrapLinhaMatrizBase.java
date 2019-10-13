/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bootstrap;

import Dominio.*;
import Dominio.LinhaMatrizBase;
import Persistencia.*;
import java.util.List;

/**
 *
 * @author Francisco Negrão
 */
public class BootstrapLinhaMatrizBase {

    public void registerLinhasMatrizBase() {

        final LinhaMatrizBaseRepositorio mbr = new LinhaMatrizBaseRepositorioJPAImpl();
        final CoberturaRepositorio c = new CoberturaRepositorioJPAImpl();
        final FatorDeRiscoRepositorio f = new FatorDeRiscoRepositorioJPAImpl();
        
        List<Cobertura> coberturaList = c.findAll();
        List<FatorRisco> fatorRiscoList = f.findAll();
        long idMatriz = 1;

        final LinhaMatrizBase linhaMatrizBase = new LinhaMatrizBase(coberturaList.get(0), fatorRiscoList.get(0), "nao", idMatriz);
        final LinhaMatrizBase linhaMatrizBase2 = new LinhaMatrizBase(coberturaList.get(0), fatorRiscoList.get(1), "sim", idMatriz);
        final LinhaMatrizBase linhaMatrizBase3 = new LinhaMatrizBase(coberturaList.get(0), fatorRiscoList.get(2), "nao", idMatriz);
        final LinhaMatrizBase linhaMatrizBase4 = new LinhaMatrizBase(coberturaList.get(0), fatorRiscoList.get(3), "sim", idMatriz);
        final LinhaMatrizBase linhaMatrizBase5 = new LinhaMatrizBase(coberturaList.get(0), fatorRiscoList.get(4), "sim", idMatriz);
        final LinhaMatrizBase linhaMatrizBase6 = new LinhaMatrizBase(coberturaList.get(1), fatorRiscoList.get(0), "sim", idMatriz);
        final LinhaMatrizBase linhaMatrizBase7 = new LinhaMatrizBase(coberturaList.get(1), fatorRiscoList.get(1), "nao", idMatriz);
        final LinhaMatrizBase linhaMatrizBase8 = new LinhaMatrizBase(coberturaList.get(1), fatorRiscoList.get(2), "sim", idMatriz);
        final LinhaMatrizBase linhaMatrizBase9 = new LinhaMatrizBase(coberturaList.get(1), fatorRiscoList.get(3), "sim", idMatriz);
        final LinhaMatrizBase linhaMatrizBase10 = new LinhaMatrizBase(coberturaList.get(1), fatorRiscoList.get(4), "sim", idMatriz);
        final LinhaMatrizBase linhaMatrizBase11 = new LinhaMatrizBase(coberturaList.get(2), fatorRiscoList.get(0), "nao", idMatriz);
        final LinhaMatrizBase linhaMatrizBase12 = new LinhaMatrizBase(coberturaList.get(2), fatorRiscoList.get(1), "sim", idMatriz);
        final LinhaMatrizBase linhaMatrizBase13 = new LinhaMatrizBase(coberturaList.get(2), fatorRiscoList.get(2), "sim", idMatriz);
        final LinhaMatrizBase linhaMatrizBase14 = new LinhaMatrizBase(coberturaList.get(2), fatorRiscoList.get(3), "sim", idMatriz);
        final LinhaMatrizBase linhaMatrizBase15 = new LinhaMatrizBase(coberturaList.get(2), fatorRiscoList.get(4), "sim", idMatriz);
        final LinhaMatrizBase linhaMatrizBase16 = new LinhaMatrizBase(coberturaList.get(3), fatorRiscoList.get(0), "sim", idMatriz);
        final LinhaMatrizBase linhaMatrizBase17 = new LinhaMatrizBase(coberturaList.get(3), fatorRiscoList.get(1), "nao", idMatriz);
        final LinhaMatrizBase linhaMatrizBase18 = new LinhaMatrizBase(coberturaList.get(3), fatorRiscoList.get(2), "sim", idMatriz);
        final LinhaMatrizBase linhaMatrizBase19 = new LinhaMatrizBase(coberturaList.get(3), fatorRiscoList.get(3), "sim", idMatriz);
        final LinhaMatrizBase linhaMatrizBase20 = new LinhaMatrizBase(coberturaList.get(3), fatorRiscoList.get(4), "sim", idMatriz);
        final LinhaMatrizBase linhaMatrizBase21 = new LinhaMatrizBase(coberturaList.get(4), fatorRiscoList.get(0), "sim", idMatriz);
        final LinhaMatrizBase linhaMatrizBase22 = new LinhaMatrizBase(coberturaList.get(4), fatorRiscoList.get(1), "sim", idMatriz);
        final LinhaMatrizBase linhaMatrizBase23 = new LinhaMatrizBase(coberturaList.get(4), fatorRiscoList.get(2), "nao", idMatriz);
        final LinhaMatrizBase linhaMatrizBase24 = new LinhaMatrizBase(coberturaList.get(4), fatorRiscoList.get(3), "sim", idMatriz);
        final LinhaMatrizBase linhaMatrizBase25 = new LinhaMatrizBase(coberturaList.get(4), fatorRiscoList.get(4), "nao", idMatriz);
        final LinhaMatrizBase linhaMatrizBase26 = new LinhaMatrizBase(coberturaList.get(5), fatorRiscoList.get(0), "nao", idMatriz);
        final LinhaMatrizBase linhaMatrizBase27 = new LinhaMatrizBase(coberturaList.get(5), fatorRiscoList.get(1), "sim", idMatriz);
        final LinhaMatrizBase linhaMatrizBase28 = new LinhaMatrizBase(coberturaList.get(5), fatorRiscoList.get(2), "sim", idMatriz);
        final LinhaMatrizBase linhaMatrizBase29 = new LinhaMatrizBase(coberturaList.get(5), fatorRiscoList.get(3), "nao", idMatriz);
        final LinhaMatrizBase linhaMatrizBase30 = new LinhaMatrizBase(coberturaList.get(5), fatorRiscoList.get(4), "sim", idMatriz);

        mbr.add(linhaMatrizBase);
        mbr.add(linhaMatrizBase2);
        mbr.add(linhaMatrizBase3);
        mbr.add(linhaMatrizBase4);
        mbr.add(linhaMatrizBase5);
        mbr.add(linhaMatrizBase6);
        mbr.add(linhaMatrizBase7);
        mbr.add(linhaMatrizBase8);
        mbr.add(linhaMatrizBase9);
        mbr.add(linhaMatrizBase10);
        mbr.add(linhaMatrizBase11);
        mbr.add(linhaMatrizBase12);
        mbr.add(linhaMatrizBase13);
        mbr.add(linhaMatrizBase14);
        mbr.add(linhaMatrizBase15);
        mbr.add(linhaMatrizBase16);
        mbr.add(linhaMatrizBase17);
        mbr.add(linhaMatrizBase18);
        mbr.add(linhaMatrizBase19);
        mbr.add(linhaMatrizBase20);
        mbr.add(linhaMatrizBase21);
        mbr.add(linhaMatrizBase22);
        mbr.add(linhaMatrizBase23);
        mbr.add(linhaMatrizBase24);
        mbr.add(linhaMatrizBase25);
        mbr.add(linhaMatrizBase26);
        mbr.add(linhaMatrizBase27);
        mbr.add(linhaMatrizBase28);
        mbr.add(linhaMatrizBase29);
        mbr.add(linhaMatrizBase30);

    }
}
