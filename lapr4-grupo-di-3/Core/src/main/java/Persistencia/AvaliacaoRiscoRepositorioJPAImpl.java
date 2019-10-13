/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Dominio.AvaliacaoRisco;
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
public class AvaliacaoRiscoRepositorioJPAImpl implements AvaliacaoRiscoRepositorio {
    
     private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.
                createEntityManagerFactory("JPA2PU");
        EntityManager manager = factory.createEntityManager();
        return manager;
    }

    /**
     * inserts an entity AvaliacaoRisco
     *
     * @param avaliacaoRisco
     * @return the persisted entity
     */
    @Override
    public AvaliacaoRisco add(AvaliacaoRisco avaliacaoRisco) {
        if (avaliacaoRisco == null) {
            throw new IllegalArgumentException();
        }
        for(AvaliacaoRisco ar : findAll()){
            if(ar.equals(avaliacaoRisco)){
              return null;
            }              
        }        
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(avaliacaoRisco);
        tx.commit();
        em.close();

        return avaliacaoRisco;
    }

    /**
     * removes an entity AvaliacaoRisco
     *
     * @param id
     * @return the persisted entity
     */
    @Override
    public AvaliacaoRisco remove(Long id) {

        EntityManager em = getEntityManager();

        AvaliacaoRisco ar = findById(id);
        em.getTransaction().begin();
        ar = em.merge(ar);
        em.remove(ar);
        em.getTransaction().commit();

        return ar;
    }

    /**
     * reads an entity AvaliacaoRisco given its ID
     *
     * @param id
     * @return
     */
    @Override
    public AvaliacaoRisco findById(Long id) {
        return getEntityManager().find(AvaliacaoRisco.class, id);
    }

    /**
     * Returns the List of all entities in the persistence store
     *
     * @return
     */
    //@SuppressWarnings("unchecked")
    @Override
    public List<AvaliacaoRisco> findAll() {
        Query query = getEntityManager().createQuery(
                "SELECT c FROM AvaliacaoRisco c");
        List<AvaliacaoRisco> list = query.getResultList();
        return list;
    }
    
    
}
