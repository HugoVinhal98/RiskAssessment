/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacao;

import Utils.FicheirosJSON;
import Utils.LerFicheiros;

/**
 *
 * @author Diogo Rolo
 */
public class LerObjetoSeguroController {

    /**
     * Controller do UC ler objeto seguro de ficheiro JSON
     *
     * @param nomeFicheiro Nome do ficheiro JSON de onde o objeto seguro sera
     * lido
     * @throws Exception
     */
    public void lerObjetosJSON(String nomeFicheiro) throws Exception {

        FicheirosJSON fj = new FicheirosJSON();

        fj.lerObjetosSeguroJSON(nomeFicheiro);
    }
}
