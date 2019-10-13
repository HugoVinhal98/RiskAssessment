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
public class Peso implements Serializable {

    /**
     * Valor (1-5) do peso a ser usado numa linha de matriz caraterizada
     */
    private int peso;
    
    /**
     * Construtor vazio para ser usado pelo JPA
     */
    protected Peso() {
    }
    
    /**
     * Construtor de peso
     * @param peso Valor do peso
     */
    public Peso(int peso) {
        this.peso = peso;
    }
    
    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
    
    /**
     * Metodo toString() do objeto peso
     * @return 
     */
    @Override
    public String toString() {
        return "Peso{" + "peso=" + peso + '}';
    }

}
