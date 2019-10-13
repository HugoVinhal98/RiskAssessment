/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Dominio.LinhaMatrizCaraterizada;
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
public class LinhaMatrizCaraterizadaRepositorioJPAImpl implements LinhaMatrizCaraterizadaRepositorio {

    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.
                createEntityManagerFactory("JPA2PU");
        EntityManager manager = factory.createEntityManager();
        return manager;
    }

    /**
     * inserts an entity LinhaMatrizCaraterizada
     *
     * @param fa
     * @return the persisted entity
     */
    @Override
    public LinhaMatrizCaraterizada add(LinhaMatrizCaraterizada fa) {
        if (fa == null) {
            throw new IllegalArgumentException();
        }
        for (LinhaMatrizCaraterizada lc : findAll()) {
            if (lc.equals(fa)) {
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
     * removes an entity LinhaMatrizCaraterizada
     *
     * @param id
     * @return the persisted entity
     */
    @Override
    public LinhaMatrizCaraterizada remove(Long id) {

        EntityManager em = getEntityManager();

        LinhaMatrizCaraterizada mr = findById(id);
        em.getTransaction().begin();
        mr = em.merge(mr);
        em.remove(mr);
        em.getTransaction().commit();

        return mr;
    }

    /**
     * reads an entity LinhaMatrizCaraterizada given its ID
     *
     * @param id
     * @return
     */
    @Override
    public LinhaMatrizCaraterizada findById(Long id) {
        return getEntityManager().find(LinhaMatrizCaraterizada.class, id);
    }

    /**
     * Returns the List of all entities in the persistence store
     *
     * @return
     */
    //@SuppressWarnings("unchecked")
    @Override
    public List<LinhaMatrizCaraterizada> findAll() {
        Query query = getEntityManager().createQuery(
                "SELECT fa FROM LinhaMatrizCaraterizada fa");
        List<LinhaMatrizCaraterizada> list = query.getResultList();
        return list;
    }
}
