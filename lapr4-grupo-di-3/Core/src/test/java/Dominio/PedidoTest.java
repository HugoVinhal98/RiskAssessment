/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class PedidoTest {

    public PedidoTest() {
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
     * Test of ordenarListaPedidosPorData method, of class Pedido.
     */
    @Test
    public void testOrdenarListaPedidosPorData() {

        System.out.println("ordenarListaPedidosPorData");

        // Pedidos ordenados cronologicamente
        final Pedido ped1 = new Pedido(new Date(116, 3, 2));
        final Pedido ped2 = new Pedido(new Date(117, 3, 2));
        final Pedido ped3 = new Pedido(new Date(118, 2, 2));
        final Pedido ped4 = new Pedido(new Date(118, 3, 2));
        final Pedido ped5 = new Pedido(new Date(119, 3, 1));
        final Pedido ped6 = new Pedido(new Date(119, 3, 2));

        List<Pedido> result = new ArrayList<>();
        List<Pedido> expResult = new ArrayList<>();

        // Adiciona-os desordenadamente à lista
        result.add(ped3);
        result.add(ped2);
        result.add(ped5);
        result.add(ped1);
        result.add(ped6);
        result.add(ped4);

        expResult.add(ped1);
        expResult.add(ped2);
        expResult.add(ped3);
        expResult.add(ped4);
        expResult.add(ped5);
        expResult.add(ped6);

        // Ordena-os pela data
        Pedido.ordenarListaPedidosPorData(result);

        assertEquals(expResult, result);

    }


    /**
     * Test of ordenarCrescente method, of class Pedido.
     */
    @Test
    public void testOrdenarCrescente() {
        System.out.println("ordenarCrescente");
        List<Pedido> pedidos = new ArrayList<>();
        
        Pedido ped1 = new Pedido(null, "1Ped", null, new EstadoPedido("processado"), "sim", "RuiBarbosa123", new Date(119, 4, 5), new Date(120, 4, 5), new Date(116, 3, 2));
        Pedido ped2 = new Pedido(null, "2Ped", null, new EstadoPedido("processado"), "sim", "RuiBarbosa123", new Date(119, 3, 12), new Date(121, 4, 5), new Date(119, 2, 5));
        Pedido ped3 = new Pedido(null, "2Ped", null, new EstadoPedido("processado"), "sim", "RuiBarbosa123", new Date(119, 3, 12), new Date(121, 4, 5), new Date(129, 2, 5));
        Pedido ped4 = new Pedido(null, "2Ped", null, new EstadoPedido("processado"), "sim", "RuiBarbosa123", new Date(119, 3, 12), new Date(121, 4, 5), new Date(109, 2, 5));
        
        pedidos.add(ped1);
        pedidos.add(ped2);
        pedidos.add(ped3);
        pedidos.add(ped4);
        
        Pedido instance = new Pedido();
        List<Pedido> expResult = new ArrayList<>();
        
        expResult.add(ped3);
        expResult.add(ped2);
        expResult.add(ped1);
        expResult.add(ped4);
        
        List<Pedido> result = instance.ordenarCrescente(pedidos);
        assertEquals(expResult, result);
    }

   
    /**
     * Test of getTempoMedioPedido method, of class Pedido.
     */
    @Test
    public void testGetTempoMedioPedido() {
        System.out.println("getTempoMedioPedido");
        int cont = 3;
        long tempoMedio = 1000l;
        Pedido instance = new Pedido();
        long expResult = 333L;
        long result = instance.getTempoMedioPedido(cont, tempoMedio);
        assertEquals(expResult, result);
    }

}
