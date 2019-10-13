/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Dominio.Cobertura;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author franc
 */
public class CoberturaRepositorioJPAImpl implements CoberturaRepositorio {

    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.
                createEntityManagerFactory("JPA2PU");
        EntityManager manager = factory.createEntityManager();
        return manager;
    }

    /**
     * inserts an entity Cobertura
     *
     * @param cobertura
     * @return the persisted entity
     */
    @Override
    public Cobertura add(Cobertura cobertura) {
        if (cobertura == null) {
            throw new IllegalArgumentException();
        }
        for(Cobertura c : findAll()){
            if(c.getNome().equals(cobertura.getNome())){
              return null;
            }              
        }
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(cobertura);
        tx.commit();
        em.close();

        return cobertura;
    }

    /**
     * reads an entity Cobertura given its ID
     *
     * @param id
     * @return
     */
    @Override
    public Cobertura findById(Long id) {
        return getEntityManager().find(Cobertura.class, id);
    }

    /**
     * Returns the List of all entities in the persistence store
     *
     * @return
     */
    //@SuppressWarnings("unchecked")
    @Override
    public List<Cobertura> findAll() {
        Query query = getEntityManager().createQuery(
                "SELECT c FROM Cobertura c");
        List<Cobertura> list = query.getResultList();
        return list;
    }
}
