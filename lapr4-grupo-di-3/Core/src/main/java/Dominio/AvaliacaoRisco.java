/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.io.Serializable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author franc
 */
@Entity
public class AvaliacaoRisco implements Serializable {

    /**
     * Id auto gerado que identifica uma avaliação de risco
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Objeto Seguro a ser realizada a avaliação de risco
     */
    private ObjetoSeguro objetoSeguro;

    /**
     * Valor do score Obtido
     */
    @OneToOne
    @Embedded
    private ScoreObtido scoreObtido;

    /**
     * Valor do score Maximo
     */
    @OneToOne
    @Embedded
    private ScoreMaximo scoreMaximo;

    /**
     * Valor do indice de avaliação de risco ( obtido/maximo )
     */
    @OneToOne
    @Embedded
    private IndiceAvaliacaoRisco indiceAvalicaoRisco;

    public AvaliacaoRisco(ObjetoSeguro objetoSeguro, ScoreObtido scoreObtido, ScoreMaximo scoreMaximo, IndiceAvaliacaoRisco indiceAvalicaoRisco) {
        this.objetoSeguro = objetoSeguro;
        this.scoreObtido = scoreObtido;
        this.scoreMaximo = scoreMaximo;
        this.indiceAvalicaoRisco = indiceAvalicaoRisco;
    }

    /**
     * Construtor vazio para ser usado pelo JPA
     */
    public AvaliacaoRisco() {
    }

    public Long getId() {
        return id;
    }

    public ObjetoSeguro getObjetoSeguro() {
        return objetoSeguro;
    }

    public ScoreObtido getScoreObtido() {
        return scoreObtido;
    }

    public ScoreMaximo getScoreMaximo() {
        return scoreMaximo;
    }

    public IndiceAvaliacaoRisco getIndiceAvalicaoRisco() {
        return indiceAvalicaoRisco;
    }

}
