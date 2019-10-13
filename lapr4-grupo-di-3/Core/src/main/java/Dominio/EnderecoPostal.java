/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;

/**
 *
 * @author Diogo Rolo
 */
@Embeddable
public class EnderecoPostal implements Serializable{
    
    private String distrito;
    private String pais;
    private String ruaPorta;
    private String codigoPostal;
    private String localidade;

    public EnderecoPostal(String distrito, String pais, String ruaPorta, String codigoPostal, String localidade) {
        this.distrito = distrito;
        this.pais = pais;
        this.ruaPorta = ruaPorta;
        this.codigoPostal = codigoPostal;
        this.localidade = localidade;
    }

    public EnderecoPostal(String localidade) {
        this.localidade = localidade;
    }
    
    public EnderecoPostal() {
    }

    public String getDistrito() {
        return distrito;
    }

    public String getPais() {
        return pais;
    }

    
    public String getRuaPorta() {
        return ruaPorta;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setRuaPorta(String ruaPorta) {
        this.ruaPorta = ruaPorta;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public void setPais(String pais) {
        this.pais = pais;
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
        final EnderecoPostal other = (EnderecoPostal) obj;
        if (!Objects.equals(this.ruaPorta, other.ruaPorta)) {
            return false;
        }
        if (!Objects.equals(this.codigoPostal, other.codigoPostal)) {
            return false;
        }
        if (!Objects.equals(this.localidade, other.localidade)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}

