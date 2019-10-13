/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apresentacao;

import Aplicacao.ExportarMatrizBaseVaziaController;
import eapli.framework.util.Console;
import java.io.IOException;

/**
 *
 * @author jmbosg
 */
public class ExportarMatrizBaseVaziaUI {
    
    /**
     * Controller do UC exportar matriz base vazia
     */
    private final ExportarMatrizBaseVaziaController controller = new ExportarMatrizBaseVaziaController();

    /**
     * UI do UC exportar matriz base
     * @throws IOException 
     */
    public void exportarMatrizBaseVazia() throws IOException {

        System.out.println("Exportar Matriz Base Vazia:");

        String caminhoFicheiro = Console.readLine("Insira o destino do ficheiro:");

        controller.exportarMatrizBaseVazia(caminhoFicheiro);

        System.out.println("Matriz exportada com sucesso!");

    }

}
