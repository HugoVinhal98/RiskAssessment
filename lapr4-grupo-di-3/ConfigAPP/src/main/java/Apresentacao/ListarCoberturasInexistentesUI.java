/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apresentacao;

import Aplicacao.ListarCoberturasInexistentesController;
import Dominio.Cobertura;
import Dominio.MatrizRisco;
import eapli.framework.util.Console;
import java.util.HashSet;


/**
 *
 * @author franc
 */
public class ListarCoberturasInexistentesUI {

    /**
     * Controller do UC listar coberturas inexistentes numa MR
     */
    private final ListarCoberturasInexistentesController controller = new ListarCoberturasInexistentesController();

 

    /**
     * UI do UC listar coberturas inexistentes numa MR
     */
    public void coberturasInexistentes() {

        Long id = Console.readLong("Indique o id da matriz de risco que pretende:");

        MatrizRisco mr = controller.verificarExistenciaId(id);

        if (mr == null) {
            System.out.println("\n\n O id inserido não foi encontrado!");

        } else {
            HashSet<Cobertura> c = controller.coberturasInexistentes(mr);

            System.out.println("------------\n");

            if (c.isEmpty()) {
                System.out.println("A matriz de risco selecionada tem todas as coberturas");
            } else {
                System.out.println("*** Coberturas não encontradas na Matriz Risco : *** \n");
                for (Cobertura cc : c) {
                    System.out.println(cc.toString());
                }
            }
        }

    }

}
