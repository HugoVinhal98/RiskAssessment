/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apresentacao;

import Aplicacao.ListarFatoreNaoExistentesController;
import Dominio.FatorRisco;
import Dominio.MatrizRisco;
import eapli.framework.util.Console;
import java.util.HashSet;

/**
 *
 * @author Diogo Rolo
 */
public class ListarFatoresNaoExistentesUI {

    /**
     * Controller do UC listar fatores nao existentes
     */
    private final ListarFatoreNaoExistentesController controller = new ListarFatoreNaoExistentesController();

    /**
     * UI do UC listar fatores de risco nao existentes numa dada matriz
     */
    public void fatoresNaoExistentes() {

        Long id = Console.readLong("Indique o id da matriz de risco que pretende:");

        MatrizRisco mr = controller.verificarExistenciaId(id);

        if (mr == null) {
            System.out.println("\n\nO id inserido não foi encontrado!");

        } else {
            HashSet<FatorRisco> fr = controller.fatoresNaoExistentes(mr);

            System.out.println("------------\n");

            if (fr.isEmpty()) {
                System.out.println("A matriz de risco selecionada tem todos os fatores"
                        + " de risco existentes");
            } else {
                System.out.println("*** Fatores De Risco Não Encontrados Numa Matriz Risco : *** \n");
                for (FatorRisco f : fr) {
                    System.out.println(f.toString());
                }
            }
        }

    }
}
