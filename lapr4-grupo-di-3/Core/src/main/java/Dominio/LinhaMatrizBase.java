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
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;

/**
 *
 * @author Diogo Rolo
 */
@Entity
public class LinhaMatrizBase implements Serializable {

    /**
     * Id auto gerado que identifica uma linha de matriz base
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Cobertura associada a uma linha de matriz base
     */
    @JoinColumn(name = "Cobertura")
    private Cobertura cobertura;

    /**
     * Fator de risco associado a uma linha de matriz base
     */
    @JoinColumns({
        @JoinColumn(name = "FatorRisco", referencedColumnName = "id")
        , @JoinColumn(name = "Metrica", referencedColumnName = "id")
    })
    private FatorRisco fatorRisco;

    /**
     * Valor que nos diz se uma linha deve ser considerada ou nao para depois
     * sofrer caraterizacao (valores possiveis: sim/nao)
     */
    private String consideravel;

    /**
     * Valor do Id da matriz à qual a linha vai estar associada.
     */
    private Long idMatriz;

    /**
     * Construtor vazio para ser usado pelo JPA
     */
    protected LinhaMatrizBase() {
    }

    /**
     * Construtor de linha de matriz base
     *
     * @param cobertura Cobertura associada a uma linha de matriz base
     * @param fatorRisco Fator de risco associado a uma linha de matriz base
     * @param consideravel Valor da consideravel
     * @param idMatriz Id da matriz à qual a linha vai pertencer
     */
    public LinhaMatrizBase(Cobertura cobertura, FatorRisco fatorRisco, String consideravel, Long idMatriz) {
        this.cobertura = cobertura;
        this.fatorRisco = fatorRisco;
        this.consideravel = consideravel;
        this.idMatriz = idMatriz;
    }

    public Long getId() {
        return id;
    }

    public Long getIdMatriz() {
        return idMatriz;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cobertura getCobertura() {
        return cobertura;
    }

    public FatorRisco getFatorRisco() {
        return fatorRisco;
    }

    public String getConsideravel() {
        return consideravel;
    }

    public void setCobertura(Cobertura cobertura) {
        this.cobertura = cobertura;
    }

    public void setFatorRisco(FatorRisco fatorRisco) {
        this.fatorRisco = fatorRisco;
    }

    public void setConsideravel(String consideravel) {
        this.consideravel = consideravel;
    }

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
        final LinhaMatrizBase other = (LinhaMatrizBase) obj;
        if (!Objects.equals(this.consideravel, other.consideravel)) {
            return false;
        }
        if (!Objects.equals(this.cobertura.getNome(), other.cobertura.getNome())) {
            return false;
        }
        if (!Objects.equals(this.fatorRisco.getId(), other.fatorRisco.getId())) {
            return false;
        }
        if (!Objects.equals(this.idMatriz, other.idMatriz)) {
            return false;
        }
        return true;
    }

}
