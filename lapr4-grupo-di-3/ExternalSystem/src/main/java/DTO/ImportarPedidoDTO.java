/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.List;

/**
 *
 * @author Jmbosg
 */
public class ImportarPedidoDTO {

    private String nomeObjeto;

    private List<String> coberturas;

    private String necessidadeAnalista;

    public ImportarPedidoDTO(String nomeObjeto, List<String> coberturas, String necessidadeAnalista) {
        this.nomeObjeto = nomeObjeto;
        this.coberturas = coberturas;
        this.necessidadeAnalista = necessidadeAnalista;
    }

    public String getNomeObjeto() {
        return nomeObjeto;
    }

    public List<String> getCoberturas() {
        return coberturas;
    }

    public String getNecessidadeAnalista() {
        return necessidadeAnalista;
    }

}
