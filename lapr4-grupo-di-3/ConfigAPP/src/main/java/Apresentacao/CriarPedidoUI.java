/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apresentacao;

import Aplicacao.CriarPedidoController;
import eapli.framework.util.Console;

/**
 *
 * @author Jmbosg
 */
public class CriarPedidoUI {

    /**
     * Controller do UC criar pedido
     */
    private final CriarPedidoController controller = new CriarPedidoController();
    
    /**
     * UI do UC criar pedido
     */
    public void criarPedidoUI() {

        String nomeObjetoSeguro = Console.readLine("Insira o nome do objeto seguro para o qual pretende criar pedido:");
        
        int prioridade = Console.readInteger("Insira o valor da prioridade deste pedido:");

        controller.criarPedido(nomeObjetoSeguro, prioridade);

    }

}
