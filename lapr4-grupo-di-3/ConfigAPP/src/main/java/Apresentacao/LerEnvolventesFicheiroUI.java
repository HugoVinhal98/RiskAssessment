/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apresentacao;

import Aplicacao.LerEnvolventesFicheiroController;
import eapli.framework.util.Console;
import java.io.IOException;

/**
 *
 * @author jmbosg
 */
public class LerEnvolventesFicheiroUI {
    
    /**
     * Controller do UC ler envolventes de ficheiro
     */
    private final LerEnvolventesFicheiroController controller = new LerEnvolventesFicheiroController();
    
    /**
     * UI do UC ler envolventes de ficheiro
     * @throws IOException 
     */
    public void lerEnvolventesDeFicheiro() throws IOException {
        System.out.println("*** Ler Envolventes de Ficheiro ***\n");
        String caminhoFicheiro = Console.readLine("Insira caminho do ficheiro:");
        controller.lerEnvolventesDeFicheiro(caminhoFicheiro);
    }
    
}
