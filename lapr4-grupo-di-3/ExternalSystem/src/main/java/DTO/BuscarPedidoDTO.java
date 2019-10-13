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
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author franc
 */
public class BuscarPedidoDTO implements Serializable {

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
    /**
     * indice ar
     */
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
     * Localizacao do objeto seguro
     */
    private String localizacao;
    /**
     * Lista de coberturas de um objeto seguro
     */
    private List<String> listaCoberturasOS;
    /**
     * Data de publicacao de pedido
     */
    private Date dataPedido;
    /**
     * Status
     */
    private String status;

    // private AvaliacaoRisco ar;
    // private ObjetoSeguro os;
    public BuscarPedidoDTO(Long idPedido, Long idAvaliacaoRisco, ScoreMaximo sm, ScoreObtido so, IndiceAvaliacaoRisco indAR, Long idObjetoSeguro, String nomeOS, List<String> listaCoberturasOS, String status) {
        this.idPedido = idPedido;
        this.idAvaliacaoRisco = idAvaliacaoRisco;
        this.sm = sm;
        this.so = so;
        this.indAR = indAR;
        this.idObjetoSeguro = idObjetoSeguro;
        this.nomeOS = nomeOS;
        this.listaCoberturasOS = listaCoberturasOS;
        this.status = status;
    }

    public BuscarPedidoDTO(Long idPedido, Long idAvaliacaoRisco, ScoreMaximo sm, ScoreObtido so, IndiceAvaliacaoRisco indAR, Long idObjetoSeguro, String nomeOS, String localizacao, List<String> listaCoberturasOS, String status) {
        this.idPedido = idPedido;
        this.idAvaliacaoRisco = idAvaliacaoRisco;
        this.sm = sm;
        this.so = so;
        this.indAR = indAR;
        this.idObjetoSeguro = idObjetoSeguro;
        this.nomeOS = nomeOS;
        this.localizacao = localizacao;
        this.listaCoberturasOS = listaCoberturasOS;
        this.status = status;
    }

    public BuscarPedidoDTO(Long idPedido, Long idAvaliacaoRisco, ScoreMaximo sm, ScoreObtido so, IndiceAvaliacaoRisco indAR, Long idObjetoSeguro, String nomeOS, String localizacao, List<String> listaCoberturasOS, Date dataPedido, String status) {
        this.idPedido = idPedido;
        this.idAvaliacaoRisco = idAvaliacaoRisco;
        this.sm = sm;
        this.so = so;
        this.indAR = indAR;
        this.idObjetoSeguro = idObjetoSeguro;
        this.nomeOS = nomeOS;
        this.localizacao = localizacao;
        this.listaCoberturasOS = listaCoberturasOS;
        this.dataPedido = dataPedido;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public Date getDataPedido() {
        return dataPedido;
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
        final BuscarPedidoDTO other = (BuscarPedidoDTO) obj;
        if (!Objects.equals(this.nomeOS, other.nomeOS)) {
            return false;
        }
        if (!Objects.equals(this.localizacao, other.localizacao)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.idPedido, other.idPedido)) {
            return false;
        }
        if (!Objects.equals(this.idAvaliacaoRisco, other.idAvaliacaoRisco)) {
            return false;
        }
        if (!Objects.equals(this.sm, other.sm)) {
            return false;
        }
        if (!Objects.equals(this.so, other.so)) {
            return false;
        }
        if (!Objects.equals(this.indAR, other.indAR)) {
            return false;
        }
        if (!Objects.equals(this.idObjetoSeguro, other.idObjetoSeguro)) {
            return false;
        }
        if (!Objects.equals(this.listaCoberturasOS, other.listaCoberturasOS)) {
            return false;
        }
        if (!Objects.equals(this.dataPedido, other.dataPedido)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    

}
