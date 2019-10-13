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
 * @author Diogo Rolo
 */
@Embeddable
public class EscalaRisco implements Serializable {

    /**
     * Valor (1-5) do elemento baixo da escala de risco
     */
    private int baixo;

    /**
     * Valor (1-5) do elemento medio da escala de risco
     */
    private int medio;

    /**
     * Valor (1-5) do elemento alto da escala de risco
     */
    private int alto;

    /**
     * Construtor vazio para ser usado pelo JPA
     */
    protected EscalaRisco() {
    }

    /**
     * Construtor da escala de risco
     *
     * @param baixo Valor (1-5) do elemento baixo da escala de risco
     * @param medio Valor (1-5) do elemento medio da escala de risco
     * @param alto Valor (1-5) do elemento alto da escala de risco
     */
    public EscalaRisco(int baixo, int medio, int alto) {
        this.baixo = baixo;
        this.medio = medio;
        this.alto = alto;
    }

    public int getBaixo() {
        return baixo;
    }

    public void setBaixo(int baixo) {
        this.baixo = baixo;
    }

    public int getMedio() {
        return medio;
    }

    public void setMedio(int medio) {
        this.medio = medio;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

}
