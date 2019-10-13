/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Diogo Rolo
 */
@Entity
public class Cobertura implements Serializable {

    /**
     * Id auto gerado para identificar uma cobertura
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome da cobertura (ex: tempestade, incendio)
     */
    private String nome;

    /**
     * Construtor vazio para ser usado pelo JPA
     */
    protected Cobertura() {
    }

    /**
     * Construtor de cobertura
     *
     * @param nome Nome da Cobertura
     */
    public Cobertura(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
    /**
     * Metodo hashCode() do objeto cobertura
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.nome);
        return hash;
    }

    /**
     * Metodo equals() do objeto cobertura
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        final Cobertura other = (Cobertura) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }

    /**
     * Metodo toString() do objeto cobertura
     *
     * @return
     */
    @Override
    public String toString() {
        return nome;
    }

}
