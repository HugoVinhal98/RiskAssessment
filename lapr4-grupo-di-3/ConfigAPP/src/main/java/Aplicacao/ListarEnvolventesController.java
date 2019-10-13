/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacao;

import Dominio.Envolvente;
import Persistencia.EnvolventeRepositorio;
import Persistencia.EnvolventeRepositorioJPAImpl;
import java.util.List;

/**
 *
 * @author jmbosg
 */
public class ListarEnvolventesController {
    
    /**
     * Controller do UC listar envolventes
     * 
     * @return Lista de envolventes listadas
     */
    public List<Envolvente> listarEnvolventes() {
        
        EnvolventeRepositorio repo = new EnvolventeRepositorioJPAImpl();
        
        List<Envolvente> list1 = repo.findAll();
        
        return list1;
        
    }
    
}
