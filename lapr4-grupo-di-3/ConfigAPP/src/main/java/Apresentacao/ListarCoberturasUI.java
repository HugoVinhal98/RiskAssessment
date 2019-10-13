/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apresentacao;

import Dominio.Cobertura;
import Aplicacao.ListarCoberturasController;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author franc
 */
public class ListarCoberturasUI {
    
    /**
     * Controller do UC listar coberturas
     */
    private final ListarCoberturasController controller = new ListarCoberturasController();

    /**
     * UI do UC listar coberturas
     * @throws IOException 
     */
    public void listarCoberturas() throws IOException {

        System.out.println("Listar Coberturas");

        List<Cobertura> lc = controller.listarCoberturas();

        for (Cobertura c : lc) {

            System.out.println(c);
        }

    }

}
