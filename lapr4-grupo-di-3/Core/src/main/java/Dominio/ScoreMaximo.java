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
public class ScoreMaximo implements Serializable {

    /**
     * Valor de score Maximo
     */
    private int scoreMaximo;

    public ScoreMaximo(int scoreMaximo) {
        this.scoreMaximo = scoreMaximo;
    }

    /**
     * Construtor vazio para ser usado pelo JPA
     */
    public ScoreMaximo() {
    }

    public int getScoreMaximo() {
        return scoreMaximo;
    }

}
