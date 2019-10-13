/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacao;

import Dominio.LinhaMatrizDetalhada;
import Persistencia.LinhaMatrizDetalhadaRepositorioJPAImpl;
import java.io.IOException;
import java.util.ArrayList;
import Persistencia.LinhaMatrizDetalhadaRepositorio;
import Utils.LerFicheiros;

/**
 *
 * @author hugov
 */
public class ImportarMatrizDetalhadaController {

    /**
     * Controller do UC importar matriz detalhada
     *
     * @param caminhoFicheiro Caminho do ficheiro de onde a matriz detalhada vai
     * ser lida
     * @param idMatriz Valor do id da matriz a qual as linhas vao ficar
     * associadas
     * @return Linhas de matriz detalhada lidas
     * @throws IOException
     */
    public ArrayList<LinhaMatrizDetalhada> lerLinhasDetalhadasFicheiro(String caminhoFicheiro, Long idMatriz) throws IOException {

        ArrayList<LinhaMatrizDetalhada> l1 = LerFicheiros.importarLinhasMatrizDetalhada(caminhoFicheiro, idMatriz);
        LinhaMatrizDetalhadaRepositorio repo = new LinhaMatrizDetalhadaRepositorioJPAImpl();
        for (LinhaMatrizDetalhada fa : l1) {
            repo.add(fa);
        }

        return l1;

    }
}
