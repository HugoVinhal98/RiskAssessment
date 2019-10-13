/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.util.Objects;
import javax.persistence.Embeddable;

/**
 *
 * @author hugov
 */
@Embeddable
public class CoberturaOS {

    /**
     * Nome da cobertura relativa ao objeto seguro
     */
    private String cobertura;

    /**
     * Construtor de cobertura de objeto seguro
     * 
     * @param cobertura cobertura relativa ao objeto seguro
     */
    public CoberturaOS(String cobertura) {
        this.cobertura = cobertura;
    }

    /**
     * Construtor vazio para ser usado pelo JPA
     */
    public CoberturaOS() {
    }

    public String getCobertura() {
        return cobertura;
    }

    public void setCobertura(String cobertura) {
        this.cobertura = cobertura;
    }

    /**
     * Metodo equals() do objeto coberturaOS
     *
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CoberturaOS other = (CoberturaOS) obj;
        if (!Objects.equals(this.cobertura, other.cobertura)) {
            return false;
        }
        return true;
    }

    /**
     * Metodo toString() do objeto coberturaOS
     *
     * @return
     */
    @Override
    public String toString() {
        return "CoberturaOS{" + "cobertura=" + cobertura + '}';
    }

}
