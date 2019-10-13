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
import java.util.Date;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hugov
 */
public class CriarMatrizRiscoControllerTest {
    
    public CriarMatrizRiscoControllerTest() {
    }

    /**
     * Test of criarMatriz method, of class CriarMatrizRiscoController.
     */
    @Test
    public void testCriarMatriz() {
        System.out.println("criarMatriz");
        LinhaMatrizDetalhadaRepositorioJPAImpl repo = new LinhaMatrizDetalhadaRepositorioJPAImpl();
        String nome = "MR1";
        long id = 1;
        int ano = 2019;
        int mes = 05;
        int dia = 03;
        CriarMatrizRiscoController instance = new CriarMatrizRiscoController();
        Date date = new Date(ano-1900,mes-1,dia);
        List<LinhaMatrizDetalhada> lista = repo.findAll();
                
        MatrizRisco expResult = new MatrizRisco("MR1", date, new EstadoMatriz(0), lista);
        expResult.setId(Long.parseLong("1"));
        
        MatrizRisco result = instance.criarMatriz(nome, ano, mes, dia, id);
        assertEquals(expResult, result);
        
    }

 
    
}
