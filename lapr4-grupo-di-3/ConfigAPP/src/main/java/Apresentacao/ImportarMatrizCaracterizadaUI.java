/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apresentacao;

import Aplicacao.ImportarMatrizCaracterizadaController;
import eapli.framework.util.Console;
import java.io.IOException;

/**
 *
 * @author franc
 */
public class ImportarMatrizCaracterizadaUI {

    /**
     * Controller do UC importar matriz caraterizada
     */
    private final ImportarMatrizCaracterizadaController controller = new ImportarMatrizCaracterizadaController();

    /**
     * UI do UC importar matriz caraterizada
     *
     * @throws IOException
     */
    public void lerLinhasCaracterizadaFicheiro() throws IOException {
        System.out.println("*** Ler linhas da matriz Caracterizada a partir de ficheiros ***\n");
        String caminhoFicheiro1 = Console.readLine("Insira caminho do ficheiro das linhas caracterizadas (Peso):");
        String caminhoFicheiro2 = Console.readLine("Insira caminho do ficheiro das linhas caracterizadas (Necessidade):");
        String caminhoFicheiro3 = Console.readLine("Insira caminho do ficheiro das linhas caracterizadas (Contribuição):");
        Long idMatriz = Console.readLong("Insira o id da matriz à qual as linhas caraterizadas vao ficar associadas:");
        controller.lerLinhasCaracterizadaPesoFicheiro(caminhoFicheiro1, caminhoFicheiro2, caminhoFicheiro3, idMatriz);
    }

}
