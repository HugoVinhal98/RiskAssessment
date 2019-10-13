/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apresentacao;

import Aplicacao.ProcessarMultiplosPedidosController;
import eapli.framework.util.Console;
import java.util.ArrayList;

/**
 *
 * @author GilTrindade
 */
public class ProcessarMultiplosPedidosUI {

    /**
     * Controller do UC processar multiplos pedidos
     */
    private final ProcessarMultiplosPedidosController controller = new ProcessarMultiplosPedidosController();
    private ArrayList<Long> pedidos = new ArrayList<>();

    /**
     * Método para iniciar o processamento de vários pedidos
     */
    public void processarMultiplosPedidosUI() {

        Long idMatriz = Console.readLong("Indique o id da matriz de risco que pretende:");

        String idsPedido = Console.readLine("Indique os ids do pedido");

        String[] idsPedidoParcelas = idsPedido.split(",");

        for (String s : idsPedidoParcelas) {
            Long idPedido = Long.valueOf(s);
            pedidos.add(idPedido);
        }

        controller.processarMultiplosPedidosController(idMatriz, pedidos);

    }

}
