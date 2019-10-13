/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Dominio.Cobertura;
import Dominio.LinhaMatrizBase;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author jmbosg
 */
public class LinhaMatrizBaseRepositorioJPAImpl implements LinhaMatrizBaseRepositorio {
     
    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.
                createEntityManagerFactory("JPA2PU");
        EntityManager manager = factory.createEntityManager();
        return manager;
    }

    /**
     * inserts an entity LinhaMatrizBase
     *
     * @param fa
     * @return the persisted entity
     */
    @Override
    public LinhaMatrizBase add(LinhaMatrizBase fa) {
        if (fa == null) {
            throw new IllegalArgumentException();
        }
        for(LinhaMatrizBase lb : findAll()){
            if(lb.equals(fa)){
              return null;
            }              
        }
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(fa);
        tx.commit();
        em.close();

        return fa;
    }

    /**
     * reads an entity LinhaMatrizBase given its ID
     *
     * @param id
     * @return
     */
    @Override
    public LinhaMatrizBase findById(Long id) {
        return getEntityManager().find(LinhaMatrizBase.class, id);
    }

    /**
     * Returns the List of all entities in the persistence store
     *
     * @return
     */
    //@SuppressWarnings("unchecked")
    @Override
    public List<LinhaMatrizBase> findAll() {
        Query query = getEntityManager().createQuery(
                "SELECT fa FROM LinhaMatrizBase fa");
        List<LinhaMatrizBase> list = query.getResultList();
        return list;
    }
}
