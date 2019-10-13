/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apresentacao;


import Aplicacao.CompararAvaliacaoRiscoController;
import java.util.Scanner;

/**
 *
 * @author Francisco Negrão
 */
public class CompararAvaliacaoRiscoUI {
    /**
     * Controller do UC comparar Avaliacao de Risco
     */
    private final CompararAvaliacaoRiscoController controller = new CompararAvaliacaoRiscoController();
    /**
     * UI do UC comparar avaliacao de risco 
     */
    public void compararAvaliacaoRisco() {
        Scanner in = new Scanner(System.in);
        
        System.out.println("Insira o id da primeira matriz");
        Long idMatriz1 = in.nextLong();
        
        System.out.println("Insira o id da segunda matriz");
        Long idMatriz2 = in.nextLong();
        
        System.out.println("Insira o id do pedido");
        Long idPedido = in.nextLong();
        
        controller.compararAvaliacaoRisco(idMatriz1, idMatriz2, idPedido);
    }
    
}
