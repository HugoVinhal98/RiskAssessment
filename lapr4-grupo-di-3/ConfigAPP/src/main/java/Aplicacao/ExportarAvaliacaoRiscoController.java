/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacao;

import Utils.FicheirosJSON;
import java.io.IOException;

/**
 *
 * @author Francisco Negrão
 */
public class ExportarAvaliacaoRiscoController {

    /**
     * Controller do UC Exportar Avaliacao de Risco
     *
     * @param id Id do pedido que possui a avaliacao de risco a exportar
     * @param nomeFicheiro Nome do ficheiro para onde a avaliacao de risco sera
     * exportada
     * @throws IOException
     */
    public void exportarAvaliacaoRisco(long id, String nomeFicheiro) throws IOException {
        FicheirosJSON.exportarAvaliacaoRiscoJSON(id, nomeFicheiro);
    }
}
