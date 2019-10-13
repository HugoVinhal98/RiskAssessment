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
public class Prioridade implements Serializable {

    /**
     * Prioridade de um pedido (valor de 1 a 5, medida de forma crescente).
     */
    private int prioridade;

    /**
     * Construtor de prioridade
     *
     * @param prioridade Prioridade de um pedido
     */
    public Prioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    /**
     * Construtor vazio para ser usado pelo JPA
     */
    public Prioridade() {
    }

    public int getPrioridade() {
        return prioridade;
    }

}
