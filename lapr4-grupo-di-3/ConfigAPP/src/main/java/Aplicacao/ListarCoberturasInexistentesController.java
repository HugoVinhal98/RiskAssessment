/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacao;

import Dominio.Cobertura;
import Dominio.MatrizRisco;
import Persistencia.CoberturaRepositorio;
import Persistencia.CoberturaRepositorioJPAImpl;
import Persistencia.MatrizRiscoRepositorio;
import Persistencia.MatrizRiscoRepositorioJPAImpl;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author franc
 */
public class ListarCoberturasInexistentesController {

    private final MatrizRisco mr = new MatrizRisco();

    /**
     * Controller do UC listar coberturas
     *
     * @return Lista de coberturas
     */
    public List<Cobertura> listarCoberturas() {

        CoberturaRepositorio repo = new CoberturaRepositorioJPAImpl();

        List<Cobertura> l = repo.findAll();

        return l;

    }

    /**
     * Controller do UC listar coberturas inexistentes numa matriz
     *
     * @param mr Matriz de risco a iterar
     * @return Lista de coberturas inexistentes
     */
    public HashSet<Cobertura> coberturasInexistentes(MatrizRisco mr) {

        HashSet<Cobertura> c = mr.coberturasInexistentes(mr);

        return c;

    }

    /**
     * Retorna uma matriz dado o seu ID
     *
     * @param id id da matriz a retornar
     * @return Matriz com o id enviado
     */
    public MatrizRisco verificarExistenciaId(Long id) {

        MatrizRiscoRepositorio mrepo = new MatrizRiscoRepositorioJPAImpl();

        MatrizRisco mr1 = mrepo.findById(id);

        return mr1;
    }

}
