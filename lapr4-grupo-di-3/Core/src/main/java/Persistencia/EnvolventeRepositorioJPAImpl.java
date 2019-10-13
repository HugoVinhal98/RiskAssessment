/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Dominio.Envolvente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author eapli
 */
public class EnvolventeRepositorioJPAImpl implements EnvolventeRepositorio {

    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.
                createEntityManagerFactory("JPA2PU");
        EntityManager manager = factory.createEntityManager();
        return manager;
    }

    /**
     * inserts an entity Envolvente
     *
     * @param envolvente
     * @return the persisted entity
     */
    @Override
    public Envolvente add(Envolvente envolvente) {
        if (envolvente == null) {
            throw new IllegalArgumentException();
        }
        for(Envolvente e : findAll()){
            if(e.getNome().equals(envolvente.getNome())){
              return null;
            }              
        }
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(envolvente);
        tx.commit();
        em.close();

        return envolvente;
    }

    /**
     * reads an entity Envolvente given its ID
     *
     * @param id
     * @return
     */
    @Override
    public Envolvente findById(Long id) {
        return getEntityManager().find(Envolvente.class, id);
    }

    /**
     * Returns the List of all entities in the persistence store
     *
     * @return
     */
    //@SuppressWarnings("unchecked")
    @Override
    public List<Envolvente> findAll() {
        Query query = getEntityManager().createQuery(
                "SELECT e FROM Envolvente e");
        List<Envolvente> list = query.getResultList();
        return list;
    }
}
