/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Dominio.LinhaMatrizBase;
import java.util.List;

/**
 *
 * @author jmbosg
 */
public interface LinhaMatrizBaseRepositorio {
    
    /**
     * inserts an entity and commits
     *
     * @param entity
     * @return the persisted entity
     */
    public LinhaMatrizBase add(LinhaMatrizBase entity);

    /**
     * reads an entity given its ID
     *
     * @param id
     * @return
     */
    public LinhaMatrizBase findById(Long id);

    /**
     * Returns the List of all entities in the persistence store
     *
     * @return
     */
    public List<LinhaMatrizBase> findAll();    
}
