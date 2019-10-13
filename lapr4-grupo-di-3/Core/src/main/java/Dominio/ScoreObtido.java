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
public class ScoreObtido implements Serializable {

    /**
     * Valor do score Obtido
     */
    private int scoreObtido;

    public ScoreObtido(int scoreObtido) {
        this.scoreObtido = scoreObtido;
    }

    /**
     * Construtor vazio para ser usado pelo JPA
     */
    public ScoreObtido() {
    }

    public int getScoreObtido() {
        return scoreObtido;
    }

}
