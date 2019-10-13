/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apresentacao;

import Aplicacao.RecaraterizarMatrizRiscoController;
import eapli.framework.util.Console;
import java.io.IOException;

/**
 *
 * @author Jmbosg
 */
public class RecaraterizarMatrizRiscoUI {

    /**
     * Controller do UC recaraterizar matriz
     */
    private final RecaraterizarMatrizRiscoController controller = new RecaraterizarMatrizRiscoController();
    
    /**
     * UI do UC recaraterizar matriz
     * @throws IOException 
     */
    public void recaraterizarMatrizRiscoUI() throws IOException {

        Long idMatriz = Console.readLong("Insira o id da matriz a recaraterizar:");

        String caminhoFicheiro1 = Console.readLine("Insira caminho do ficheiro das novas linhas caracterizadas (Peso):");
        String caminhoFicheiro2 = Console.readLine("Insira caminho do ficheiro das novas linhas caracterizadas (Necessidade):");
        String caminhoFicheiro3 = Console.readLine("Insira caminho do ficheiro das novas linhas caracterizadas (Contribuição):");

        controller.recaraterizarMatrizRiscoController(idMatriz, caminhoFicheiro1, caminhoFicheiro2, caminhoFicheiro3);

    }

}
