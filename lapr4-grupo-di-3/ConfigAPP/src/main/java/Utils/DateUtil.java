/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Dominio.Pedido;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jmbosg
 */
public class DateUtil {
    
    public static String getAmmountOfTimePassedBetweenTwoDates(Date d1, Date d2) {
        
        long time = Math.round((d2.getTime() - d1.getTime()) / (double) 86400000);
        String s = Long.toString(time);
        
        return s;
        
    }
    
    public static Long getAmmountOfDaysPassedBetweenTwoDates(Date d1, Date d2) {
        
        long dias = Math.round((d2.getTime() - d1.getTime()) / (double) 86400000);
        
        return dias;
        
    }

    public long getTempoTotal(List<Pedido> listaPedidos) {
        DateUtil du = new DateUtil();
        Long tempoTotal = 0l;
        for (Pedido p : listaPedidos) {
            long tempoPedido = du.getAmmountOfDaysPassedBetweenTwoDates(p.getDataAtribuicaoAnalista(), p.getDataFinalAtribuicaoAnalista());
            tempoTotal = tempoTotal + tempoPedido;
        }
        return tempoTotal;
    }
    
}
