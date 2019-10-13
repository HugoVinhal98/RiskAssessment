/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Dominio.AvaliacaoRisco;
import Dominio.EnderecoPostal;
import Dominio.EstadoPedido;
import Dominio.IndiceAvaliacaoRisco;
import Dominio.ObjetoSeguro;
import Dominio.Pedido;
import Dominio.ScoreMaximo;
import Dominio.ScoreObtido;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Diogo Rolo
 */
public class DateUtilTest {

    public DateUtilTest() {
    }

    /**
     * Test of getAmmountOfTimePassedBetweenTwoDates method, of class DateUtil.
     */
    @Test
    public void testGetAmmountOfTimePassedBetweenTwoDates() {
        System.out.println("getAmmountOfTimePassedBetweenTwoDates");
        Date d1 = new Date();
        Date d2 = new Date();
        String expResult = "0";
        String result = DateUtil.getAmmountOfTimePassedBetweenTwoDates(d1, d2);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAmmountOfDaysPassedBetweenTwoDates method, of class DateUtil.
     */
    @Test
    public void testGetAmmountOfDaysPassedBetweenTwoDates() {
        System.out.println("getAmmountOfDaysPassedBetweenTwoDates");
        Date d1 = new Date(118,10,10);
        Date d2 = new Date(119, 10, 10);
        Long expResult = 365l;
        Long result = DateUtil.getAmmountOfDaysPassedBetweenTwoDates(d1, d2);
        assertEquals(expResult, result);
    }

    /**
     * Test of getTempoTotal method, of class DateUtil.
     */
    @Test
    public void testGetTempoTotal() {
        System.out.println("getTempoTotal");
        List<Pedido> listaPedidos = new ArrayList<>();
        
        ObjetoSeguro os1 = new ObjetoSeguro("Isep", null, new EnderecoPostal("Porto"));
        AvaliacaoRisco av1 = new AvaliacaoRisco(os1, new ScoreObtido(40), new ScoreMaximo(80), new IndiceAvaliacaoRisco(0.5f));
        Pedido ped1 = new Pedido(os1, "1Ped", av1, new EstadoPedido("processado"), "sim", "RuiBarbosa123", new Date(119, 4, 5), new Date(120, 4, 5), new Date(116, 3, 2));
        ObjetoSeguro os2 = new ObjetoSeguro("Isep", null, new EnderecoPostal("Porto"));
        AvaliacaoRisco av2 = new AvaliacaoRisco(os2, new ScoreObtido(40), new ScoreMaximo(80), new IndiceAvaliacaoRisco(0.5f));
        Pedido ped2 = new Pedido(os2, "1Ped", av2, new EstadoPedido("processado"), "sim", "RuiBarbosa123", new Date(119, 4, 5), new Date(120, 4, 5), new Date(116, 3, 2));
        
        listaPedidos.add(ped1);
        listaPedidos.add(ped2);
        
        DateUtil instance = new DateUtil();
        long expResult = 732L;
        long result = instance.getTempoTotal(listaPedidos);
        assertEquals(expResult, result);
        
        List<Pedido> listaPedidos1 = new ArrayList<>();
        
        long expResult1 = 0L;
        long result1 = instance.getTempoTotal(listaPedidos1);
        assertEquals(expResult, result);
    }

}
