/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacao;

import Dominio.Contribuicao;
import Dominio.LinhaMatrizCaraterizada;
import Dominio.LinhaMatrizDetalhada;
import Dominio.MatrizRisco;
import Dominio.Necessidade;
import Dominio.Peso;
import Persistencia.LinhaMatrizCaraterizadaRepositorio;
import Persistencia.LinhaMatrizCaraterizadaRepositorioJPAImpl;
import Persistencia.LinhaMatrizDetalhadaRepositorio;
import Persistencia.LinhaMatrizDetalhadaRepositorioJPAImpl;
import Persistencia.MatrizRiscoRepositorio;
import Persistencia.MatrizRiscoRepositorioJPAImpl;
import Utils.LerFicheiros;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Jmbosg
 */
public class RecaraterizarMatrizRiscoController {

    /**
     * Controller do UC recaraterizar matriz
     *
     * @param idMatriz Id da matriz a recaraterizar
     * @param nomeFicheiro1 Nome do ficheiro que possui os pesos
     * @param nomeFicheiro2 Nome do ficheiro que possui as necessidades
     * @param nomeFicheiro3 Nome do ficheiro que possui as contribuicoes
     * @throws IOException
     */
    public void recaraterizarMatrizRiscoController(Long idMatriz, String nomeFicheiro1, String nomeFicheiro2, String nomeFicheiro3) throws IOException {

        MatrizRiscoRepositorio mrRepo = new MatrizRiscoRepositorioJPAImpl();

        LinhaMatrizCaraterizadaRepositorio lmcRepo = new LinhaMatrizCaraterizadaRepositorioJPAImpl();

        LinhaMatrizDetalhadaRepositorio lmdRepo = new LinhaMatrizDetalhadaRepositorioJPAImpl();

        MatrizRisco mr = mrRepo.findById(idMatriz);

        Long idLinhas = mr.getLinhasMatrizDetalhada().get(0).getIdMatriz();

        List<LinhaMatrizCaraterizada> l1 = LerFicheiros.recaraterizarLinhasMatrizCaracterizada(nomeFicheiro1, nomeFicheiro2, nomeFicheiro3, idLinhas);

        List<LinhaMatrizDetalhada> l2 = mr.getLinhasMatrizDetalhada();

        for (int i = 0; i < l1.size(); i++) {

            Peso p = l1.get(i).getPeso();

            l2.get(i).getLinhaMatrizCaraterizada().setPeso(p);

            Necessidade n = l1.get(i).getNecessidade();

            l2.get(i).getLinhaMatrizCaraterizada().setNecessidade(n);

            Contribuicao c = l1.get(i).getContribuicao();

            l2.get(i).getLinhaMatrizCaraterizada().setContribuicao(c);

        }

        List<LinhaMatrizCaraterizada> l3 = lmcRepo.findAll();

        List<LinhaMatrizDetalhada> l4 = lmdRepo.findAll();

        for (int i = 0; i < l3.size(); i++) {

            if (Objects.equals(l3.get(i).getIdMatriz(), idLinhas)) {

                lmdRepo.remove(l4.get(i).getId());

            }

        }

        for (int i = 0; i < l4.size(); i++) {

            if (Objects.equals(l4.get(i).getIdMatriz(), idLinhas)) {

                lmcRepo.remove(l4.get(i).getId());

            }

        }

        for (int i = 0; i < l3.size(); i++) {

            lmcRepo.add(l2.get(i).getLinhaMatrizCaraterizada());

        }

        for (int i = 0; i < l3.size(); i++) {

            lmdRepo.add(l2.get(i));

        }

        mr.setLinhasMatrizDetalhada(l2);

        mrRepo.remove(idMatriz);

        mrRepo.add(mr);

    }

}
