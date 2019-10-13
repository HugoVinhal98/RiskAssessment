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

/**
 *
 * @author Jmbosg
 */
public class ProcessarAvaliacaoRiscoComDetalhesController {

    /**
     * Controller do UC fazer avaliacao de risco com detalhes
     *
     * @param idMatriz Id da matriz a ser usada na avaliacao
     * @param idPedido Id do pedido a ser usado na avaliacao
     */
    public void fazerAvaliacaoRiscoComDetalhes(Long idMatriz, Long idPedido) {

        PedidoRepositorio prepo = new PedidoRepositorioJPAImpl();

        Pedido p = prepo.findById(idPedido);

        ObjetoSeguro os = p.getObjetoSeguro();

        Object[] scoreMax = AvaliacaoRiscoService.obterScoreMaximo(os, idMatriz);
        Object[] scoreObtido = AvaliacaoRiscoService.obterScoreObtido(os, idMatriz);
        float indiceAvaliacao = AvaliacaoRiscoService.calculaIndiceRiscoAvaliacao((int) scoreObtido[0], (int) scoreMax[0]);

        int scoreO = (int) scoreObtido[0];
        int scoreM = (int) scoreMax[0];

        String stScoreObtido = (String) scoreObtido[1];
        String stScoreMax = (String) scoreMax[1];

        AvaliacaoRisco ar = new AvaliacaoRisco(os, new ScoreObtido(scoreO), new ScoreMaximo(scoreM), new IndiceAvaliacaoRisco(indiceAvaliacao));

        AvaliacaoRiscoRepositorio arepo = new AvaliacaoRiscoRepositorioJPAImpl();

        System.out.println("Detalhes Score Obtido: " + stScoreObtido);
        System.out.println("Detalhes Score Maximo: " + stScoreMax);
        System.out.println("Indice de avaliacao de risco: " + scoreO + "/" + scoreM + "= " + indiceAvaliacao);

        arepo.add(ar);

        prepo.remove(idPedido);

        p.setAvaliacaoRisco(ar);

        prepo.add(p);

    }

}
