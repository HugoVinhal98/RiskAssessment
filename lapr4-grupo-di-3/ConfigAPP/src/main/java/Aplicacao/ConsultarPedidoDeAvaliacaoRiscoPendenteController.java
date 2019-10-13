/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacao;

import Dominio.Pedido;
import eapli.framework.util.Console;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Francisco Negrão
 */
public class ConsultarPedidoDeAvaliacaoRiscoPendenteController {

    /**
     * Método para listar os pedidos pendentes.
     * @return
     */
    public List<Pedido> pedidosPendentesAvaliacao() {
     List<Pedido> pedidos = new ArrayList<Pedido>();
      Pedido p = new Pedido();
      return pedidos = p.pedidosPendentesAvaliacao();
      
  }
  
    /**
     *Método para listar os pedidos de um determinado distrito.
     * @param cidade
     * @return
     */
    public List<Pedido> pedidosPendentesAvaliacaoLocalizacao(String cidade) {
       List<Pedido> pedidos = new ArrayList<Pedido>();
      Pedido p = new Pedido();
      return pedidos = p.pedidosCidade(cidade);
      
  }

    /**
     *Método para listar os pedidos ordenados.
     * @return
     */
    public List<Pedido> pedidosPendentesAvaliacaoOrdenados() {
        Pedido p = new Pedido();
         List<Pedido> pedidos = pedidosPendentesAvaliacao();
         return p.ordenar(pedidos);
         
    }
}
 

    