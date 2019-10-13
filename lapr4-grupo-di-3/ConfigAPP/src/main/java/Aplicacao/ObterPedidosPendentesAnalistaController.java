/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacao;

import DTO.ImprimirPedidosAnalistaDTO;
import Dominio.Pedido;
import Persistencia.PedidoRepositorio;
import Persistencia.PedidoRepositorioJPAImpl;
import Utils.DateUtil;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jmbosg
 */
public class ObterPedidosPendentesAnalistaController {

    /**
     * Controller do UC Obter Pedidos Pendentes Analista
     *
     * @param nome Nome do utilizador do analista logado
     * @return
     */
    public List<ImprimirPedidosAnalistaDTO> obterPedidosPendentesAnalistaController(String nome) {

        List<Pedido> listaPedidos = obterListaOrdenadaPedidosPendentesAnalista(nome);

        List<ImprimirPedidosAnalistaDTO> listaDTO = new ArrayList<>();

        for (Pedido p : listaPedidos) {
            Long pedido = p.getId();
            String tempoDesdeAtribuicao = DateUtil.getAmmountOfTimePassedBetweenTwoDates(p.getDataAtribuicaoAnalista(), new Date());
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String dataCriacaoPedido = dateFormat.format(p.getDataPedido());

            listaDTO.add(new ImprimirPedidosAnalistaDTO(pedido, tempoDesdeAtribuicao, dataCriacaoPedido));

        }

        return listaDTO;

    }

    /**
     * Retorna uma lista ordenada por ordem de data de atribuição com os pedidos
     * pendentes de um analista
     *
     * @param nome Nome do analista em questão
     * @return Lista com os pedidos pendentes de um analista ordenada
     */
    public List<Pedido> obterListaOrdenadaPedidosPendentesAnalista(String nome) {

        PedidoRepositorio pr = new PedidoRepositorioJPAImpl();
        List<Pedido> lista = pr.findPedidosPendentesByAnalista(nome);

        Pedido.ordenarListaPedidosPorData(lista);

        return lista;

    }

}
