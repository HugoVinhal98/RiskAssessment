/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apresentacao;

import Aplicacao.ListarEnvolventesController;
import Dominio.Envolvente;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author jmbosg
 */
public class ListarEnvolventesUI {
    
    /**
     * Controller do UC listar envolventes
     */
    private final ListarEnvolventesController controller = new ListarEnvolventesController();
    
    /**
     * UI do UC listar envolventes
     * @throws IOException 
     */
    public void listarEnvolventes() throws IOException {
        System.out.println("*** Listar Envolventes Ficheiro ***\n");
        List<Envolvente> listaEnvolventes = controller.listarEnvolventes();

        for (Envolvente e : listaEnvolventes) {

            System.out.println(e);
        }
    }

}
