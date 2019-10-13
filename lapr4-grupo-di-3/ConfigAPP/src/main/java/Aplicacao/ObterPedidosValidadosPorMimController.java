/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacao;

import Dominio.Pedido;
import Persistencia.PedidoRepositorio;
import Persistencia.PedidoRepositorioJPAImpl;
import java.util.ArrayList;
import java.util.List;
import Utils.DateUtil;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Diogo Rolo
 */
public class ObterPedidosValidadosPorMimController {

    private final PedidoRepositorio pr = new PedidoRepositorioJPAImpl();
    private final Pedido p = new Pedido();
    private AnalisarPedidoC APcontroller = new AnalisarPedidoC();

    public List<Pedido> pedidosValidadosPorMim(String nome) {

        List<Pedido> lista = new ArrayList<>();
        lista = pr.findPedidosProcessadosByMe(nome);
        return lista;
    }

    public void ordenarlista(List<Pedido> lista) {
        p.ordenarCrescente(lista);
    }

    public void apresentarPedidos(List<Pedido> listaPedidos) {

        DateUtil du = new DateUtil();

        for (Pedido p : listaPedidos) {

            System.out.println("Pedido: " + p.getId() + "\tTempo decorrido: " + DateUtil.getAmmountOfTimePassedBetweenTwoDates(p.getDataAtribuicaoAnalista(), p.getDataFinalAtribuicaoAnalista())+" dias");
        }
    }

    public List<Pedido> filtrarPedidos(List<Pedido> listaPedidos, Date dataIni, Date dataFim) throws ParseException {
        List<Pedido> listaFiltrada = new ArrayList<>();
        

        listaFiltrada = p.filtrarPorData(listaPedidos, dataIni, dataFim);

        return listaFiltrada;
    }

    public void apresentarSumario(List<Pedido> listaPedidos) {
        DateUtil du = new DateUtil();
        int cont = listaPedidos.size();
        if(cont == 0){
            System.out.println("Não existem pedidos!");
        }else{
        long tempoTotal = 0;
        tempoTotal = du.getTempoTotal(listaPedidos);
        long tempoMedioPedidos = p.getTempoMedioPedido(cont, tempoTotal);
        System.out.println("Número de pedidos: " + cont + "\t Tempo Medio: " + tempoMedioPedidos+" dias");
    }
    }
            
    public void exportarXHTML(List<Pedido> listaPedidos, String nome) throws IOException {
        APcontroller.exportarInfoPedidosHTML(listaPedidos, nome);
    }

    public List<Pedido> getListaFiltradaPorDatas(String nome, Date dataIni, Date dataFim) {
        
        PedidoRepositorio pr = new PedidoRepositorioJPAImpl();
        
        List<Pedido> lista = pr.findPedidosProcessadosByDatas(nome, dataIni, dataFim);
        
        return lista;
    }

}
