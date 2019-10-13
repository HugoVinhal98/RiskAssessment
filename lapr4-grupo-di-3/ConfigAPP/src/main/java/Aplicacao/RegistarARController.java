/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacao;

import Dominio.AnalistaRisco;
import Dominio.Email;
import Dominio.NomeUtilizador;
import Dominio.Password;
import Persistencia.AnalistaRiscoRepositorioJPAImpl;

/**
 *
 * @author GilTrindade
 */
public class RegistarARController {

    /**
     * Método para validar o email mediante vários critérios.
     *
     * @param email
     * @return
     */
    public boolean validarEmail(String email) {
        AnalistaRisco ana = new AnalistaRisco();

        return ana.validarEmail(email);

    }

    /**
     * Método para validar a password mediante vários critérios.
     *
     * @param password
     * @return
     */
    public boolean validarPassword(String password) {
        AnalistaRisco aR = new AnalistaRisco();
        return aR.validarPassword(password);

    }

    /**
     * Método para registar o Analista de Risco na base de dados.
     *
     * @param username
     * @param password
     * @param email
     */
    public void registarAR(String username, String password, String email) {
        AnalistaRiscoRepositorioJPAImpl repo = new AnalistaRiscoRepositorioJPAImpl();
        AnalistaRisco ar = new AnalistaRisco(new NomeUtilizador(username), new Password(password), new Email(email));
        repo.add(ar);
    }

}
