/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Persistencia.CoberturaRepositorio;
import Persistencia.CoberturaRepositorioJPAImpl;
import Persistencia.FatorDeRiscoRepositorio;
import Persistencia.MatrizRiscoRepositorio;
import Persistencia.FatorDeRiscoRepositorioJPAImpl;
import Persistencia.MatrizRiscoRepositorioJPAImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Diogo Rolo
 */
@Entity
public class MatrizRisco implements Serializable {

    /**
     * Id auto gerado que identifica uma matriz de risco
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome da matriz
     */
    private String nome;

    /**
     * Data de publicacao estipulada
     */
    @Temporal(TemporalType.DATE)
    private Date DataPublicacao;

    /**
     * Estado atual da matriz
     */
    @Embedded
    private EstadoMatriz estadoMatriz;

    /**
     * Lista de linhas da matriz
     */
    @Column
    public ListLinhaMatrizDetalhada linhasMatrizDetalhada = new ListLinhaMatrizDetalhada();

    /**
     * Construtor vazio para ser usado pelo JPA
     */
    public MatrizRisco() {
    }

    /**
     * Construtor de matriz de risco
     *
     * @param nome Nome da matriz
     * @param DataPublicacao Data de publicacao da matriz
     * @param estadoMatriz Estado da matriz
     * @param linhasMatrizDetalhada Lista de linhas que vao pertencer a matriz
     */
    public MatrizRisco(String nome, Date DataPublicacao, EstadoMatriz estadoMatriz, List<LinhaMatrizDetalhada> linhasMatrizDetalhada) {
        this.nome = nome;
        this.DataPublicacao = DataPublicacao;
        this.estadoMatriz = estadoMatriz;
        this.linhasMatrizDetalhada.setList(linhasMatrizDetalhada);
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Date getDataPublicacao() {
        return DataPublicacao;
    }

    public EstadoMatriz getEstadoMatriz() {
        return estadoMatriz;
    }

    public List<LinhaMatrizDetalhada> getLinhasMatrizDetalhada() {
        return linhasMatrizDetalhada.getList();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataPublicacao(Date DataPublicacao) {
        this.DataPublicacao = DataPublicacao;
    }

    public void setEstadoMatriz(EstadoMatriz estadoMatriz) {
        this.estadoMatriz = estadoMatriz;
    }

    public void setLinhasMatrizDetalhada(List<LinhaMatrizDetalhada> linhasMatrizDetalhada) {
        this.linhasMatrizDetalhada.setList(linhasMatrizDetalhada);
    }

    /**
     * Metodo toString() de matriz risco
     *
     * @return
     */
    @Override
    public String toString() {
        return "MatrizRisco{" + "id=" + id + ", nome=" + nome + ", DataPublicacao=" + DataPublicacao + ", estadoMatriz=" + estadoMatriz + ", linhasMatrizDetalhada=" + linhasMatrizDetalhada + '}';
    }

    /**
     * Metodo hashCode() de matriz risco
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
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
        final MatrizRisco other = (MatrizRisco) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.DataPublicacao.getDate(), other.DataPublicacao.getDate())) {
            return false;
        }
        if (!Objects.equals(this.estadoMatriz.getEstado(), other.estadoMatriz.getEstado())) {
            return false;
        }
        if (!(this.linhasMatrizDetalhada.equals(other.linhasMatrizDetalhada))) {
            return false;
        }
        return true;
    }

    /**
     * Pegando em todas as coberturas persistidas na BD, compara com as coberturas
     * usadas na matriz de risco passada por parametro e vê quais não são usadas
     * enviando-as para uma lista
     * 
     * @param mr
     * @return 
     */
    public HashSet<Cobertura> coberturasInexistentes(MatrizRisco mr) {

        MatrizRiscoRepositorio m = new MatrizRiscoRepositorioJPAImpl();

        CoberturaRepositorio cr = new CoberturaRepositorioJPAImpl();
        List<Cobertura> all = cr.findAll();

        HashSet<Cobertura> coberturasInexistentes = new HashSet<>();
        List<Long> coberturasExistentes = new ArrayList<>();

        for (LinhaMatrizDetalhada l : mr.linhasMatrizDetalhada.getList()) {
            coberturasExistentes.add(l.getCobertura().getId());
        }

        for (Cobertura c : all) {
            if (!(coberturasExistentes.contains(c.getId()))) {
                coberturasInexistentes.add(c);
            }
        }

        return coberturasInexistentes;
    }

}
