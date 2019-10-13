/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import javax.persistence.Embeddable;

/**
 *
 * @author Jmbosg
 */
@Embeddable
public class TipoTempo {

    /**
     * Tipo de tempo (baixo, medio, alto) de uma envolventeOS em relaca com um
     * OS.
     */
    private String tipoTempo;

    /**
     * Construtor de TipoTempo
     *
     * @param tipoTempo Tipo de tempo (baixo, medio, alto)
     */
    public TipoTempo(String tipoTempo) {
        this.tipoTempo = tipoTempo;
    }
    
    /**
     * Construtor vazio para ser usado pelo JPA
     */
    public TipoTempo() {
    }

    public String getTipoTempo() {
        return tipoTempo;
    }

}
