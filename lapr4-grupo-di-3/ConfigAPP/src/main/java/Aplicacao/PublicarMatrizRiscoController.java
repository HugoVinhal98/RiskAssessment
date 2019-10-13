/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacao;

import Dominio.EstadoMatriz;
import Dominio.MatrizRisco;
import Persistencia.MatrizRiscoRepositorioJPAImpl;
import java.util.Date;

/**
 *
 * @author hugov
 */
public class PublicarMatrizRiscoController {

    /**
     * Publicar uma matriz existente na base de dados atraves do id
     *
     * @param id id da matriz que o utilizador pretende publicar
     * @return matriz de risco publicada
     */
    public MatrizRisco publicarMatriz(Long id) {

        MatrizRiscoRepositorioJPAImpl repoMatrix = new MatrizRiscoRepositorioJPAImpl();
        Date date = new Date();

        MatrizRisco mr = repoMatrix.findById(id);
        repoMatrix.remove(id);

        mr.setEstadoMatriz(new EstadoMatriz(1));
        mr.setDataPublicacao(date);

        for (MatrizRisco matriz : repoMatrix.findAll()) {
            if (matriz.getEstadoMatriz().getEstado() == 1) {
                MatrizRisco matrizPub = matriz;
                repoMatrix.remove(matrizPub.getId());
                matrizPub.setEstadoMatriz(new EstadoMatriz(2));
                repoMatrix.add(matrizPub);
            }
        }
        repoMatrix.add(mr);

        System.out.println("Matriz '" + mr.getNome() + "' publicada com sucesso! ID = " + mr.getId() + ".");

        return mr;

    }
}
