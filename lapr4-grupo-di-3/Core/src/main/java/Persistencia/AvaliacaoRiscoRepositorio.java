/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Dominio.AvaliacaoRisco;
import java.util.List;

/**
 *
 * @author franc
 */
public interface AvaliacaoRiscoRepositorio {
    
/**
     * inserts an entity and commits
     *
     * @param entity
     * @return the persisted entity
     */
    public AvaliacaoRisco add(AvaliacaoRisco entity);
    
    /**
     * removes an entity and commits
     *
     * @param id
     * @return the persisted entity
     */
    public AvaliacaoRisco remove(Long id);

    /**
     * reads an entity given its ID
     *
     * @param id
     * @return
     */
    public AvaliacaoRisco findById(Long id);

    /**
     * Returns the List of all entities in the persistence store
     *
     * @return
     */
    public List<AvaliacaoRisco> findAll();
}
