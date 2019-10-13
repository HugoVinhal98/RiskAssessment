/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author hugov
 */
@Entity
public class ObjetoSeguro implements Serializable {

    /**
     * Id auto gerado para identificar um Objeto Seguro
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Nome do objeto seguro
     */
    private String nomeObjeto;

    /**
     * Lista do tipo cobertura objeto seguro
     */
    @Column
    public List<String> listaCoberturas;

    /**
     * Localização do objeto seguro
     */
    @Embedded
    private EnderecoPostal endereco;

    /**
     * Construtor vazio para ser usado pelo JPA
     */
    public ObjetoSeguro() {
    }

    public ObjetoSeguro(String nomeObjeto, List<String> cobertura, EnderecoPostal end) {
        this.nomeObjeto = nomeObjeto;
        this.listaCoberturas = cobertura;
        this.endereco = end;
    }

    public ObjetoSeguro(EnderecoPostal endereco) {
        this.endereco = endereco;
    }

    public ObjetoSeguro(String nomeObjeto, List<String> listaCoberturas) {
        this.nomeObjeto = nomeObjeto;
        this.listaCoberturas = listaCoberturas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeObjeto() {
        return nomeObjeto;
    }

    public void setNomeObjeto(String nomeObjeto) {
        this.nomeObjeto = nomeObjeto;
    }

    public List<String> getListaCoberturas() {
        return listaCoberturas;
    }

    public void setListaCoberturas(List<String> listaCoberturas) {
        this.listaCoberturas = listaCoberturas;
    }

    public EnderecoPostal getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoPostal endereco) {
        this.endereco = endereco;
    }

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
        final ObjetoSeguro other = (ObjetoSeguro) obj;
        if (!Objects.equals(this.nomeObjeto, other.nomeObjeto)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.listaCoberturas, other.listaCoberturas)) {
            return false;
        }
        if (!Objects.equals(this.endereco, other.endereco)) {
            return false;
        }
        return true;
    }

    /**
     * Metodo toString() do objeto seguro
     *
     * @return
     */
    @Override
    public String toString() {
        return "ObjetoSeguro{" + "id=" + id + ", nomeObjeto=" + nomeObjeto + ", listaCoberturas=" + listaCoberturas + ", endereco=" + endereco + '}';
    }

}
