/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacao;

import Dominio.EstadoPedido;
import Dominio.ObjetoSeguro;
import Dominio.Pedido;
import Dominio.Prioridade;
import Persistencia.ObjetoSeguroRepositorio;
import Persistencia.ObjetoSeguroRepositorioJPAImpl;
import Persistencia.PedidoRepositorio;
import Persistencia.PedidoRepositorioJPAImpl;
import java.util.List;

/**
 *
 * @author Jmbosg
 */
public class CriarPedidoController {
    
    /**
     * Controller do UC criar pedido
     * @param nomeObjetoSeguro Nome do objeto seguro do pedido
     * @param prioridade Valor da prioridade do pedido
     */
    public void criarPedido(String nomeObjetoSeguro, int prioridade) {

        PedidoRepositorio p = new PedidoRepositorioJPAImpl();
        ObjetoSeguroRepositorio o = new ObjetoSeguroRepositorioJPAImpl();
        ObjetoSeguro objetoSeguroPedido = null;

        List<ObjetoSeguro> oList = o.findAll();

        for (ObjetoSeguro os : oList) {

            if (os.getNomeObjeto().equalsIgnoreCase(nomeObjetoSeguro)) {
                objetoSeguroPedido = os;
            }

        }

        Prioridade prio = new Prioridade(prioridade);
        EstadoPedido estado = new EstadoPedido("nao processado");

        Pedido pedido = new Pedido(objetoSeguroPedido, prio, estado);
        
        p.add(pedido);

    }

}
