/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apresentacao;

import Aplicacao.CriarMatrizRiscoController;
import eapli.framework.util.Console;
import java.util.Scanner;

/**
 *
 * @author Diogo Rolo
 */
public class CriarMatrizRiscoUI {
    
    /**
     * UI do UC criar matriz de risco
     */
    public void criarMatriz() {
        Scanner in = new Scanner(System.in);
        System.out.println("Que nome pretende para a matriz?");
        String nome = in.nextLine();
        System.out.println("Insira a data de publicação da matriz (ano-mes-dia)");
        String data = in.nextLine();
        String list[] = data.split("-");
        
        Long idLinhas = Console.readLong("Insira o id das linhas a serem usadas:");

        CriarMatrizRiscoController c = new CriarMatrizRiscoController();
        c.criarMatriz(nome, Integer.parseInt(list[0]), Integer.parseInt(list[1]), Integer.parseInt(list[2]), idLinhas);
    }
    
    
}
