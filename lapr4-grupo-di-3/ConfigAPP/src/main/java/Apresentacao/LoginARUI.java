/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apresentacao;

import Aplicacao.LoginARController;
import eapli.framework.util.Console;

/**
 *
 * @author GilTrindade
 */
public class LoginARUI {

    private final LoginARController lar = new LoginARController();
    private String nome;

    public String loginARUI() {
        
        String email = "";
        String password = "";

        while (lar.validarLogin(email, password).equalsIgnoreCase("")) {
            email = Console.readLine("Indique o seu email");
            password = Console.readLine("Indique a sua password");

        }
        
        System.out.println("Bem vindo" + lar.validarLogin(email, password));
        nome = lar.validarLogin(email, password);
        return nome;

    }

}
