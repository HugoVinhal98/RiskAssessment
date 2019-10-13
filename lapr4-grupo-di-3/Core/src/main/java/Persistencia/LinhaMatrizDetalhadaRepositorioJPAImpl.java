/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Dominio.LinhaMatrizBase;
import Dominio.LinhaMatrizDetalhada;
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
public class LinhaMatrizDetalhadaRepositorioJPAImpl implements LinhaMatrizDetalhadaRepositorio {

    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.
                createEntityManagerFactory("JPA2PU");
        EntityManager manager = factory.createEntityManager();
        return manager;
    }

    /**
     * inserts an entity LinhaMatrizDetalhada
     *
     * @param fa
     * @return the persisted entity
     */
    @Override
    public LinhaMatrizDetalhada add(LinhaMatrizDetalhada fa) {
        if (fa == null) {
            throw new IllegalArgumentException();
        }
        for (LinhaMatrizDetalhada ld : findAll()) {
            if (ld.equals(fa)) {
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
     * removes an entity LinhaMatrizDetalhada
     *
     * @param id
     * @return the persisted entity
     */
    @Override
    public LinhaMatrizDetalhada remove(Long id) {

        EntityManager em = getEntityManager();

        LinhaMatrizDetalhada mr = findById(id);
        em.getTransaction().begin();
        mr = em.merge(mr);
        em.remove(mr);
        em.getTransaction().commit();

        return mr;
    }

    /**
     * reads an entity LinhaMatrizDetalhada given its ID
     *
     * @param id
     * @return
     */
    @Override
    public LinhaMatrizDetalhada findById(Long id) {
        return getEntityManager().find(LinhaMatrizDetalhada.class, id);
    }

    /**
     * Returns the List of all entities in the persistence store
     *
     * @return
     */
    //@SuppressWarnings("unchecked")
    @Override
    public List<LinhaMatrizDetalhada> findAll() {
        Query query = getEntityManager().createQuery(
                "SELECT fa FROM LinhaMatrizDetalhada fa");
        List<LinhaMatrizDetalhada> list = query.getResultList();
        return list;
    }
}
