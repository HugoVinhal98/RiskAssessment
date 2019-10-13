/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacao;

import Dominio.FatorRisco;
import Dominio.MatrizRisco;
import Persistencia.FatorDeRiscoRepositorio;
import Persistencia.FatorDeRiscoRepositorioJPAImpl;
import Persistencia.MatrizRiscoRepositorio;
import Persistencia.MatrizRiscoRepositorioJPAImpl;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author Diogo Rolo
 */
public class ListarFatoresRiscoController {
    
    private final MatrizRisco mr = new MatrizRisco();
    
    /**
     * Controller do UC listar fatores de risco existentes na base de dados
     * @return Lista de fatores de risco encontrados
     */
    public List<FatorRisco> listarFatoresRisco() {
        
        FatorDeRiscoRepositorio repo = new FatorDeRiscoRepositorioJPAImpl();
        
        List<FatorRisco> l = repo.findAll();

        return l;
        
    }
    
 
    
}
