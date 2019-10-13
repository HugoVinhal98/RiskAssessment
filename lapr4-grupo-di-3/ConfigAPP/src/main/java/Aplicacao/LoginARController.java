/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacao;

import Dominio.AnalistaRisco;
import Dominio.NomeUtilizador;
import Persistencia.AnalistaRiscoRepositorioJPAImpl;
import java.util.List;

/**
 *
 * @author GilTrindade
 */
public class LoginARController {

    /**
     * Método para validar o login de um analista de risco.
     *
     * @param email
     * @param password
     * @return
     */
    public String validarLogin(String email, String password) {
        AnalistaRisco ar = new AnalistaRisco();
        return ar.validarLogin(email, password);
    }

}
