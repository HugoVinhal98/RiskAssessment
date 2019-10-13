/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apresentacao;

import Aplicacao.ExportarAvaliacaoRiscoController;
import eapli.framework.util.Console;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Francisco Negrão
 */
public class ExportarAvaliacaoRiscoUI {

    /**
     * Controller do UC exportar avaliacao de risco
     */
    ExportarAvaliacaoRiscoController controller = new ExportarAvaliacaoRiscoController();
    Scanner in = new Scanner(System.in);

    public void exportarAvaliacaoRiscoUI() throws IOException {

        Long id = Console.readLong("Insira o id de pedido cuja a avaliação de risco vai ser exportada");

        String nomeFicheiro = Console.readLine("Insira o nome do ficheiro para onde a avaliacao de risco vai ser exportada");

        controller.exportarAvaliacaoRisco(id, nomeFicheiro);

    }

}
