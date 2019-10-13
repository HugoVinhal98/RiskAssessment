/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Dominio.AnalistaRisco;
import Dominio.AvaliacaoRisco;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author GilTrindade
 */
public class AnalistaRiscoRepositorioJPAImpl implements AnalistaRiscoRepositorio {
   
    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.
                createEntityManagerFactory("JPA2PU");
        EntityManager manager = factory.createEntityManager();
        return manager;
    }
    @Override
    public AnalistaRisco add(AnalistaRisco entity) {
          if (entity== null) {
            throw new IllegalArgumentException();
        }
        for(AnalistaRisco ar : findAll()){
            if(ar.equals(entity)){
              return null;
            }              
        }        
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(entity);
        tx.commit();
        em.close();

        return entity;
    }

    @Override
    public AnalistaRisco findById(Long id) {
       return getEntityManager().find(AnalistaRisco.class, id);
    }

    @Override
    public List<AnalistaRisco> findAll() {
        Query query = getEntityManager().createQuery(
                "SELECT c FROM AnalistaRisco c");
        List<AnalistaRisco> list = query.getResultList();
        return list;
    }

    @Override
    public AnalistaRisco remove(Long id) {
             EntityManager em = getEntityManager();

        AnalistaRisco ar = findById(id);
        em.getTransaction().begin();
        ar = em.merge(ar);
        em.remove(ar);
        em.getTransaction().commit();

        return ar;
    }
    
}
