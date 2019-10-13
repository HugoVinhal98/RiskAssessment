/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apresentacao;

import Aplicacao.RegistarARController;
import eapli.framework.util.Console;

/**
 *
 * @author GilTrindade
 */
public class RegistarARUI {
    private final RegistarARController controller = new RegistarARController();
    
    public void registarARUI(){
        String email = "";
        String password = "";
        String nomeUtilizador = null;
        while(controller.validarEmail(email) == false){
                 
           email = Console.readLine("Indique o seu email"); 
        }
       while(controller.validarPassword(password) == false){
        password = Console.readLine("Indique a password");
       }
       
       while(!password.equals(nomeUtilizador)){
           nomeUtilizador = Console.readLine("Indique um nome de utilizador(igual à password)");
       }
       
       controller.registarAR(nomeUtilizador, password, email); 
    }
    
}
