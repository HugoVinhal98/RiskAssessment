/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacao;

import Dominio.LinhaMatrizBase;
import Persistencia.LinhaMatrizBaseRepositorioJPAImpl;
import java.io.IOException;
import Persistencia.LinhaMatrizBaseRepositorio;
import Utils.LerFicheiros;
import java.util.List;

/**
 *
 * @author jmbosg
 */
public class ImportarMatrizBaseController {

    /**
     * Controller do UC importar matriz base
     *
     * @param caminhoFicheiro Caminho do ficheiro de onde as linhas vao ser
     * lidas
     * @param idMatriz Id da matriz a qual as linhas vão ficar associadas
     * @throws IOException
     */
    public void lerLinhasBaseFicheiro(String caminhoFicheiro, Long idMatriz) throws IOException {

        List<LinhaMatrizBase> l1 = LerFicheiros.importarLinhasMatrizBase(caminhoFicheiro, idMatriz);
        LinhaMatrizBaseRepositorio repo = new LinhaMatrizBaseRepositorioJPAImpl();
        for (LinhaMatrizBase fa : l1) {
            repo.add(fa);
        }

    }

}
