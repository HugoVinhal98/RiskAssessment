/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Jmbosg
 */
public class ImprimirPedidosAnalistaDTO {

    private Long pedido;

    private String tempoDecorridoDesdeAtribuicao;

    private String dataCriacaoPedido;

    public Long getPedido() {
        return pedido;
    }

    public void setPedido(Long pedido) {
        this.pedido = pedido;
    }

    public String getTempoDecorridoDesdeAtribuicao() {
        return tempoDecorridoDesdeAtribuicao;
    }

    public void setTempoDecorridoDesdeAtribuicao(String tempoDecorridoDesdeAtribuicao) {
        this.tempoDecorridoDesdeAtribuicao = tempoDecorridoDesdeAtribuicao;
    }

    public String getDataCriacaoPedido() {
        return dataCriacaoPedido;
    }

    public void setDataCriacaoPedido(String dataCriacaoPedido) {
        this.dataCriacaoPedido = dataCriacaoPedido;
    }

    public ImprimirPedidosAnalistaDTO(Long pedido, String tempoDecorridoDesdeAtribuicao, String dataCriacaoPedido) {
        this.pedido = pedido;
        this.tempoDecorridoDesdeAtribuicao = tempoDecorridoDesdeAtribuicao;
        this.dataCriacaoPedido = dataCriacaoPedido;
    }

}
