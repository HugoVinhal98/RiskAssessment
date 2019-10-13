/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacao;

import Dominio.Cobertura;
import Persistencia.CoberturaRepositorio;
import Persistencia.CoberturaRepositorioJPAImpl;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author franc
 */
public class ListarCoberturasController {
    
    /**
     * Controller do UC listar coberturas
     * 
     * @return Lista de coberturas listadas
     * @throws IOException 
     */
    
    public List<Cobertura> listarCoberturas() throws IOException {
        
        CoberturaRepositorio repo = new CoberturaRepositorioJPAImpl();

        List<Cobertura> lc = repo.findAll();

        return lc;

    }

}
