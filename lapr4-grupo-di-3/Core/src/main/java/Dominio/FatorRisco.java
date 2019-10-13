/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

/**
 *
 * @author Diogo Rolo
 */
@Entity
public class FatorRisco implements Serializable {

    /**
     * Id auto gerado que identifica um fator de risco
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Metrica associada a um fator de risco
     */
    @Embedded
    private Metrica metrica;

    /**
     * Envolvente associada a um fator de risco
     */
    @JoinColumn(name = "Envolvente")
    private Envolvente envolvente;

    /**
     * Construtor vazio para ser usado pelo JPA
     */
    protected FatorRisco() {
    }

    /**
     * Construtor de fator de risco
     *
     * @param envolvente Envolvente associada a um fator de risco
     * @param metrica Metrica associada a um fator de risco
     */
    public FatorRisco(Envolvente envolvente, Metrica metrica) {
        this.metrica = metrica;
        this.envolvente = envolvente;
    }

    public Metrica getMetrica() {
        return metrica;
    }

    public Envolvente getEnvolvente() {
        return envolvente;
    }

    public Long getId() {
        return id;
    }

    public String getNomeEnvolvente() {
        return envolvente.getNome();
    }

    public String getNomeMetrica() {
        return metrica.getNome();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMetrica(Metrica metrica) {
        this.metrica = metrica;
    }

    public void setEnvolvente(Envolvente envolvente) {
        this.envolvente = envolvente;
    }

    /**
     * Metodo hashCode() do objeto fator de risco
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.metrica);
        hash = 23 * hash + Objects.hashCode(this.envolvente);
        return hash;
    }

    /**
     * Metodo equals() de um objeto fator de risco
     *
     * @param obj
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
        final FatorRisco other = (FatorRisco) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.metrica.getNome(), other.metrica.getNome())) {
            return false;
        }
        if (!Objects.equals(this.envolvente, other.envolvente)) {
            return false;
        }
        return true;
    }

    /**
     * Metodo toString() de um fator de risco
     *
     * @return
     */
    @Override
    public String toString() {
        return "Fator de Risco ( Envolvente: " + this.getNomeEnvolvente() + "\t" + " Métrica: " + this.getNomeMetrica() + " )";
    }

}
