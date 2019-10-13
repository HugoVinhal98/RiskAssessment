/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apresentacao;

import Aplicacao.LerCoberturasFicheiroController;

import eapli.framework.util.Console;
import java.io.IOException;

/**
 *
 * @author franc
 */
public class LerCoberturasFicheiroUI {
    
    /**
     * Controller do UC importar coberturas de ficheiro
     */
    private final LerCoberturasFicheiroController controller = new LerCoberturasFicheiroController();

    /**
     * UI do UC ler coberturas de ficheiro
     * @throws IOException 
     */
    public void lerCoberturaFicheiro() throws IOException {

        System.out.println("Ler Coberturas de ficheiro");

        String caminhoFicheiro = Console.readLine("Insira caminho do ficheiro:");
       
        controller.lerCoberturasFicheiro(caminhoFicheiro);

    }

}
