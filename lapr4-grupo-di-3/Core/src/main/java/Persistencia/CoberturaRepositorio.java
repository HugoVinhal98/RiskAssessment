/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Dominio.Cobertura;
import java.util.List;

/**
 *
 * @author franc
 */
public interface CoberturaRepositorio {

    /**
     * inserts an entity and commits
     *
     * @param entity
     * @return the persisted entity
     */
    public Cobertura add(Cobertura entity);

    /**
     * reads an entity given its ID
     *
     * @param id
     * @return
     */
    public Cobertura findById(Long id);

    /**
     * Returns the List of all entities in the persistence store
     *
     * @return
     */
    public List<Cobertura> findAll();

}
