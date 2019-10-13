/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apresentacao;

import eapli.framework.util.Console;
import java.io.IOException;

/**
 *
 * @author Francisco Negrão
 */
public class MenuAnalistaRisco {

    public void mainLoop(String nome) throws IOException, Exception {

        int opcao = 0;
        do {
            opcao = menu();

            switch (opcao) {

                case 0:
                    System.out.println("Fim ...");
                    break;

                case 1:
                    ConsultarPedidoAvaliacaoRiscoPendenteUI ar1 = new ConsultarPedidoAvaliacaoRiscoPendenteUI();
                    ar1.consultarPedidoAvaliacaoRiscoPendenteOrdenados();
                    break;
                case 2:
                    ConsultarPedidoAvaliacaoRiscoPendenteUI ar2 = new ConsultarPedidoAvaliacaoRiscoPendenteUI();
                    ar2.consultarPedidoAvaliacaoRiscoPendenteLocalizacao();
                    break;

                case 3:
                    ConsultarPedidoAvaliacaoRiscoPendenteUI ar3 = new ConsultarPedidoAvaliacaoRiscoPendenteUI();
                    ar3.atribuirPedidoAAnalista();
                    break;
                case 4:
                    ObterPedidosPendentesAnalistaUI ar4 = new ObterPedidosPendentesAnalistaUI();
                    ar4.obterPedidosPendentesAnalista(nome);
                    break;
                case 5:
                    ObterPedidosValidadosPorMimUI ar5 = new ObterPedidosValidadosPorMimUI();
                    ar5.obterPedidosValidadosPorMim(nome);  
                    break;
                case 6:
                    RegistarEnvolventeUI u29 = new RegistarEnvolventeUI();
                    u29.registarEnvolvente();
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
        System.out.println(" Menu com Funcionalidades Analista Risco ");
        System.out.println("=============================\n");
        System.out.println("1. Consultar Pedido de Avaliação de Risco pendente por ordem");
        System.out.println("2. Consultar Pedidos de Avaliação de Risco pendente por Cidade");
        System.out.println("3. Atribuir um Analista a um pedido");
        System.out.println("4. Consultar os meus pedidos de avaliação");
        System.out.println("5. Consultar pedidos de avaliação processados por mim");
        System.out.println("6. Registar a existência de uma envolvente de um determinado tipo numa determinada localização");
        System.out.println("=============================");
        System.out.println("0. Sair\n\n");
        option = Console.readInteger("Por favor escolha opção");
        return option;
    }

}
