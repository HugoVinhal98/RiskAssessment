/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.List;

/**
 *
 * @author hugov
 */
public class ComparacaoArDTO {

    /**
     * id do pedido
     */
    private final Long idPedido;

    /**
     * id da matriz 1 
     */
    private final Long idMatriz1;

    /**
     * id da matriz 2
     */
    private final Long idMatriz2;

    /**
     * score obtido pela matriz 1
     */
    private final int scoreO1;

    /**
     * score maximo da matriz 1
     */
    private final int scoreM1;

    /**
     * score obtido pela matriz 2
     */
    private final int scoreO2;

    /**
     * score maximo da matriz 2
     */
    private final int scoreM2;

    /**
     * indice de avaliacao da matriz 1
     */
    private final float indiceAvaliacao1;

    /**
     * indice de avaliacao da matiz 2
     */
    private final float indiceAvaliacao2;

    /**
     * local de avaliação de risco
     */
    private final String local;

    /**
     * lista de coberturas do local
     */
    private List<String> coberturas;

    public ComparacaoArDTO(Long idPedido, Long idMatriz1, Long idMatriz2, int scoreO1, int scoreM1, int scoreO2, int scoreM2, float indiceAvaliacao1, float indiceAvaliacao2, String local, List<String> coberturas) {
        this.idPedido = idPedido;
        this.idMatriz1 = idMatriz1;
        this.idMatriz2 = idMatriz2;
        this.scoreO1 = scoreO1;
        this.scoreM1 = scoreM1;
        this.scoreO2 = scoreO2;
        this.scoreM2 = scoreM2;
        this.indiceAvaliacao1 = indiceAvaliacao1;
        this.indiceAvaliacao2 = indiceAvaliacao2;
        this.local = local;
        this.coberturas = coberturas;
    }

    public String getLocal() {
        return local;
    }

    public List<String> getCoberturas() {
        return coberturas;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public Long getIdMatriz1() {
        return idMatriz1;
    }

    public Long getIdMatriz2() {
        return idMatriz2;
    }

    public int getScoreO1() {
        return scoreO1;
    }

    public int getScoreM1() {
        return scoreM1;
    }

    public int getScoreO2() {
        return scoreO2;
    }

    public int getScoreM2() {
        return scoreM2;
    }

    public float getIndiceAvaliacao1() {
        return indiceAvaliacao1;
    }

    public float getIndiceAvaliacao2() {
        return indiceAvaliacao2;
    }

}
