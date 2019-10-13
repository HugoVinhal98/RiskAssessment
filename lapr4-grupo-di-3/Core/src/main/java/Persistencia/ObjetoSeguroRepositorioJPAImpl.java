/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Dominio.ObjetoSeguro;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author hugov
 */
public class ObjetoSeguroRepositorioJPAImpl implements ObjetoSeguroRepositorio {
    
    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.
                createEntityManagerFactory("JPA2PU");
        EntityManager manager = factory.createEntityManager();
        return manager;
    }

    /**
     * inserts an entity MatrizRisco
     *
     * @param objetoSeguro
     * @return the persisted entity
     */
    @Override
    public ObjetoSeguro add(ObjetoSeguro objetoSeguro) {
        if (objetoSeguro == null) {
            throw new IllegalArgumentException();
        }
        for(ObjetoSeguro mr : findAll()){
            if(mr.equals(objetoSeguro)){
              return null;
            }              
        }        
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(objetoSeguro);
        tx.commit();
        em.close();

        return objetoSeguro;
    }

    /**
     * removes an entity MatrizRisco
     *
     * @param id
     * @return the persisted entity
     */
    @Override
    public ObjetoSeguro remove(Long id) {

        EntityManager em = getEntityManager();

        ObjetoSeguro mr = findById(id);
        em.getTransaction().begin();
        mr = em.merge(mr);
        em.remove(mr);
        em.getTransaction().commit();

        return mr;
    }

    /**
     * reads an entity ObjetoSeguro given its ID
     *
     * @param id
     * @return
     */
    @Override
    public ObjetoSeguro findById(Long id) {
        return getEntityManager().find(ObjetoSeguro.class, id);
    }

    /**
     * Returns the List of all entities in the persistence store
     *
     * @return
     */
    //@SuppressWarnings("unchecked")
    @Override
    public List<ObjetoSeguro> findAll() {
        Query query = getEntityManager().createQuery(
                "SELECT c FROM ObjetoSeguro c");
        List<ObjetoSeguro> list = query.getResultList();
        return list;
    }
}
