/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apresentacao;

import Aplicacao.LerFatoresRiscoFicheiroController;
import eapli.framework.util.Console;
import java.io.IOException;

/**
 *
 * @author hugov
 */
public class LerFatoresRiscoFicheiroUI {
    
    /**
     * Controller do UC ler fatores de risco de ficheiro
     */
    private final LerFatoresRiscoFicheiroController controller = new LerFatoresRiscoFicheiroController();
    
    /**
     * UI do UC ler fatores de risco do ficheiro
     * @throws IOException 
     */
    public void lerFatoresRiscoDeFicheiro() throws IOException {
        System.out.println("*** Ler Fatores de Risco de Ficheiro ***\n");
        String caminhoFicheiro = Console.readLine("Insira caminho do ficheiro dos fatores de risco:");
        controller.lerFatoresRiscoDeFicheiro(caminhoFicheiro);
    }
    
}
