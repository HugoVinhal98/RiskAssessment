/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacao;

import Utils.LerFicheiros;
import java.io.IOException;

/**
 *
 * @author jmbosg
 */
public class ExportarMatrizBaseVaziaController {
    
    /**
     * Controller do UC exportar matriz base vazia
     * @param caminhoFicheiro Caminho do ficheiro onde a matriz base vai ser gravada.
     * @throws IOException 
     */
    public void exportarMatrizBaseVazia(String caminhoFicheiro) throws IOException {
        
        LerFicheiros.exportarLinhasMatrizBase(caminhoFicheiro);
        
    }
    
}
