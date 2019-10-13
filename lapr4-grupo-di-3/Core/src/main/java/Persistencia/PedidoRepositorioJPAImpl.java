/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Dominio.Pedido;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author Jmbosg
 */
public class PedidoRepositorioJPAImpl implements PedidoRepositorio {

    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.
                createEntityManagerFactory("JPA2PU");
        EntityManager manager = factory.createEntityManager();
        return manager;
    }

    /**
     * inserts an entity Pedido
     *
     * @param pedido
     * @return the persisted entity
     */
    @Override
    public Pedido add(Pedido pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException();
        }
        for (Pedido mr : findAll()) {
            if (mr.equals(pedido)) {
                return null;
            }
        }
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(pedido);
        tx.commit();
        em.close();

        return pedido;
    }

    /**
     * removes an entity Pedido
     *
     * @param id
     * @return the persisted entity
     */
    @Override
    public Pedido remove(Long id) {

        EntityManager em = getEntityManager();

        Pedido mr = findById(id);
        em.getTransaction().begin();
        mr = em.merge(mr);
        em.remove(mr);
        em.getTransaction().commit();

        return mr;
    }

    /**
     * reads an entity Pedido given its ID
     *
     * @param id
     * @return
     */
    @Override
    public Pedido findById(Long id) {
        return getEntityManager().find(Pedido.class, id);
    }

    /**
     * Returns the List of all entities in the persistence store
     *
     * @return
     */
    //@SuppressWarnings("unchecked")
    @Override
    public List<Pedido> findAll() {
        Query query = getEntityManager().createQuery(
                "SELECT c FROM Pedido c");
        List<Pedido> list = query.getResultList();
        return list;
    }

    /**
     * Retorna todos os pedidos pendentes pertencentes a um analista
     *
     * @param nome Nome de utilizador do analista
     * @return Lista de pedidos pendentes do analista
     */
    @Override
    public List<Pedido> findPedidosPendentesByAnalista(String nome) {
        Query query = getEntityManager().createQuery("SELECT c FROM Pedido c WHERE c.nomeUtilizador=:nome AND c.estadoPedido.estadoPedido=:pendente");
        query.setParameter("nome", nome);
        query.setParameter("pendente", "pendente");
        List<Pedido> list = query.getResultList();
        return list;
    }

    /**
     * Returns the List of all entities in the persistence store com processado
     * no estado de pedido
     *
     * @return
     */
    //@SuppressWarnings("unchecked")
    @Override
    public List<Pedido> findAllConcluidos() {
        Query query = getEntityManager().createQuery(
                "SELECT c FROM Pedido c WHERE c.estadoPedido.estadoPedido = 'processado'");
        List<Pedido> list = query.getResultList();
        return list;
    }

    /**
     * Retorna todos os pedidos pendentes pertencentes a um analista
     *
     * @param nome Nome de utilizador do analista
     * @return Lista de pedidos pendentes do analista
     */
    @Override
    public List<Pedido> findPedidosProcessadosByMe(String nome) {
        Query query = getEntityManager().createQuery("SELECT c FROM Pedido c WHERE c.nomeUtilizador=:nome AND c.estadoPedido.estadoPedido=:processado");
        query.setParameter("nome", nome);
        query.setParameter("processado", "processado");
        List<Pedido> list = query.getResultList();
        return list;
    }

    @Override
    public List<Pedido> findPedidosProcessadosByDatas(String nome, Date dataIni, Date dataFim) {

        Query query = getEntityManager().createQuery(
                "SELECT c FROM Pedido c WHERE c.nomeUtilizador=:nome AND c.estadoPedido.estadoPedido=:processado AND c.dataPedido BETWEEN :dataIni AND :dataFim");
        query.setParameter("nome", nome);
        query.setParameter("processado", "processado");
        query.setParameter("dataIni", dataIni);
        query.setParameter("dataFim", dataFim);
        List<Pedido> list = query.getResultList();

        return list;
    }

}
