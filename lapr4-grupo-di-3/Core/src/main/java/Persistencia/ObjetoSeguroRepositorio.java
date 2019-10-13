/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Dominio.ObjetoSeguro;
import java.util.List;

/**
 *
 * @author hugov
 */
public interface ObjetoSeguroRepositorio {
    
    /**
     * inserts an entity and commits
     *
     * @param entity
     * @return the persisted entity
     */
    public ObjetoSeguro add(ObjetoSeguro entity);
    
    /**
     * removes an entity and commits
     *
     * @param id
     * @return the persisted entity
     */
    public ObjetoSeguro remove(Long id);

    /**
     * reads an entity given its ID
     *
     * @param id
     * @return
     */
    public ObjetoSeguro findById(Long id);

    /**
     * Returns the List of all entities in the persistence store
     *
     * @return
     */
    public List<ObjetoSeguro> findAll();
}
