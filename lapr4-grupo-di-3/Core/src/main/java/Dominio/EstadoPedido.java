/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author Jmbosg
 */
@Embeddable
public class EstadoPedido implements Serializable {

    /**
     * Estado do pedido (nao processado/processado).
     */
    private String estadoPedido;

    /**
     * Construtor de estado de pedido
     *
     * @param estadoPedido Estado de um pedido
     */
    public EstadoPedido(String estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    /**
     * Construtor vazio para ser usado pelo JPA
     */
    public EstadoPedido() {
    }

    public String getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(String estadoPedido) {
        this.estadoPedido = estadoPedido;
    }
    
    
    

}
