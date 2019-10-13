/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apresentacao;

import Aplicacao.ListarFatoresRiscoController;
import Dominio.FatorRisco;
import Dominio.MatrizRisco;
import java.util.List;
import eapli.framework.util.Console;
import java.util.HashSet;

/**
 *
 * @author Diogo Rolo
 */
public class ListarFatoresRiscoUI {

    /**
     * Controller do UC listar fatores de risco
     */
    private final ListarFatoresRiscoController controller = new ListarFatoresRiscoController();

    /**
     * UI do UC listar fatores de risco existentes na base de dados
     */
    public void listarFatoresRisco() {

        System.out.println("*** Listar Fatores De Risco: *** \n");

        List<FatorRisco> l = controller.listarFatoresRisco();

        for (FatorRisco fc : l) {

            System.out.println(fc);
        }
    }

}
