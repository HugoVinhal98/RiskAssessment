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
 * @author Jmbosg
 */
@Embeddable
public class TipoDistancia implements Serializable {

    /**
     * Tipo de distancia (baixo, medio, alto) de uma envolventeOS em relacao com
     * um OS.
     */
    private String tipoDistancia;
    
    /**
     * Construtor de TipoDistancia
     * @param tipoDistancia Tipo de distancia (baixo, medio, alto)
     */
    public TipoDistancia(String tipoDistancia) {
        this.tipoDistancia = tipoDistancia;
    }
    
    /**
     * Construtor vazio para ser usado pelo JPa
     */
    public TipoDistancia() {
    }
    
    public String getTipoDistancia() {
        return tipoDistancia;
    }

}
