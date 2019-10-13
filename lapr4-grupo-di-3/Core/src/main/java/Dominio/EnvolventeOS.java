/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.io.Serializable;

/**
 *
 * @author Jmbosg
 */
public class EnvolventeOS implements Serializable {

    /**
     * Nome da envolvente OS
     */
    private String nome;

    /**
     * Descricao da envolvente OS
     */
    private String descricao;

    /**
     * Tipo de distancia (baixo, medio, alto) da envolvente OS tendo em conta o
     * seu OS
     */
    private TipoDistancia tipoDistancia;
    
    /**
     * Tipo de tempo (baixo, medio, alto) da envolvente OS tendo em conta o
     * seu OS
     */
    private TipoTempo tipoTempo;

    /**
     * Construtor vazio para ser usado pelo JPA
     */
    protected EnvolventeOS() {
    }

    /**
     * Construtor de envolvente OS.
     *
     * @param nome Nome da envolvente
     * @param descricao Descricao da envolvente
     * @param tipoDistancia Tipo de distancia da envolvente em relacao ao seu OS
     * @param tipoTempo Tipo de tempo da envolvente em relacao ao seu OS
     */
    public EnvolventeOS(String nome, String descricao, TipoDistancia tipoDistancia, TipoTempo tipoTempo) {
        this.nome = nome;
        this.descricao = descricao;
        this.tipoDistancia = tipoDistancia;
        this.tipoTempo = tipoTempo;
    }

    public String getNome() {
        return nome;
    }

    public TipoDistancia getTipoDistancia() {
        return tipoDistancia;
    }

    public TipoTempo getTipoTempo() {
        return tipoTempo;
    }
    
}
