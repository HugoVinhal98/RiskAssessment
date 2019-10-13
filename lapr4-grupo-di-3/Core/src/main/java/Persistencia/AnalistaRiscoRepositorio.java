/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Dominio.AnalistaRisco;
import java.util.List;

/**
 *
 * @author GilTrindade
 */
public interface AnalistaRiscoRepositorio {
      /**
     * inserts an entity and commits
     *
     * @param entity
     * @return the persisted entity
     */
    public AnalistaRisco add(AnalistaRisco entity);
        /**
     * removes an entity and commits
     *
     * @param id
     * @return the persisted entity
     */
    public AnalistaRisco remove(Long id);

    /**
     * reads an entity given its ID
     *
     * @param id
     * @return
     */
    public AnalistaRisco findById(Long id);

    /**
     * Returns the List of all entities in the persistence store
     *
     * @return
     */
    public List<AnalistaRisco> findAll();
}
