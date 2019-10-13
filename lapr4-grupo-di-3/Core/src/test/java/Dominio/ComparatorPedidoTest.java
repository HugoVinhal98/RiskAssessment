/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jmbosg
 */
public class ComparatorPedidoTest {

    public ComparatorPedidoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of compare method, of class ComparatorPedido.
     */
    @Test
    public void testCompare() {

        System.out.println("compare");

        final Pedido ped1 = new Pedido(new Date(116, 3, 2));
        final Pedido ped2 = new Pedido(new Date(117, 3, 2));

        ComparatorPedido instance = new ComparatorPedido();
        int expResult = -1;

        // -1 se Data do ped1 for mais antiga que ped2
        // 1 se Data do ped1 for mais recente que ped2
        // 0 se forem iguais
        int result = instance.compare(ped1, ped2);
        
        assertEquals(expResult, result);

    }

}
