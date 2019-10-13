/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacao;

import Dominio.AvaliacaoRisco;
import Dominio.IndiceAvaliacaoRisco;
import Dominio.ObjetoSeguro;
import Dominio.Pedido;
import Dominio.ScoreMaximo;
import Dominio.ScoreObtido;
import Persistencia.AvaliacaoRiscoRepositorio;
import Persistencia.AvaliacaoRiscoRepositorioJPAImpl;
import Persistencia.PedidoRepositorio;
import Persistencia.PedidoRepositorioJPAImpl;
import Utils.AvaliacaoRiscoService;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author GilTrindade
 */
public class ProcessarMultiplosPedidosController {

    /**
     * Fazer a avaliacao de risco sem detalhes as vezes indicadas pelo
     * utilizador
     *
     * @param idMatriz Id da matriz que vai ser utilizada
     * @param pedidos Ids dos pedidos a serem processados
     */
    public void processarMultiplosPedidosController(Long idMatriz, ArrayList<Long> pedidos) {

        AvaliacaoRiscoRepositorio arepo = new AvaliacaoRiscoRepositorioJPAImpl();
        PedidoRepositorio prepo = new PedidoRepositorioJPAImpl();
        AvaliacaoRisco ar = null;
        ArrayList<Pedido> listaPedidos = new ArrayList<>();

        for (int e = 0; e < pedidos.size(); e++) {

            Pedido p = prepo.findById(pedidos.get(e));

            listaPedidos.add(p);

        }
        
        Collections.sort(listaPedidos);

        for (int e = 0; e < pedidos.size(); e++) {

            Pedido p = listaPedidos.get(e);

            ObjetoSeguro os = p.getObjetoSeguro();

            Object[] scoreMax = AvaliacaoRiscoService.obterScoreMaximo(os, idMatriz);
            Object[] scoreObtido = AvaliacaoRiscoService.obterScoreObtido(os, idMatriz);
            float indiceAvaliacao = AvaliacaoRiscoService.calculaIndiceRiscoAvaliacao((int) scoreMax[0], (int) scoreObtido[0]);

            int scoreO = (int) scoreObtido[0];
            int scoreM = (int) scoreMax[0];

            ar = new AvaliacaoRisco(os, new ScoreObtido(scoreO), new ScoreMaximo(scoreM), new IndiceAvaliacaoRisco(indiceAvaliacao));

            arepo.add(ar);

            prepo.remove(p.getId());

            p.setAvaliacaoRisco(ar);

            prepo.add(p);

        }

    }

}
