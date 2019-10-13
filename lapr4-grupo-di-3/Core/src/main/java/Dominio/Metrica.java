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
public class Metrica implements Serializable {

    /**
     * Nome da metrica de um fator de risco (ex: distancia minima)
     */
    private String nomeMetrica;
    
    /**
     * Construtor vazio para ser usado pelo JPA
     */
    protected Metrica() {
    }
    
    /**
     * Construtor de metrica
     * @param designacao Designacao da metrica
     */
    public Metrica(String designacao) {
        this.nomeMetrica = designacao;
    }

    
    public String getNome() {
        return nomeMetrica;
    }
    
    /**
     * Metodo toString() do objeto metrica
     * @return 
     */
    @Override
    public String toString() {
        return "Metrica{" + "nomeMetrica=" + nomeMetrica + '}';
    }

}
