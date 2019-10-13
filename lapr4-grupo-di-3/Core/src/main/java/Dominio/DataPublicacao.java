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
 * @author Diogo Rolo
 */
@Embeddable
class DataPublicacao implements Serializable {
    
    /**
     * Data de publicacao de uma matriz de risco
     */
    String datapublicacao;
    
}
