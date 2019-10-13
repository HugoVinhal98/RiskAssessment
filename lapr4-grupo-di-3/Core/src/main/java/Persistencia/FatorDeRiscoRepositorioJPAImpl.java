/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Dominio.FatorRisco;
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
public class FatorDeRiscoRepositorioJPAImpl implements FatorDeRiscoRepositorio{
    
     private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.
                createEntityManagerFactory("JPA2PU");
        EntityManager manager = factory.createEntityManager();
        return manager;
    }

    /**
     * inserts an entity FatorRisco
     *
     * @param fa
     * @return the persisted entity
     */
    @Override
    public FatorRisco add(FatorRisco fa) {
        if (fa == null) {
            throw new IllegalArgumentException();
        }
        for(FatorRisco fr : findAll()){
            if(fr.getNomeEnvolvente().equals(fa.getNomeEnvolvente()) && fr.getNomeMetrica().equals(fa.getNomeMetrica())){
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
     * reads an entity FatorRisco given its ID
     *
     * @param id
     * @return
     */
    @Override
    public FatorRisco findById(Long id) {
        return getEntityManager().find(FatorRisco.class, id);
    }

    /**
     * Returns the List of all entities in the persistence store
     *
     * @return
     */
    //@SuppressWarnings("unchecked")
    @Override
    public List<FatorRisco> findAll() {
        Query query = getEntityManager().createQuery(
                "SELECT fa FROM FatorRisco fa");
        List<FatorRisco> list = query.getResultList();
        return list;
    }
}
