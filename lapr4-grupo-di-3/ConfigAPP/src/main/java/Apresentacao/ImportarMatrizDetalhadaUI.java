/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apresentacao;

import Aplicacao.ImportarMatrizDetalhadaController;
import eapli.framework.util.Console;
import java.io.IOException;

/**
 *
 * @author hugov
 */
public class ImportarMatrizDetalhadaUI {
    
    /**
     * Controller do UC importar matriz detalhada
     */
    private final ImportarMatrizDetalhadaController controller = new ImportarMatrizDetalhadaController();
    
    /**
     * UI do UC importar matriz detalhada a partir de ficheiro
     * @throws IOException 
     */
    public void lerLinhasDetalhadasFicheiro() throws IOException {
        System.out.println("*** Ler linhas da matriz detalhada de Ficheiro ***\n");
        String caminhoFicheiro = Console.readLine("Insira caminho do ficheiro das linhas detalhadas:");
        Long idMatriz = Console.readLong("Insira o id da matriz à qual as linhas detalhadas vao ficar associadas:");
        controller.lerLinhasDetalhadasFicheiro(caminhoFicheiro, idMatriz);
    }
}
