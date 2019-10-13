/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacao;

import Dominio.FatorRisco;
import Dominio.MatrizRisco;
import Persistencia.MatrizRiscoRepositorio;
import Persistencia.MatrizRiscoRepositorioJPAImpl;
import Utils.LerFicheiros;
import java.util.HashSet;

/**
 *
 * @author Diogo Rolo
 */
public class ListarFatoreNaoExistentesController {
    
  

    /**
     * Controller do UC listar fatores de risco nao existentes numa matriz
     *
     * @param id id da matriz cujos fatores de risco vao ser avaliados
     * @return Lista de fatores que nao existem na matriz
     */
    public HashSet<FatorRisco> fatoresNaoExistentes(MatrizRisco mr) {

        LerFicheiros lf = new LerFicheiros();
        
        HashSet<FatorRisco> fr = lf.fatoresNaoExistentes(mr);

        return fr;

    }

    /**
     * Controller de uma verificação de existencia de uma certa matriz risco
     *
     * @param id
     * @return
     */
    public MatrizRisco verificarExistenciaId(Long id) {
        
         LerFicheiros lf = new LerFicheiros();

         MatrizRisco mr = lf.verificarIdMatrizRisco(id);
       
        return mr;
    }
}
