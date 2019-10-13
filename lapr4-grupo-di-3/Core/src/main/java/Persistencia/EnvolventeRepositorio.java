/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Dominio.Envolvente;
import java.util.List;

/**
 *
 * @author eapli
 *
 */
public interface EnvolventeRepositorio {

    /**
     * inserts an entity and commits
     *
     * @param entity
     * @return the persisted entity
     */
    public Envolvente add(Envolvente entity);

    /**
     * reads an entity given its ID
     *
     * @param id
     * @return
     */
    public Envolvente findById(Long id);

    /**
     * Returns the List of all entities in the persistence store
     *
     * @return
     */
    public List<Envolvente> findAll();

}
