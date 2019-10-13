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
public class FatorRiscoTest {
    
    public FatorRiscoTest() {
    }

    /**
     * Test of getMetrica method, of class FatorRisco.
     */
    @Test
    public void testGetMetrica() {
        System.out.println("getMetrica");
        Envolvente envolvente = new Envolvente("nome");
        Metrica metrica = new Metrica("des");
        FatorRisco instance = new FatorRisco(envolvente, metrica);
        Metrica expResult = metrica;
        Metrica result = instance.getMetrica();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEnvolvente method, of class FatorRisco.
     */
    @Test
    public void testGetEnvolvente() {
        System.out.println("getEnvolvente");
        Envolvente envolvente = new Envolvente("des");
        Metrica metrica = new Metrica("des");
        FatorRisco instance = new FatorRisco(envolvente, metrica);
        Envolvente result = instance.getEnvolvente();
        Envolvente expResult = envolvente;
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class FatorRisco.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        FatorRisco instance = new FatorRisco();
        Long expResult = null;
        Long result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNomeEnvolvente method, of class FatorRisco.
     */
    @Test
    public void testGetNomeEnvolvente() {
        System.out.println("getNomeEnvolvente");
        Envolvente e = new Envolvente("nome");
        FatorRisco instance = new FatorRisco(e, null);
        String expResult = "nome";
        String result = instance.getNomeEnvolvente();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNomeMetrica method, of class FatorRisco.
     */
    @Test
    public void testGetNomeMetrica() {
        System.out.println("getNomeMetrica");
        Metrica m = new Metrica("desi");
        FatorRisco instance = new FatorRisco(null, m);
        String expResult = "desi";
        String result = instance.getNomeMetrica();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class FatorRisco.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Long id = 1L;
        FatorRisco instance = new FatorRisco();
        instance.setId(id);
    }

    /**
     * Test of setMetrica method, of class FatorRisco.
     */
    @Test
    public void testSetMetrica() {
        System.out.println("setMetrica");
        Metrica metrica = new Metrica("des");
        FatorRisco instance = new FatorRisco();
        instance.setMetrica(metrica);
    }

    /**
     * Test of setEnvolvente method, of class FatorRisco.
     */
    @Test
    public void testSetEnvolvente() {
        System.out.println("setEnvolvente");
        Envolvente envolvente = new Envolvente("nome");
        FatorRisco instance = new FatorRisco();
        instance.setEnvolvente(envolvente);
    }
 
}
