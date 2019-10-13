/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apresentacao;

import Aplicacao.ProcessarAvaliacaoRiscoComDetalhesController;
import eapli.framework.util.Console;

/**
 *
 * @author Jmbosg
 */
public class ProcessarAvaliacaoRiscoComDetalhesUI {
    
    /**
     * Controller do UC processar avaliacao de risco com detalhes
     */
    private final ProcessarAvaliacaoRiscoComDetalhesController controller = new ProcessarAvaliacaoRiscoComDetalhesController();
    
    /**
     * UI do UC fazer avaliacao de risco com detalhes
     */
    public void processarAvaliacaoRiscoComDetalhesUI() {

        Long idMatriz = Console.readLong("Indique o id da matriz de risco que pretende:");

        Long idPedido = Console.readLong("Indique o id do pedido:");

        controller.fazerAvaliacaoRiscoComDetalhes(idMatriz, idPedido);

    }

}
