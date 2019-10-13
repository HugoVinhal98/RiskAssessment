/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacao;

import Bootstrap.Bootstrapper;
import Dominio.EstadoMatriz;
import Dominio.MatrizRisco;
import Persistencia.MatrizRiscoRepositorioJPAImpl;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hugov
 */
public class PublicarMatrizRiscoControllerTest {
    
    public PublicarMatrizRiscoControllerTest() {
    }

     /**
     * Test of publicarMatriz method, of class CriarMatrizRiscoController.
     */
    @Test
    public void testPublicarMatriz() {
        System.out.println("publicarMatriz");
        Date date = new Date();
        Long id = new Long(1);
        MatrizRiscoRepositorioJPAImpl matrizRepo = new MatrizRiscoRepositorioJPAImpl();
        PublicarMatrizRiscoController instance = new PublicarMatrizRiscoController();
        
        Bootstrapper b = new Bootstrapper();
        b.executeBootstrap();
        
        MatrizRisco expResult = matrizRepo.findById(id);
        expResult.setDataPublicacao(date);
        expResult.setEstadoMatriz(new EstadoMatriz(1));
        MatrizRisco result = instance.publicarMatriz(id);
        expResult.setId(new Long(2));
        assertEquals(expResult, result);
        
    }
    
}
