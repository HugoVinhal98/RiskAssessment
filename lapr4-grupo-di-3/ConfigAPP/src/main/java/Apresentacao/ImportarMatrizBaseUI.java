/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apresentacao;

import Aplicacao.ImportarMatrizBaseController;
import eapli.framework.util.Console;
import java.io.IOException;

/**
 *
 * @author jmbosg
 */
public class ImportarMatrizBaseUI {

    /**
     * Controller do uc importar matriz base
     */
    private final ImportarMatrizBaseController controller = new ImportarMatrizBaseController();

    /**
     * UI do UC importar matriz base
     *
     * @throws IOException
     */
    public void lerLinhasBaseFicheiro() throws IOException {
        System.out.println("*** Ler linhas da matriz base de Ficheiro ***\n");
        String caminhoFicheiro = Console.readLine("Insira caminho do ficheiro das linhas base:");
        Long idMatriz = Console.readLong("Insira o id da matriz à qual as linhas base vao ficar associadas:");
        controller.lerLinhasBaseFicheiro(caminhoFicheiro, idMatriz);
    }

}
