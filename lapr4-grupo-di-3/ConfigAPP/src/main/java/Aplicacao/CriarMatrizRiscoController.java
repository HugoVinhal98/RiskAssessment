/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacao;

import Dominio.EstadoMatriz;
import Dominio.LinhaMatrizDetalhada;
import Dominio.MatrizRisco;
import Persistencia.LinhaMatrizDetalhadaRepositorioJPAImpl;
import Persistencia.MatrizRiscoRepositorioJPAImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Diogo Rolo
 */
public class CriarMatrizRiscoController {

    /**
     * Criar uma matriz de risco nova ainda não publicada.
     *
     * @param nome nome da matriz
     * @param ano ano em que a matriz deve ser publicada
     * @param mes mes em que a matriz deve ser publicada
     * @param dia dia em que a matriz deve ser publicada
     * @param idLinhas Id das linhas a ser usado
     * @return matriz de risco criada
     */
    public MatrizRisco criarMatriz(String nome, int ano, int mes, int dia, Long idLinhas) {

        MatrizRiscoRepositorioJPAImpl matrizRepo = new MatrizRiscoRepositorioJPAImpl();
        LinhaMatrizDetalhadaRepositorioJPAImpl repo = new LinhaMatrizDetalhadaRepositorioJPAImpl();
        ano = ano - 1900;
        mes = mes - 1;
        Date date = new Date(ano, mes, dia);
        List<LinhaMatrizDetalhada> listaRepo = repo.findAll();

        List<LinhaMatrizDetalhada> lista = new ArrayList<>();

        for (LinhaMatrizDetalhada l : listaRepo) {
            if (l.getIdMatriz() == idLinhas) {
                lista.add(l);
            }

        }

        MatrizRisco mr = new MatrizRisco(nome, date, new EstadoMatriz(0), lista);

        matrizRepo.add(mr);
        if (mr.getId() == null) {
            System.out.println("Matriz já existe na base de dados!");
        } else {
            System.out.println("Matriz '" + mr.getNome() + "' criada com sucesso! ID = " + mr.getId() + ".");
        }
        return mr;
    }

}
