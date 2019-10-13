/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apresentacao;

import Aplicacao.PublicarMatrizRiscoController;
import Dominio.MatrizRisco;
import Persistencia.MatrizRiscoRepositorioJPAImpl;
import java.util.Scanner;

/**
 *
 * @author hugov
 */
public class PublicarMatrizRiscoUI {
    /**
     * UI do UC publicar matriz de risco
     */
    public void publicarMatriz() {
        
        Scanner in = new Scanner(System.in);
        MatrizRiscoRepositorioJPAImpl repo = new MatrizRiscoRepositorioJPAImpl();
        
        int cnt = 0;
        System.out.println("** Matrizes por publicar **");
        for(MatrizRisco r : repo.findAll()){
            if(r.getEstadoMatriz().getEstado() == 0){
                cnt++;
                System.out.println("Matriz " + cnt + ":  ID = " + r.getId() +  " Nome = " + r.getNome());              
            }          
        }
        
        System.out.println("");
        System.out.println("Insira o id da matriz que pretende publicar:");
        Long id = in.nextLong();

        PublicarMatrizRiscoController c = new PublicarMatrizRiscoController();
        c.publicarMatriz(id);
    }
}
