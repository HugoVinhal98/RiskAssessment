/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Diogo Rolo
 */
public class LinhaMatrizDetalhadaTest {
    
    public LinhaMatrizDetalhadaTest() {
    }

    /**
     * Test of getId method, of class LinhaMatrizDetalhada.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        LinhaMatrizDetalhada instance = new LinhaMatrizDetalhada();
        Long expResult = null;
        Long result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLinhaMatrizCaraterizada method, of class LinhaMatrizDetalhada.
     */
    @Test
    public void testGetLinhaMatrizCaraterizada() {
        System.out.println("getLinhaMatrizCaraterizada");
        LinhaMatrizDetalhada instance = new LinhaMatrizDetalhada();
        LinhaMatrizCaraterizada expResult = null;
        LinhaMatrizCaraterizada result = instance.getLinhaMatrizCaraterizada();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEscala method, of class LinhaMatrizDetalhada.
     */
    @Test
    public void testGetEscala() {
        System.out.println("getEscala");
        LinhaMatrizCaraterizada linhaMatrizCaraterizada = new LinhaMatrizCaraterizada();
        long id = 1;
        EscalaRisco escala = new EscalaRisco(2, 3, 4);
        LinhaMatrizDetalhada instance = new LinhaMatrizDetalhada(linhaMatrizCaraterizada, escala, id);
        EscalaRisco expResult = escala;
        EscalaRisco result = instance.getEscala();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class LinhaMatrizDetalhada.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Long id = 1L;
        LinhaMatrizDetalhada instance = new LinhaMatrizDetalhada();
        instance.setId(id);
    }


    /**
     * Test of setEscala method, of class LinhaMatrizDetalhada.
     */
    @Test
    public void testSetEscala() {
        System.out.println("setEscala");
        EscalaRisco escala = new EscalaRisco(2, 3, 4);
        LinhaMatrizDetalhada instance = new LinhaMatrizDetalhada();
        instance.setEscala(escala);
    }
    
}
