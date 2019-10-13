/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author franc
 */
public class ImprimirResultadoDTO {
    
    /**
     * Informação enviada por HTTP( mensagem com os dados do pedido ou uma mensagem de erro sugestiva )
     */
    private String content;
    
    /**
     * Status da mensagem, 404 para erro 200 para sucesso de envio
     */
    private String status;

    public ImprimirResultadoDTO(String content, String status) {
        this.content = content;
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public String getStatus() {
        return status;
    }
    
    
    
}
