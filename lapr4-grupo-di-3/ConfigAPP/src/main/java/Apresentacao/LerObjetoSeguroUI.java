/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apresentacao;

import Aplicacao.LerObjetoSeguroController;
import java.util.Scanner;

/**
 *
 * @author Diogo Rolo
 */
public class LerObjetoSeguroUI {

    /**
     * Controller do UC ler objeto seguro de JSON
     */
    private final LerObjetoSeguroController lob = new LerObjetoSeguroController();

    /**
     * UI do UC ler objeto seguro de JSON
     *
     * @throws Exception
     */
    public void lerObjetosJSON() throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("Ler Objetos do ficheiro JSON");

        System.out.println("\n\nQual o nome do ficheiro?");
        String nomeFicheiro = in.nextLine();
        lob.lerObjetosJSON(nomeFicheiro);

    }

}
