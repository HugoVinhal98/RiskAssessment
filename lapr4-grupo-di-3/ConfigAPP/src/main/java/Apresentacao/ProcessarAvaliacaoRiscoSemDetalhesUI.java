/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apresentacao;

import Aplicacao.ProcessarAvaliacaoRiscoSemDetalhesController;
import eapli.framework.util.Console;

/**
 *
 * @author franc
 */
public class ProcessarAvaliacaoRiscoSemDetalhesUI {
    
    /**
     * Controller do UC processar avaliacao de risco sem detalhes
     */
    private final ProcessarAvaliacaoRiscoSemDetalhesController controller = new ProcessarAvaliacaoRiscoSemDetalhesController();
    
    /**
     * UI do UC fazer avaliacao de risco sem detalhes
     */
    public void processarAvaliacaoRiscoSemDetalhes() {

        Long idMatriz = Console.readLong("Indique o id da matriz de risco que pretende:");

        Long idPedido = Console.readLong("Indique o id do pedido");

        controller.fazerAvaliacaoRiscoSemDetalhes(idMatriz, idPedido);

    }
}
