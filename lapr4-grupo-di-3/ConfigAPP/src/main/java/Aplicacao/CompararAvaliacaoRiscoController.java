/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacao;

import Dominio.ObjetoSeguro;
import Dominio.Pedido;
import Persistencia.PedidoRepositorioJPAImpl;
import Utils.AvaliacaoRiscoService;

/**
 *
 * @author Francisco Negrão
 */
public class CompararAvaliacaoRiscoController {

    /**
     * Controller do UC Comparar Avaliacao Risco
     *
     * @param idMatriz1 Id da matriz1 a ser usada para a comparação com a
     * matriz2
     * @param idMatriz2 Id da matriz2 a ser usada para a comparação com a
     * matriz1
     * @param idPedido Id do pedido a ser avaliado
     */
    public void compararAvaliacaoRisco(Long idMatriz1, Long idMatriz2, Long idPedido) {
        PedidoRepositorioJPAImpl pedidoRepo = new PedidoRepositorioJPAImpl();

        Pedido p = pedidoRepo.findById(idPedido);

        ObjetoSeguro os = p.getObjetoSeguro();

        Object[] scoreMax = AvaliacaoRiscoService.obterScoreMaximo(os, idMatriz1);
        Object[] scoreObtido = AvaliacaoRiscoService.obterScoreObtido(os, idMatriz1);
        float indiceAvaliacao = AvaliacaoRiscoService.calculaIndiceRiscoAvaliacao((int) scoreObtido[0], (int) scoreMax[0]);

        int scoreO = (int) scoreObtido[0];
        int scoreM = (int) scoreMax[0];

        Object[] scoreMax2 = AvaliacaoRiscoService.obterScoreMaximo(os, idMatriz2);
        Object[] scoreObtido2 = AvaliacaoRiscoService.obterScoreObtido(os, idMatriz2);
        float indiceAvaliacao2 = AvaliacaoRiscoService.calculaIndiceRiscoAvaliacao((int) scoreObtido2[0], (int) scoreMax2[0]);

        int scoreO2 = (int) scoreObtido2[0];
        int scoreM2 = (int) scoreMax2[0];

        System.out.println("Id Pedido:" + idPedido);

        System.out.println("Id Matriz 1:" + idMatriz1);

        System.out.println("Score Obtido Matriz1:" + scoreO);

        System.out.println("Score Maximo Matriz1:" + scoreM);

        System.out.println("Indice Avaliacao Risco1:" + indiceAvaliacao);

        System.out.println("Id Matriz2:" + idMatriz2);

        System.out.println("Score Obtido Matriz2:" + scoreO2);

        System.out.println("Score Maximo Matriz2:" + scoreM2);

        System.out.println("Indice Avaliacao Risco2:" + indiceAvaliacao2);

    }
}
