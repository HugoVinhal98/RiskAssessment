/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apresentacao;

import Aplicacao.AnalisarPedidoC;
import Dominio.Cobertura;
import DTO.PedidoBuscarDTO;
import Dominio.Pedido;
import Persistencia.PedidoRepositorio;
import Persistencia.PedidoRepositorioJPAImpl;
import eapli.framework.util.Console;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author franc
 */
public class AnalisarPedidoUI {

    private AnalisarPedidoC APcontroller = new AnalisarPedidoC();

    /**
     * Apresenta todos os detalhes de um pedido de avaliação de risco
     *
     * @param pedido
     */
    public void ApresentarDetalhesPedido(Pedido pedido) {

        PedidoBuscarDTO pDTO = APcontroller.ExportarDetalhesPedido(pedido);

        if (pDTO != null) {
            System.out.println("\nID pedido: " + pDTO.getIdPedido() + "\n");
            System.out.println("ID Avaliação Risco: " + pDTO.getIdAvaliacaoRisco() + "\n");
            System.out.println("Score Obtido: " + pDTO.getSo().getScoreObtido() + "\n");
            System.out.println("Score Máximo: " + pDTO.getSm().getScoreMaximo() + "\n");
            System.out.println("Indice Avaliação Risco: " + pDTO.getIndAR().getIndiceAvalicaoRisco() + "\n");
            System.out.println("ID Objeto Seguro: " + pDTO.getIdObjetoSeguro() + "\n");
            System.out.println("Nome Objeto Seguro: " + pDTO.getNomeOS() + "\n");

            System.out.println("Lista Coberturas:");
            for (String c : pDTO.getListaCoberturasOS()) {
                System.out.println(c);
            }
        } else {
            System.out.println("Pedido não encontrado ou não necessita de Analista de Risco! ");
        }
    }

    /**
     * Para um dado pedido as diferentes opções possiveis quer seja Exportar a
     * Info para um XHTML Confirmar os dados do pedido, Atribuir diretamente os
     * dados ou solicitar uma reavaliação automatica
     *
     * @param ped
     * @throws IOException
     * @throws Exception
     */
    public void mainLoop(Pedido ped) throws IOException, Exception {

        int opcao = 0;
        do {
            opcao = menu();

            switch (opcao) {

                case 0:
                    System.out.println("Fim ...");
                    break;

                case 1:

                    APcontroller.ExportarInfoHTML(ped);
                    System.out.println("Exportado com Sucesso");
                    break;

                case 2:

                    if (ped.getNecessidadeAnalista().equalsIgnoreCase("nao")) {
                        System.out.println("Pedido já foi confirmado. \n");
                        break;
                    }
                    String ocurrencia = Console.readLine("Se desejar insira um comentário/observação");
                    APcontroller.ConfirmarResultados(ped, ocurrencia);
                    System.out.println("Dados confirmados com sucesso \n");
                    break;

                case 3:

                    if (ped.getNecessidadeAnalista().equalsIgnoreCase("nao")) {
                        System.out.println("Pedido já foi confirmado. \n");
                        break;
                    }
                    System.out.println("Insira os novos dados do Pedido: \n");
                    int scoreMax = Console.readInteger("Insira o novo Score Máximo");
                    int scoreObtido = Console.readInteger("Insira o novo Score Obtido");
                    String fundamentacao = Console.readLine("Insira uma fundamentação obrigatória para os seus novos dados introduzidos");
                    APcontroller.AtribuirResultados(ped, scoreMax, scoreObtido, fundamentacao);
                    System.out.println("Dados atribuidos com sucesso \n");
                    break;

                case 4:

                    if (ped.getNecessidadeAnalista().equalsIgnoreCase("nao")) {
                        System.out.println("Pedido já foi confirmado. \n");
                        break;
                    }
                    String novaLista;

                    System.out.println("Lista total de coberturas no sistema");
                    List<Cobertura> lc = APcontroller.listarCoberturas();
                    for (Cobertura c : lc) {
                        System.out.println(c);
                    }
                    System.out.println("Lista de coberturas do pedido: " + ped.getNomePedido());
                    List<String> lcp = APcontroller.listarCoberturasPedido(ped);
                    for (String s : lcp) {
                        System.out.println(s);
                    }

                    int option = Console.readInteger("Pretende alterar a lista de coberturas?(0/1)");
                    if (option == 0) {
                        System.out.println("Caso de uso terminou");
                        break;
                    } else {
                        novaLista = Console.readLine("Insira uma nova lista de coberturas separada por ';' ");
                    }
                    APcontroller.ReavaliacaoAutomatica(ped, novaLista);
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
        System.out.println("1.Exportar informação para um ficheiro XHTML");
        System.out.println("2.Confirmar os resultados do Pedido");
        System.out.println("3.Atribuir diretamente resultados ao Pedido");
        System.out.println("4.Solicitar uma reavaliação automática ao Pedido");
        System.out.println("=============================");
        System.out.println("0. Sair\n\n");
        option = Console.readInteger("Por favor escolha opção");
        return option;
    }

}
