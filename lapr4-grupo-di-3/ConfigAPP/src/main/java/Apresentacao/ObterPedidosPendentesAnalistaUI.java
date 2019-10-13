/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apresentacao;

import Aplicacao.ObterPedidosPendentesAnalistaController;
import DTO.ImprimirPedidosAnalistaDTO;
import Dominio.Pedido;
import Persistencia.PedidoRepositorio;
import Persistencia.PedidoRepositorioJPAImpl;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Jmbosg
 */
public class ObterPedidosPendentesAnalistaUI {

    /**
     * Controller do UC Obter Pedidos Pendentes de Analista
     */
    private final ObterPedidosPendentesAnalistaController controller = new ObterPedidosPendentesAnalistaController();

    /**
     * UI do UC Obter Pedidos Pendentes de Analista
     *
     * @param nome Nome de utilizador do analista de risco atualmente logado
     */
    public void obterPedidosPendentesAnalista(String nome) throws Exception {

        List<ImprimirPedidosAnalistaDTO> l1 = controller.obterPedidosPendentesAnalistaController(nome);
        long idPedido = 0;

        System.out.println("Pedidos pendentes do analista " + nome + ":");
        for (ImprimirPedidosAnalistaDTO dto : l1) {
            System.out.println("ID: " + dto.getPedido() + " Data de criação do Pedido: " + dto.getDataCriacaoPedido() + " Tempo decorrido desde atribuição: " + dto.getTempoDecorridoDesdeAtribuicao() + " dias");
        }

        boolean verificacao = false;

        while (!verificacao) {

            System.out.println("Insira o ID de um pedido que queira analisar:");

            Scanner in = new Scanner(System.in);
            idPedido = in.nextLong();

            verificacao = verificaPedidoInserido(l1, idPedido);

        }

        PedidoRepositorio pRepo = new PedidoRepositorioJPAImpl();
        Pedido ped = pRepo.findById(idPedido);

        AnalisarPedidoUI ar04 = new AnalisarPedidoUI();
        ar04.ApresentarDetalhesPedido(ped);
        ar04.mainLoop(ped);

    }

    /**
     * Verifica se o pedido inserido é pertence à lista de pedidos obtidos
     *
     * @param l1 Lista que contem os pedidos validos
     * @param idPedidoInserido Id do pedido inserido
     * @return True se o id inserido for valido, false se o contrario
     */
    public boolean verificaPedidoInserido(List<ImprimirPedidosAnalistaDTO> l1, Long idPedidoInserido) {

        for (ImprimirPedidosAnalistaDTO dto : l1) {
            if (dto.getPedido() == idPedidoInserido) {
                return true;
            }
        }

        return false;

    }

}
