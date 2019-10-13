/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bootstrap;

import Dominio.*;
import Persistencia.*;
import java.util.List;

/**
 *
 * @author Francisco Negrão
 */
public class BootstrapLinhaMatrizDetalhada {

    public void registerLinhasMatrizDetalhada() {

        final LinhaMatrizCaraterizadaRepositorio mcr = new LinhaMatrizCaraterizadaRepositorioJPAImpl();
        final LinhaMatrizDetalhadaRepositorio mdr = new LinhaMatrizDetalhadaRepositorioJPAImpl();

        List<LinhaMatrizCaraterizada> listMcr = mcr.findAll();

        long idMatriz = 1;

        final LinhaMatrizDetalhada linhaMatrizDetalhada = new LinhaMatrizDetalhada(listMcr.get(0), new EscalaRisco(2, 4, 4), idMatriz);
        final LinhaMatrizDetalhada linhaMatrizDetalhada1 = new LinhaMatrizDetalhada(listMcr.get(1), new EscalaRisco(2, 3, 4), idMatriz);
        final LinhaMatrizDetalhada linhaMatrizDetalhada2 = new LinhaMatrizDetalhada(listMcr.get(2), new EscalaRisco(3, 4, 5), idMatriz);
        final LinhaMatrizDetalhada linhaMatrizDetalhada3 = new LinhaMatrizDetalhada(listMcr.get(3), new EscalaRisco(2, 3, 3), idMatriz);
        final LinhaMatrizDetalhada linhaMatrizDetalhada4 = new LinhaMatrizDetalhada(listMcr.get(4), new EscalaRisco(2, 3, 5), idMatriz);
        final LinhaMatrizDetalhada linhaMatrizDetalhada5 = new LinhaMatrizDetalhada(listMcr.get(5), new EscalaRisco(3, 4, 4), idMatriz);
        final LinhaMatrizDetalhada linhaMatrizDetalhada6 = new LinhaMatrizDetalhada(listMcr.get(6), new EscalaRisco(2, 4, 5), idMatriz);
        final LinhaMatrizDetalhada linhaMatrizDetalhada7 = new LinhaMatrizDetalhada(listMcr.get(7), new EscalaRisco(4, 4, 5), idMatriz);
        final LinhaMatrizDetalhada linhaMatrizDetalhada8 = new LinhaMatrizDetalhada(listMcr.get(8), new EscalaRisco(3, 4, 5), idMatriz);
        final LinhaMatrizDetalhada linhaMatrizDetalhada9 = new LinhaMatrizDetalhada(listMcr.get(9), new EscalaRisco(2, 3, 4), idMatriz);
        final LinhaMatrizDetalhada linhaMatrizDetalhada10 = new LinhaMatrizDetalhada(listMcr.get(10), new EscalaRisco(1, 3, 4), idMatriz);
        final LinhaMatrizDetalhada linhaMatrizDetalhada11 = new LinhaMatrizDetalhada(listMcr.get(11), new EscalaRisco(2, 3, 3), idMatriz);
        final LinhaMatrizDetalhada linhaMatrizDetalhada12 = new LinhaMatrizDetalhada(listMcr.get(12), new EscalaRisco(2, 3, 5), idMatriz);
        final LinhaMatrizDetalhada linhaMatrizDetalhada13 = new LinhaMatrizDetalhada(listMcr.get(13), new EscalaRisco(2, 4, 5), idMatriz);
        final LinhaMatrizDetalhada linhaMatrizDetalhada14 = new LinhaMatrizDetalhada(listMcr.get(14), new EscalaRisco(2, 3, 3), idMatriz);
        final LinhaMatrizDetalhada linhaMatrizDetalhada15 = new LinhaMatrizDetalhada(listMcr.get(15), new EscalaRisco(2, 4, 5), idMatriz);
        final LinhaMatrizDetalhada linhaMatrizDetalhada16 = new LinhaMatrizDetalhada(listMcr.get(16), new EscalaRisco(2, 3, 5), idMatriz);
        final LinhaMatrizDetalhada linhaMatrizDetalhada17 = new LinhaMatrizDetalhada(listMcr.get(17), new EscalaRisco(2, 5, 5), idMatriz);
        final LinhaMatrizDetalhada linhaMatrizDetalhada18 = new LinhaMatrizDetalhada(listMcr.get(18), new EscalaRisco(3, 5, 5), idMatriz);
        final LinhaMatrizDetalhada linhaMatrizDetalhada19 = new LinhaMatrizDetalhada(listMcr.get(19), new EscalaRisco(2, 4, 4), idMatriz);
        final LinhaMatrizDetalhada linhaMatrizDetalhada20 = new LinhaMatrizDetalhada(listMcr.get(20), new EscalaRisco(5, 5, 5), idMatriz);

        mdr.add(linhaMatrizDetalhada);
        mdr.add(linhaMatrizDetalhada1);
        mdr.add(linhaMatrizDetalhada2);
        mdr.add(linhaMatrizDetalhada3);
        mdr.add(linhaMatrizDetalhada4);
        mdr.add(linhaMatrizDetalhada5);
        mdr.add(linhaMatrizDetalhada6);
        mdr.add(linhaMatrizDetalhada7);
        mdr.add(linhaMatrizDetalhada8);
        mdr.add(linhaMatrizDetalhada9);
        mdr.add(linhaMatrizDetalhada10);
        mdr.add(linhaMatrizDetalhada11);
        mdr.add(linhaMatrizDetalhada12);
        mdr.add(linhaMatrizDetalhada13);
        mdr.add(linhaMatrizDetalhada14);
        mdr.add(linhaMatrizDetalhada15);
        mdr.add(linhaMatrizDetalhada16);
        mdr.add(linhaMatrizDetalhada17);
        mdr.add(linhaMatrizDetalhada18);
        mdr.add(linhaMatrizDetalhada19);
        mdr.add(linhaMatrizDetalhada20);

        long idMatriz2 = 2;

        final LinhaMatrizDetalhada linhaMatrizDetalhada110 = new LinhaMatrizDetalhada(listMcr.get(0), new EscalaRisco(1, 1, 1), idMatriz2);
        final LinhaMatrizDetalhada linhaMatrizDetalhada1111 = new LinhaMatrizDetalhada(listMcr.get(1), new EscalaRisco(1, 2, 3), idMatriz2);
        final LinhaMatrizDetalhada linhaMatrizDetalhada21 = new LinhaMatrizDetalhada(listMcr.get(2), new EscalaRisco(1, 2, 3), idMatriz2);
        final LinhaMatrizDetalhada linhaMatrizDetalhada31 = new LinhaMatrizDetalhada(listMcr.get(3), new EscalaRisco(2, 3, 4), idMatriz2);
        final LinhaMatrizDetalhada linhaMatrizDetalhada41 = new LinhaMatrizDetalhada(listMcr.get(4), new EscalaRisco(2, 4, 5), idMatriz2);
        final LinhaMatrizDetalhada linhaMatrizDetalhada51 = new LinhaMatrizDetalhada(listMcr.get(5), new EscalaRisco(4, 1, 1), idMatriz2);
        final LinhaMatrizDetalhada linhaMatrizDetalhada61 = new LinhaMatrizDetalhada(listMcr.get(6), new EscalaRisco(1, 1, 3), idMatriz2);
        final LinhaMatrizDetalhada linhaMatrizDetalhada71 = new LinhaMatrizDetalhada(listMcr.get(7), new EscalaRisco(1, 3, 4), idMatriz2);
        final LinhaMatrizDetalhada linhaMatrizDetalhada81 = new LinhaMatrizDetalhada(listMcr.get(8), new EscalaRisco(2, 3, 4), idMatriz2);
        final LinhaMatrizDetalhada linhaMatrizDetalhada91 = new LinhaMatrizDetalhada(listMcr.get(9), new EscalaRisco(1, 2, 3), idMatriz2);
        final LinhaMatrizDetalhada linhaMatrizDetalhada101 = new LinhaMatrizDetalhada(listMcr.get(10), new EscalaRisco(3, 4, 5), idMatriz2);
        final LinhaMatrizDetalhada linhaMatrizDetalhada111 = new LinhaMatrizDetalhada(listMcr.get(11), new EscalaRisco(2, 4, 4), idMatriz2);
        final LinhaMatrizDetalhada linhaMatrizDetalhada121 = new LinhaMatrizDetalhada(listMcr.get(12), new EscalaRisco(4, 4, 4), idMatriz2);
        final LinhaMatrizDetalhada linhaMatrizDetalhada131 = new LinhaMatrizDetalhada(listMcr.get(13), new EscalaRisco(1, 3, 5), idMatriz2);
        final LinhaMatrizDetalhada linhaMatrizDetalhada141 = new LinhaMatrizDetalhada(listMcr.get(14), new EscalaRisco(1, 3, 4), idMatriz2);
        final LinhaMatrizDetalhada linhaMatrizDetalhada151 = new LinhaMatrizDetalhada(listMcr.get(15), new EscalaRisco(1, 2, 3), idMatriz2);
        final LinhaMatrizDetalhada linhaMatrizDetalhada161 = new LinhaMatrizDetalhada(listMcr.get(16), new EscalaRisco(1, 1, 3), idMatriz2);
        final LinhaMatrizDetalhada linhaMatrizDetalhada171 = new LinhaMatrizDetalhada(listMcr.get(17), new EscalaRisco(1, 1, 3), idMatriz2);
        final LinhaMatrizDetalhada linhaMatrizDetalhada181 = new LinhaMatrizDetalhada(listMcr.get(18), new EscalaRisco(1, 1, 1), idMatriz2);
        final LinhaMatrizDetalhada linhaMatrizDetalhada191 = new LinhaMatrizDetalhada(listMcr.get(19), new EscalaRisco(2, 3, 5), idMatriz2);
        final LinhaMatrizDetalhada linhaMatrizDetalhada201 = new LinhaMatrizDetalhada(listMcr.get(20), new EscalaRisco(1, 3, 4), idMatriz2);

        mdr.add(linhaMatrizDetalhada110);
        mdr.add(linhaMatrizDetalhada1111);
        mdr.add(linhaMatrizDetalhada21);
        mdr.add(linhaMatrizDetalhada31);
        mdr.add(linhaMatrizDetalhada41);
        mdr.add(linhaMatrizDetalhada51);
        mdr.add(linhaMatrizDetalhada61);
        mdr.add(linhaMatrizDetalhada71);
        mdr.add(linhaMatrizDetalhada81);
        mdr.add(linhaMatrizDetalhada91);
        mdr.add(linhaMatrizDetalhada101);
        mdr.add(linhaMatrizDetalhada111);
        mdr.add(linhaMatrizDetalhada121);
        mdr.add(linhaMatrizDetalhada131);
        mdr.add(linhaMatrizDetalhada141);
        mdr.add(linhaMatrizDetalhada151);
        mdr.add(linhaMatrizDetalhada161);
        mdr.add(linhaMatrizDetalhada171);
        mdr.add(linhaMatrizDetalhada181);
        mdr.add(linhaMatrizDetalhada191);
        mdr.add(linhaMatrizDetalhada201);

    }
}
