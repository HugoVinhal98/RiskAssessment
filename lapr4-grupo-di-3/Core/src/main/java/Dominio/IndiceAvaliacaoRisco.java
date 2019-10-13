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
 * @author franc
 */
@Embeddable
public class IndiceAvaliacaoRisco implements Serializable {

    /**
     * Valor do Indice de avalição de risco
     */
    private float indiceAvalicaoRisco;

    public IndiceAvaliacaoRisco(float indiceAvalicaoRisco) {
        this.indiceAvalicaoRisco = indiceAvalicaoRisco;
    }

    /**
     * Construtor vazio para ser usado pelo JPA
     */
    public IndiceAvaliacaoRisco() {
    }

    public float getIndiceAvalicaoRisco() {
        return indiceAvalicaoRisco;
    }

}
