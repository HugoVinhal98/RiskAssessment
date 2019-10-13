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
public class Contribuicao implements Serializable {

    /**
     * Valor (negativo ou positivo) da contribuicao de uma linha de matriz
     * caraterizada
     */
    String contribuicao;

    /**
     * Construtor vazio para ser usado pelo JPA
     */
    protected Contribuicao() {
    }

    /**
     * Construtor de contribuicao
     *
     * @param contribuicao Valor da contribuicao
     */
    public Contribuicao(String contribuicao) {
        this.contribuicao = contribuicao;
    }

    public String getContribuicao() {
        return contribuicao;
    }

    public void setContribuicao(String contribuicao) {
        this.contribuicao = contribuicao;
    }
    
    /**
     * Metodo toString() do objeto contribuicao
     *
     * @return
     */
    @Override
    public String toString() {
        return "Contribuicao{" + "contribuicao=" + contribuicao + '}';
    }

}
