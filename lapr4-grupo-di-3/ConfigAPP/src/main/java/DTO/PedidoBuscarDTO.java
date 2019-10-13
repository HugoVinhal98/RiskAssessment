/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import Dominio.AvaliacaoRisco;
import Dominio.IndiceAvaliacaoRisco;
import Dominio.ObjetoSeguro;
import Dominio.ScoreMaximo;
import Dominio.ScoreObtido;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author franc
 */
public class PedidoBuscarDTO implements Serializable {

    /**
     * ID identificador do Pedido
     */
    private Long idPedido;

    /**
     * ID identificador da Avaliação de Risco
     */
    private Long idAvaliacaoRisco;

    /**
     * ScoreMaximo da avalição de risco
     */
    private ScoreMaximo sm;

    /**
     * ScoreObtido da avaliação de risco
     */
    private ScoreObtido so;

    private IndiceAvaliacaoRisco indAR;

    /**
     * ID identificador do objeto seguro
     */
    private Long idObjetoSeguro;

    /**
     * Nome do objeto seguro
     */
    private String nomeOS;

    /**
     * Lista de coberturas de um objeto seguro
     */
    private List<String> listaCoberturasOS;

    // private AvaliacaoRisco ar;
    // private ObjetoSeguro os;
    public PedidoBuscarDTO(Long idPedido, Long idAvaliacaoRisco, ScoreMaximo sm, ScoreObtido so, IndiceAvaliacaoRisco indAR, Long idObjetoSeguro, String nomeOS, List<String> listaCoberturasOS) {
        this.idPedido = idPedido;
        this.idAvaliacaoRisco = idAvaliacaoRisco;
        this.sm = sm;
        this.so = so;
        this.indAR = indAR;
        this.idObjetoSeguro = idObjetoSeguro;
        this.nomeOS = nomeOS;
        this.listaCoberturasOS = listaCoberturasOS;

    }

    public Long getIdPedido() {
        return idPedido;
    }

    public Long getIdAvaliacaoRisco() {
        return idAvaliacaoRisco;
    }

    public ScoreMaximo getSm() {
        return sm;
    }

    public ScoreObtido getSo() {
        return so;
    }

    public IndiceAvaliacaoRisco getIndAR() {
        return indAR;
    }

    public Long getIdObjetoSeguro() {
        return idObjetoSeguro;
    }

    public String getNomeOS() {
        return nomeOS;
    }

    public List<String> getListaCoberturasOS() {
        return listaCoberturasOS;
    }

}
