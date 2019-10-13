/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apresentacao;

import Bootstrap.Bootstrapper;
import eapli.framework.util.Console;
import java.io.IOException;

/**
 *
 * @author jmbosg
 */
public class MainMenu {

    /**
     * Menu principal do programa
     *
     * @throws IOException
     */
    public static void mainLoop() throws IOException, Exception {
        int opcao = 0;
        do {
            opcao = menu();

            switch (opcao) {

                case 0:
                    System.out.println("Fim ...");
                    break;

                case 1:
                    LerCoberturasFicheiroUI u1 = new LerCoberturasFicheiroUI();
                    u1.lerCoberturaFicheiro();
                    break;

                case 2:
                    LerEnvolventesFicheiroUI u2 = new LerEnvolventesFicheiroUI();
                    u2.lerEnvolventesDeFicheiro();
                    break;

                case 3:
                    LerFatoresRiscoFicheiroUI u3 = new LerFatoresRiscoFicheiroUI();
                    u3.lerFatoresRiscoDeFicheiro();
                    break;

                case 4:
                    ListarCoberturasUI u4 = new ListarCoberturasUI();
                    u4.listarCoberturas();
                    break;

                case 5:
                    ListarEnvolventesUI u5 = new ListarEnvolventesUI();
                    u5.listarEnvolventes();
                    break;

                case 6:
                    ListarFatoresRiscoUI u6 = new ListarFatoresRiscoUI();
                    u6.listarFatoresRisco();
                    break;

                case 7:
                    ExportarMatrizBaseVaziaUI u7 = new ExportarMatrizBaseVaziaUI();
                    u7.exportarMatrizBaseVazia();
                    break;

                case 8:
                    ImportarMatrizBaseUI u8 = new ImportarMatrizBaseUI();
                    u8.lerLinhasBaseFicheiro();
                    break;

                case 9:
                    ImportarMatrizCaracterizadaUI u9 = new ImportarMatrizCaracterizadaUI();
                    u9.lerLinhasCaracterizadaFicheiro();
                    break;

                case 10:
                    ImportarMatrizDetalhadaUI u10 = new ImportarMatrizDetalhadaUI();
                    u10.lerLinhasDetalhadasFicheiro();
                    break;

                case 11:
                    CriarMatrizRiscoUI u11 = new CriarMatrizRiscoUI();
                    u11.criarMatriz();
                    break;

                case 12:
                    PublicarMatrizRiscoUI u12 = new PublicarMatrizRiscoUI();
                    u12.publicarMatriz();
                    break;

                case 13:
                    ListarFatoresNaoExistentesUI u13 = new ListarFatoresNaoExistentesUI();
                    u13.fatoresNaoExistentes();
                    break;

                case 14:
                    Bootstrapper u14 = new Bootstrapper();
                    u14.executeBootstrap();
                    break;

                case 15:
                    LerObjetoSeguroUI u15 = new LerObjetoSeguroUI();
                    u15.lerObjetosJSON();
                    break;

                case 16:
                    ListarCoberturasInexistentesUI u16 = new ListarCoberturasInexistentesUI();
                    u16.coberturasInexistentes();
                    break;

                case 17:
                    CriarPedidoUI u17 = new CriarPedidoUI();
                    u17.criarPedidoUI();
                    break;

                case 18:
                    ProcessarAvaliacaoRiscoSemDetalhesUI u18 = new ProcessarAvaliacaoRiscoSemDetalhesUI();
                    u18.processarAvaliacaoRiscoSemDetalhes();
                    break;

                case 19:
                    ProcessarAvaliacaoRiscoComDetalhesUI u19 = new ProcessarAvaliacaoRiscoComDetalhesUI();
                    u19.processarAvaliacaoRiscoComDetalhesUI();
                    break;

                case 20:
                    ProcessarMultiplosPedidosUI u20 = new ProcessarMultiplosPedidosUI();
                    u20.processarMultiplosPedidosUI();

                case 21:
                    RecaraterizarMatrizRiscoUI u21 = new RecaraterizarMatrizRiscoUI();
                    u21.recaraterizarMatrizRiscoUI();
                    break;

                case 22:
                    ExportarAvaliacaoRiscoUI u22 = new ExportarAvaliacaoRiscoUI();
                    u22.exportarAvaliacaoRiscoUI();
                    break;

                case 23:
                    CompararAvaliacaoRiscoUI u23 = new CompararAvaliacaoRiscoUI();
                    u23.compararAvaliacaoRisco();
                    break;

                case 24:
                    RegistarARUI u24 = new RegistarARUI();
                    u24.registarARUI();
                    break;

                case 25:
                    LoginARUI u25 = new LoginARUI();
                    String email = u25.loginARUI();

                    MenuAnalistaRisco menu = new MenuAnalistaRisco();
                    menu.mainLoop(email);

                    break;
                    
                case 26:
                    ObterLocalizacaoViaEnderecoUI u26 = new ObterLocalizacaoViaEnderecoUI();
                    u26.obterCoordenadasPorEndereco();
                    break;

                case 27:
                    EnriquecerEnderecoPostalUI u27 = new EnriquecerEnderecoPostalUI();
                    u27.obterDados();
                    break;

                case 28:
                    ObterElevacaoViaEnderecoUI u28 = new ObterElevacaoViaEnderecoUI();
                    u28.obterElevacaoViaEndereco();
                    break;
                case 36:
                    RegistarEnvolventeUI u29 = new RegistarEnvolventeUI();
                    u29.registarEnvolvente();
                    break;

                default:
                    break;

            }
        } while (opcao != 0);

    }

    private static int menu() {
        int option = -1;
        System.out.println("");
        System.out.println("=============================");
        System.out.println(" EAPLI_2DI_G3 ");
        System.out.println("=============================\n");
        System.out.println("1. Ler coberturas de ficheiro");
        System.out.println("2. Ler envolventes de ficheiro");
        System.out.println("3. Ler fatores de risco");
        System.out.println("4. Listar todas as coberturas");
        System.out.println("5. Listar todas as envolventes");
        System.out.println("6. Listar todos os fatores de risco");
        System.out.println("7. Gerar ficheiro CSV para definir que fatores de risco"
                + " interessam a cada cobertura");
        System.out.println("8. Importar ficheiro CSV para definir que fatores de risco"
                + " interessam a cada cobertura");
        System.out.println("9. Importar ficheiro CSV com a matriz de risco caraterizada");
        System.out.println("10. Importar ficheiro CSV com a matriz de risco detalhada");
        System.out.println("11. Criar matriz risco");
        System.out.println("12. Publicar matriz de risco");
        System.out.println("13. Listar fatores de risco não existentes na matriz risco");
        System.out.println("14. Bootstrap total da matriz de risco");
        System.out.println("15. Ler objeto seguro a partir de um ficheiro JSON");
        System.out.println("16. Listar coberturas inexistentes na matriz risco");
        System.out.println("17. Criar pedido");
        System.out.println("18. Fazer avaliação de risco sem detalhes (Processar Pedido)");
        System.out.println("19. Fazer avaliação de risco com detalhes (Processar Pedido)");
        System.out.println("20. Processar um conjunto de pedidos");
        System.out.println("21. Recaraterizar matriz de risco já existente");
        System.out.println("22. Exportar uma avaliacao de risco");
        System.out.println("23. Fazer avaliacao de risco com 2 matrizes diferentes e comparar resultados");
        System.out.println("24. Registar um novo Analista de Risco");
        System.out.println("25. Login do Analista de Risco");
        System.out.println("26. Obter coordenadas via endereço postal");
        System.out.println("27. Enriquecer o endereço postal com informação adicional");
        System.out.println("=============================");
        System.out.println("0. Sair\n\n");
        option = Console.readInteger("Por favor escolha opção");
        return option;
    }

}
