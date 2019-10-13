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
public class EstadoMatriz implements Serializable {
    
    /**
     * Valor do estado de uma matriz de risco (0 - Detalhada; 1 - Publicada; 2 - Obsoleta)
     */
    int estado;
    
    /**
     * Construtor vazio para ser usado pelo JPA
     */
    protected EstadoMatriz() {
    }
    
    /**
     * Construtor de estado matriz
     * @param estado Valor do estado da matriz
     */
    public EstadoMatriz(int estado) {
        this.estado = estado;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    /**
     * Metodo toString() do objeto estado matriz
     * @return 
     */
    @Override
    public String toString() {
        return "EstadoMatriz{" + "estado=" + estado + '}';
    }
 
}