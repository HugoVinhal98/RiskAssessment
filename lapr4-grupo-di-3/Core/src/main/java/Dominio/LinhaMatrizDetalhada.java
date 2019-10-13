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
import javax.persistence.OneToOne;

/**
 *
 * @author Diogo Rolo
 */
@Entity
public class LinhaMatrizDetalhada implements Serializable {

    /**
     * Id auto gerado para identificar uma linha de matriz detalhada
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Linha de matriz caraterizada associada a uma linha de matriz detalhada
     */
    @OneToOne
    @JoinColumn(name = "idLinhaMatrizCaraterizada")
    private LinhaMatrizCaraterizada linhaMatrizCaraterizada;

    /**
     * Escala de risco de uma linha de matriz detalhada
     */
    @Embedded
    private EscalaRisco escala;

    /**
     * Valor do Id da matriz à qual a linha vai estar associada.
     */
    private Long idMatriz;

    /**
     * Construtor vazio para ser usado pelo JPA
     */
    protected LinhaMatrizDetalhada() {
    }

    /**
     * Construtor de linha de matriz detalhada
     *
     * @param linhaMatrizCaraterizada Linha de matriz caraterizada associada a
     * uma linha de matriz detalhada
     * @param escala Escala de risco de uma linha de matriz detalhada
     * @param idMatriz Id da matriz à qual a linha vai pertencer
     */
    public LinhaMatrizDetalhada(LinhaMatrizCaraterizada linhaMatrizCaraterizada, EscalaRisco escala, Long idMatriz) {
        this.linhaMatrizCaraterizada = linhaMatrizCaraterizada;
        this.escala = escala;
        this.idMatriz = idMatriz;
    }

    public Long getId() {
        return id;
    }

    public Long getIdMatriz() {
        return idMatriz;
    }

    public LinhaMatrizCaraterizada getLinhaMatrizCaraterizada() {
        return linhaMatrizCaraterizada;
    }

    public EscalaRisco getEscala() {
        return escala;
    }

    public FatorRisco getFatorRisco() {
        return this.linhaMatrizCaraterizada.getLinhaMatrizBase().getFatorRisco();
    }

    public Cobertura getCobertura() {
        return this.linhaMatrizCaraterizada.getLinhaMatrizBase().getCobertura();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLinhaMatrizCaraterizada(LinhaMatrizCaraterizada linhaMatrizCaraterizada) {
        this.linhaMatrizCaraterizada = linhaMatrizCaraterizada;
    }

    public void setEscala(EscalaRisco escala) {
        this.escala = escala;
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
        final LinhaMatrizDetalhada other = (LinhaMatrizDetalhada) obj;
        if (!Objects.equals(this.linhaMatrizCaraterizada.getId(), other.linhaMatrizCaraterizada.getId())) {
            return false;
        }
        if (!Objects.equals(this.escala.getAlto(), other.escala.getAlto()) && !Objects.equals(this.escala.getMedio(), other.escala.getMedio()) && !Objects.equals(this.escala.getBaixo(), other.escala.getBaixo())) {
            return false;
        }
        if (!Objects.equals(this.idMatriz, other.idMatriz)) {
            return false;
        }
        return true;
    }

    /**
     * Metodo toString() de linha de matriz detalhada
     *
     * @return
     */
    @Override
    public String toString() {
        return "LinhaMatrizDetalhada{" + "id=" + id + ", linhaMatrizCaraterizada=" + linhaMatrizCaraterizada + ", escala=" + escala + '}';
    }
}
