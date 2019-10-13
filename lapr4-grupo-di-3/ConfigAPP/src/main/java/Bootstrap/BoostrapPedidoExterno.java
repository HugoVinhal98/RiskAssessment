/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bootstrap;

import Dominio.AvaliacaoRisco;
import Dominio.EnderecoPostal;
import Dominio.EstadoPedido;
import Dominio.IndiceAvaliacaoRisco;
import Dominio.ObjetoSeguro;
import Dominio.Pedido;
import Dominio.ScoreMaximo;
import Dominio.ScoreObtido;
import Persistencia.AvaliacaoRiscoRepositorio;
import Persistencia.AvaliacaoRiscoRepositorioJPAImpl;
import Persistencia.ObjetoSeguroRepositorio;
import Persistencia.ObjetoSeguroRepositorioJPAImpl;
import Persistencia.PedidoRepositorio;
import Persistencia.PedidoRepositorioJPAImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Diogo Rolo
 */
public class BoostrapPedidoExterno {
    
    
    public void bootstrap(){
    
    // Criar Objetos Seguros para o SE03
        
        List<String> l1 = new ArrayList<String>();
        List<String> l2 = new ArrayList<String>();
        
        final PedidoRepositorio pedidoPersistencia = new PedidoRepositorioJPAImpl();
        final ObjetoSeguroRepositorio osr = new ObjetoSeguroRepositorioJPAImpl();
        final AvaliacaoRiscoRepositorio avr = new AvaliacaoRiscoRepositorioJPAImpl();
        
        l1.add("incendio");
        l1.add("sismo");
        l2.add("tempestade");
        l2.add("tsunami");
                
        final ObjetoSeguro os2 = new ObjetoSeguro("Isep", l1, new EnderecoPostal("Porto"));
        final ObjetoSeguro os3 = new ObjetoSeguro("FEUP", l2, new EnderecoPostal("Porto"));
        final ObjetoSeguro os4 = new ObjetoSeguro("Casa do binhale",l1, new EnderecoPostal("Vila Real"));
        final ObjetoSeguro os5 = new ObjetoSeguro("Palacio de Neve",l2, new EnderecoPostal("Lisboa"));
        
        
        osr.add(os2);
        osr.add(os3);
        osr.add(os4);
        osr.add(os5);
        
        
        final AvaliacaoRisco av1 = new AvaliacaoRisco(os2, new ScoreObtido(40), new ScoreMaximo(80), new IndiceAvaliacaoRisco(0.5f));
        final AvaliacaoRisco av2 = new AvaliacaoRisco(os3, new ScoreObtido(30), new ScoreMaximo(60), new IndiceAvaliacaoRisco(0.5f));
        final AvaliacaoRisco av3 = new AvaliacaoRisco(os4, new ScoreObtido(20), new ScoreMaximo(40), new IndiceAvaliacaoRisco(0.5f));
        final AvaliacaoRisco av4 = new AvaliacaoRisco(os4, new ScoreObtido(10), new ScoreMaximo(20), new IndiceAvaliacaoRisco(0.5f));
        // Criar Pedidos 
        
        avr.add(av1);
        avr.add(av2);
        avr.add(av3);
        avr.add(av4);
       
        final Pedido ped1 = new Pedido(os2, av1, new EstadoPedido("processado"), new Date(119, 06, 04));
        final Pedido ped2 = new Pedido(os3, av2, new EstadoPedido("processado"), new Date(118, 06, 04));
        final Pedido ped3 = new Pedido(os4, av3, new EstadoPedido("nao processado"), new Date(119, 06, 04));
        final Pedido ped4 = new Pedido(os3, av4, new EstadoPedido("nao processado"), new Date(110, 06, 04));
        final Pedido ped5 = new Pedido(os5, av2, new EstadoPedido("processado"), new Date(110, 06, 04));
        final Pedido ped6 = new Pedido(os4, av4, new EstadoPedido("processado"), new Date(110, 06, 04));
   
        pedidoPersistencia.add(ped1);
        pedidoPersistencia.add(ped2);
        pedidoPersistencia.add(ped3);
        pedidoPersistencia.add(ped4);
        pedidoPersistencia.add(ped5);
        pedidoPersistencia.add(ped6);
        
        
    }
    
}
