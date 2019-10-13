/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apresentacao;

import Aplicacao.ObterPedidosValidadosPorMimController;
import Dominio.Pedido;
import eapli.framework.util.Console;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Diogo Rolo
 */
class ObterPedidosValidadosPorMimUI {

    private final ObterPedidosValidadosPorMimController controller = new ObterPedidosValidadosPorMimController();

    void obterPedidosValidadosPorMim(String nome) throws Exception {
        List<Pedido> lista = new ArrayList<>();
        lista = controller.pedidosValidadosPorMim(nome);

        //se a lista estiver vazia sai do menu
        if (lista.isEmpty()) {
            System.out.println("Não existem pedidos validados por este analista de risco!");
        } else {
            controller.ordenarlista(lista);
            mainLoop(lista, nome);
        }
        //System.out.println(lista);

    }

    void filtrarPedidosUI(List<Pedido> listaPedidos, String nome) throws ParseException {
        List<Pedido> listaFiltrada = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        

        System.out.println("\n\nFiltrar edidos por periodo de tempo:\n\n");

        System.out.println("Qual a data inicial? (YYYY-MM-DD)");
        String dataIni = in.nextLine();
        dataIni.trim();

        System.out.println("Qual a data Final? (YYYY-MM-DD)");
        String dataFim = in.nextLine();
        dataFim.trim();

        if (dataIni.length() != 10 || dataFim.length() != 10) {
            System.out.println("As datas inseridas são inválidas!");
        } else {
            Date dataInicial = sdf.parse(dataIni);
            Date dataFinal = sdf.parse(dataFim);

            if (dataFinal.before(dataInicial)) {
                System.out.println("Datas inseridas são inválidas!");
            } else {

                listaFiltrada = controller.getListaFiltradaPorDatas(nome, dataInicial, dataFinal);
                controller.ordenarlista(listaFiltrada);
                //se a lista estiver vazia sai do menu
                if (listaFiltrada.isEmpty()) {
                    System.out.println("Não existem pedidos para esse periodo!");
                } else {
                    System.out.println("\nPedidos que se encontram dentro do periodo inserido");
                    for (Pedido p : listaFiltrada) {
                        System.out.println(p.getId());
                    }
                }
            }
            //System.out.println(lista);

        }
    }

    /**
     * Analista pode pretender que os pedidos sejam filtrados por um determinado
     * periodo de tempo ou prenteder exportar o resultado da consulta(sumario
     * incluido) para um documento XHTML
     *
     * @param ped
     * @throws IOException
     * @throws Exception
     */
    public void mainLoop(List<Pedido> listaPedidos, String nome) throws IOException, Exception {

        int opcao = 0;
        do {
            opcao = menu();

            switch (opcao) {

                case 0:
                    System.out.println("Fim ...");
                    break;

                case 1:
                    System.out.println("\nPedidos validados por mim:\n");
                    controller.apresentarPedidos(listaPedidos);
                    break;
                case 2:
                    System.out.println("\nFiltrar edidos por periodo de tempo:\n");
                    filtrarPedidosUI(listaPedidos, nome);
                    break;
                case 3:
                    System.out.println("\nSumário:\n");
                    controller.apresentarSumario(listaPedidos);
                    break;
                case 4:
                    System.out.println("\nExportando XHTML...\n");
                    controller.exportarXHTML(listaPedidos, nome);
                    System.out.println("\nXHTML exportado!\n");
                    break;
                default:
                    break;

            }
        } while (opcao != 0);

    }

    public static int menu() {
        int option = -1;
        System.out.println("");
        System.out.println("=============================");
        System.out.println(" Menu ");
        System.out.println("=============================\n");
        System.out.println("1.Apresentar pedidos ordenados e respetivos tempos decorridos");
        System.out.println("2.Filtrar pedidos para um determinado periodo de tempo");
        System.out.println("3.Apresentar sumário dos pedidos");
        System.out.println("4.Exportar informação para um ficheiro XHTML");
        System.out.println("=============================");
        System.out.println("0. Sair\n\n");
        option = Console.readInteger("Por favor escolha opção");
        return option;
    }

}
