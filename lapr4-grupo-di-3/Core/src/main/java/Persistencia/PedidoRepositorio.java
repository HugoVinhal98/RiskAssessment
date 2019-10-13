/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Dominio.Pedido;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jmbosg
 */
public interface PedidoRepositorio {

    /**
     * inserts an entity and commits
     *
     * @param entity
     * @return the persisted entity
     */
    public Pedido add(Pedido entity);

    /**
     * removes an entity and commits
     *
     * @param id
     * @return the persisted entity
     */
    public Pedido remove(Long id);

    /**
     * reads an entity given its ID
     *
     * @param id
     * @return
     */
    public Pedido findById(Long id);

    /**
     * Returns the List of all entities in the persistence store
     *
     * @return
     */
    public List<Pedido> findAll();

    /**
     * Retorna todos os pedidos pendentes pertencentes a um analista
     *
     * @param nome Nome de utilizador do analista
     * @return Lista de pedidos pendentes do analista
     */
    public List<Pedido> findPedidosPendentesByAnalista(String nome);

    /**
     * Returns the List of all entities in the persistence store
     *
     * @return
     */
    public List<Pedido> findAllConcluidos();
    /**
     * returns the list
     * @param nome
     * @return 
     */
    public List<Pedido> findPedidosProcessadosByMe(String nome); 
    /**
     * returns list
     * @param nome
     * @param dataIni
     * @param dataFim
     * @return 
     */
    public List<Pedido> findPedidosProcessadosByDatas(String nome, Date dataIni, Date dataFim); 
}
