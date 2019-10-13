/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Dominio.MatrizRisco;
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
public class MatrizRiscoRepositorioJPAImpl implements MatrizRiscoRepositorio {

    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.
                createEntityManagerFactory("JPA2PU");
        EntityManager manager = factory.createEntityManager();
        return manager;
    }

    /**
     * inserts an entity MatrizRisco
     *
     * @param matrizRisco
     * @return the persisted entity
     */
    @Override
    public MatrizRisco add(MatrizRisco matrizRisco) {
        if (matrizRisco == null) {
            throw new IllegalArgumentException();
        }
        for(MatrizRisco mr : findAll()){
            if(mr.equals(matrizRisco)){
              return null;
            }              
        }        
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(matrizRisco);
        tx.commit();
        em.close();

        return matrizRisco;
    }

    /**
     * removes an entity MatrizRisco
     *
     * @param id
     * @return the persisted entity
     */
    @Override
    public MatrizRisco remove(Long id) {

        EntityManager em = getEntityManager();

        MatrizRisco mr = findById(id);
        em.getTransaction().begin();
        mr = em.merge(mr);
        em.remove(mr);
        em.getTransaction().commit();

        return mr;
    }

    /**
     * reads an entity MatrizRisco given its ID
     *
     * @param id
     * @return
     */
    @Override
    public MatrizRisco findById(Long id) {
        return getEntityManager().find(MatrizRisco.class, id);
    }

    /**
     * Returns the List of all entities in the persistence store
     *
     * @return
     */
    //@SuppressWarnings("unchecked")
    @Override
    public List<MatrizRisco> findAll() {
        Query query = getEntityManager().createQuery(
                "SELECT c FROM MatrizRisco c");
        List<MatrizRisco> list = query.getResultList();
        return list;
    }

}
