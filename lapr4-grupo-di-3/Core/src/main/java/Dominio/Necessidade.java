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
public class Necessidade implements Serializable {

    /**
     * Valor (Facultativo ou Obrigatorio) da necessidade a ser usada por uma linha de matriz caraterizada
     */
    String necessidade;

    /**
     * Construtor de necessidade
     * @param necessidade Valor da necessidade
     */
    public Necessidade(String necessidade) {
        this.necessidade = necessidade;
    }

    /**
     * Construtor vazio para ser usado pelo JPA
     */
    protected Necessidade() {
    }

    public String getNecessidade() {
        return necessidade;
    }

    public void setNecessidade(String necessidade) {
        this.necessidade = necessidade;
    }
    
    /**
     * Metodo toString() do objeto necessidade
     * @return 
     */
    @Override
    public String toString() {
        return "Necessidade{" + "necessidade=" + necessidade + '}';
    }

}
