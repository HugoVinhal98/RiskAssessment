/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacao;

import Dominio.LinhaMatrizCaraterizada;
import Persistencia.LinhaMatrizCaraterizadaRepositorio;
import Persistencia.LinhaMatrizCaraterizadaRepositorioJPAImpl;
import Utils.LerFicheiros;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author franc
 */
public class ImportarMatrizCaracterizadaController {

    /**
     * Controller do UC importar matriz caraterizada
     * 
     * @param caminhoFicheiro1 Caminho do ficheiro de onde os pesos vao ser lidos
     * @param caminhoFicheiro2 Caminho do ficheiro de onde as necessidades vao ser lidos
     * @param caminhoFicheiro3 Caminho do ficheiro de onde as contribuicoes vao ser lidos
     * @param idMatriz Id da matriz à qual as linhas vao pertencer
     * @return Lista das linhas de matriz caraterizada lidas
     * @throws IOException 
     */
    public ArrayList<LinhaMatrizCaraterizada> lerLinhasCaracterizadaPesoFicheiro(String caminhoFicheiro1, String caminhoFicheiro2, String caminhoFicheiro3, Long idMatriz) throws IOException {

        ArrayList<LinhaMatrizCaraterizada> l1 = LerFicheiros.importarLinhasMatrizCaracterizada(caminhoFicheiro1, caminhoFicheiro2, caminhoFicheiro3, idMatriz);
        LinhaMatrizCaraterizadaRepositorio repo = new LinhaMatrizCaraterizadaRepositorioJPAImpl();
        for (LinhaMatrizCaraterizada lc : l1) {
            repo.add(lc);
        }

        return l1;

    }

}
