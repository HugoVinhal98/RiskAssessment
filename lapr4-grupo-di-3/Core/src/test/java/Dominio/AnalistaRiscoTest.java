/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Persistencia.AnalistaRiscoRepositorioJPAImpl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author GilTrindade
 */
public class AnalistaRiscoTest {
    
    public AnalistaRiscoTest() {
    }
    
    /**
     * Test of validarPassword method, of class AnalistaRisco.
     */
    @Test
    public void testValidarPasswordTamanho() {
        System.out.println("validarPassword");
        String password = "Gil1";
        AnalistaRisco instance = new AnalistaRisco();
        boolean expResult = false;
        boolean result = instance.validarPassword(password);
        assertEquals(expResult, result);
       
    }
       /**
     * Test of validarPassword method, of class AnalistaRisco.
     */
    @Test
    public void testValidarPasswordMaiuscula() {
        System.out.println("validarPassword");
        String password = "gil1111";
        AnalistaRisco instance = new AnalistaRisco();
        boolean expResult = false;
        boolean result = instance.validarPassword(password);
        assertEquals(expResult, result);
       
    }
       /**
     * Test of validarPassword method, of class AnalistaRisco.
     */
    @Test
    public void testValidarPasswordMinuscula() {
        System.out.println("validarPassword");
        String password = "GIL11111";
        AnalistaRisco instance = new AnalistaRisco();
        boolean expResult = false;
        boolean result = instance.validarPassword(password);
        assertEquals(expResult, result);
       
    }
      /**
     * Test of validarPassword method, of class AnalistaRisco.
     */
    @Test
    public void testValidarPasswordNumero() {
        System.out.println("validarPassword");
        String password = "Gillllll";
        AnalistaRisco instance = new AnalistaRisco();
        boolean expResult = false;
        boolean result = instance.validarPassword(password);
        assertEquals(expResult, result);
       
    }
          /**
     * Test of validarPassword method, of class AnalistaRisco.
     */
    @Test
    public void testValidarPasswordCorreto() {
        System.out.println("validarPassword");
        String password = "Gillllll1";
        AnalistaRisco instance = new AnalistaRisco();
        boolean expResult = true;
        boolean result = instance.validarPassword(password);
        assertEquals(expResult, result);
       
    }


    /**
     * Test of validarEmail method, of class AnalistaRisco.
     */
    @Test
    public void testValidarEmail() {
        System.out.println("validarEmail");
        String email = "gil";
        AnalistaRisco instance = new AnalistaRisco();
        boolean expResult = false;
        boolean result = instance.validarEmail(email);
        assertEquals(expResult, result);
      
    }
    
    /**
     * Test of validarEmail method, of class AnalistaRisco.
     */
    @Test
    public void testValidarEmailCorreto() {
        System.out.println("validarEmail");
        String email = "gil@gmail.com";
        AnalistaRisco instance = new AnalistaRisco();
        boolean expResult = true;
        boolean result = instance.validarEmail(email);
        assertEquals(expResult, result);
      
    }

}
