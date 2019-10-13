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
import javax.persistence.OneToOne;
import javax.persistence.Embedded;

/**
 *
 * @author Diogo Rolo
 */
@Entity
public class LinhaMatrizCaraterizada implements Serializable {

    /**
     * Id auto gerado de linha de matriz caraterizada
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Linha de matriz base associada a uma linha de matriz caraterizada
     */
    @OneToOne
    @JoinColumn(name = "LinhaMatrizBase")
    private LinhaMatrizBase linhaMatrizBase;

    /**
     * Valor do peso de uma linha de matriz caraterizada
     */
    @OneToOne
    @Embedded
    private Peso peso;

    /**
     * Valor da necessidade de uma linha de matriz caraterizada
     */
    @OneToOne
    @Embedded
    private Necessidade necessidade;

    /**
     * Valor da contribuicao de uma linha de matriz caraterizada
     */
    @OneToOne
    @Embedded
    private Contribuicao contribuicao;

    /**
     * Valor do Id da matriz à qual a linha vai estar associada.
     */
    private Long idMatriz;

    /**
     * Construtor vazio para ser usado pelo JPA
     */
    protected LinhaMatrizCaraterizada() {
    }

    /**
     * Construtor de linha de matriz caraterizada
     *
     * @param linhaMatrizBase Linha de matriz base associada a uma linha de
     * matriz caraterizada
     * @param peso Valor do peso de uma linha de matriz caraterizada
     * @param necessidade Valor da necessidade de uma linha de matriz
     * caraterizada
     * @param contribuicao Valor da contribuicao de uma linha de matriz
     * caraterizada
     * @param idMatriz Id da matriz à qual a linha vai pertencer
     */
    public LinhaMatrizCaraterizada(LinhaMatrizBase linhaMatrizBase, Peso peso, Necessidade necessidade, Contribuicao contribuicao, Long idMatriz) {
        this.linhaMatrizBase = linhaMatrizBase;
        this.peso = peso;
        this.necessidade = necessidade;
        this.contribuicao = contribuicao;
        this.idMatriz = idMatriz;
    }

    /**
     * Construtor de linha de matriz caraterizada
     *
     * @param linhaMatrizBase Linha de matriz base associada a uma linha de
     * matriz caraterizada
     * @param peso Valor do peso de uma linha de matriz caraterizada
     * @param idMatriz Valor do id da matriz
     */
    public LinhaMatrizCaraterizada(LinhaMatrizBase linhaMatrizBase, Peso peso, Long idMatriz) {
        this.linhaMatrizBase = linhaMatrizBase;
        this.peso = peso;
        this.idMatriz = idMatriz;
    }

    public LinhaMatrizCaraterizada(Peso peso) {
        this.peso = peso;
    }

    /**
     * Construtor de linha de matriz caraterizada
     *
     * @param linhaMatrizBase Linha de matriz base associada a uma linha de
     * matriz caraterizada
     * @param necessidade Valor da necessidade de uma linha de matriz
     * caraterizada
     * @param idMatriz Valor do id da matriz
     */
    public LinhaMatrizCaraterizada(LinhaMatrizBase linhaMatrizBase, Necessidade necessidade, Long idMatriz) {
        this.linhaMatrizBase = linhaMatrizBase;
        this.necessidade = necessidade;
        this.idMatriz = idMatriz;
    }

    /**
     * Construtor de linha de matriz caraterizada
     *
     * @param linhaMatrizBase Linha de matriz base associada a uma linha de
     * matriz caraterizada
     * @param contribuicao Valor da contribuicao de uma linha de matriz
     * caraterizada
     */
    public LinhaMatrizCaraterizada(LinhaMatrizBase linhaMatrizBase, Contribuicao contribuicao, Long idMatriz) {
        this.linhaMatrizBase = linhaMatrizBase;
        this.contribuicao = contribuicao;
        this.idMatriz = idMatriz;
    }

    /**
     * Construtor de linha de matriz caraterizada
     *
     * @param linhaMatrizBase Linha de matriz base associada a uma linha de
     * matriz caraterizada
     */
    public LinhaMatrizCaraterizada(LinhaMatrizBase linhaMatrizBase) {
        this.linhaMatrizBase = linhaMatrizBase;
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

    public LinhaMatrizBase getLinhaMatrizBase() {
        return linhaMatrizBase;
    }

    public FatorRisco getFatorDeRisco() {
        return linhaMatrizBase.getFatorRisco();
    }

    public Cobertura getCobertura() {
        return linhaMatrizBase.getCobertura();
    }

    public void setPeso(Peso peso) {
        this.peso = peso;
    }

    public Peso getPeso() {
        return peso;
    }

    public FatorRisco getFatorRisco(LinhaMatrizCaraterizada lmc) {
        return lmc.linhaMatrizBase.getFatorRisco();
    }

    public void setNecessidade(Necessidade necessidade) {
        this.necessidade = necessidade;
    }

    public void setContribuicao(Contribuicao contribuicao) {
        this.contribuicao = contribuicao;
    }

    public Necessidade getNecessidade() {
        return necessidade;
    }

    public Contribuicao getContribuicao() {
        return contribuicao;
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
        final LinhaMatrizCaraterizada other = (LinhaMatrizCaraterizada) obj;
        if (!Objects.equals(this.linhaMatrizBase.getId(), other.linhaMatrizBase.getId())) {
            return false;
        }
        if (!Objects.equals(this.peso.getPeso(), other.peso.getPeso())) {
            return false;
        }
        if (!Objects.equals(this.necessidade.getNecessidade(), other.necessidade.getNecessidade())) {
            return false;
        }
        if (!Objects.equals(this.contribuicao.getContribuicao(), other.contribuicao.getContribuicao())) {
            return false;
        }
        if (!Objects.equals(this.idMatriz, other.idMatriz)) {
            return false;
        }
        return true;
    }

}
