/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bootstrap;

import Dominio.*;
import Persistencia.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author GilTrindade
 */
public class BootstrapPedido {

    public void bootstrap() {

        List<String> l1 = new ArrayList<String>();
        List<String> l2 = new ArrayList<String>();

        final PedidoRepositorio pedidoPersistencia = new PedidoRepositorioJPAImpl();
        final ObjetoSeguroRepositorio osr = new ObjetoSeguroRepositorioJPAImpl();
        final AvaliacaoRiscoRepositorio avr = new AvaliacaoRiscoRepositorioJPAImpl();

        l1.add("incendio");
        l1.add("sismo");
        l2.add("tempestade");
        l2.add("tsunami");

        final ObjetoSeguro os1 = new ObjetoSeguro("Isep", l1, new EnderecoPostal("Porto"));
        final ObjetoSeguro os2 = new ObjetoSeguro("FEUP", l2, new EnderecoPostal("Porto"));
        final ObjetoSeguro os3 = new ObjetoSeguro("Casa do binhale", l1, new EnderecoPostal("Vila Real"));
        final ObjetoSeguro os4 = new ObjetoSeguro("Palacio de Neve", l2, new EnderecoPostal("Lisboa"));

        osr.add(os1);
        osr.add(os2);
        osr.add(os3);
        osr.add(os4);

        final AvaliacaoRisco av1 = new AvaliacaoRisco(os1, new ScoreObtido(40), new ScoreMaximo(80), new IndiceAvaliacaoRisco(0.5f));
        final AvaliacaoRisco av2 = new AvaliacaoRisco(os2, new ScoreObtido(30), new ScoreMaximo(60), new IndiceAvaliacaoRisco(0.5f));
        final AvaliacaoRisco av3 = new AvaliacaoRisco(os3, new ScoreObtido(20), new ScoreMaximo(40), new IndiceAvaliacaoRisco(0.5f));
        final AvaliacaoRisco av4 = new AvaliacaoRisco(os4, new ScoreObtido(10), new ScoreMaximo(20), new IndiceAvaliacaoRisco(0.5f));

        avr.add(av1);
        avr.add(av2);
        avr.add(av3);
        avr.add(av4);

        final Pedido ped1 = new Pedido(os1, "1Ped", av1, new EstadoPedido("processado"), "sim", "RuiBarbosa123", new Date(119, 4, 5), new Date(120, 4, 5), new Date(116, 3, 2));
        final Pedido ped2 = new Pedido(os2, "2Ped", av2, new EstadoPedido("pendente"), "sim", "RuiBarbosa123", new Date(119, 3, 12), new Date(121, 4, 5), new Date(119, 2, 5));
        final Pedido ped3 = new Pedido(os3, "3Ped", av3, new EstadoPedido("processado"), "sim", "RuiBarbosa123", new Date(119, 1, 24), new Date(122, 4, 5), new Date(118, 6, 9));
        final Pedido ped4 = new Pedido(os4, "4Ped", av4, new EstadoPedido("pendente"), "sim", "RuiBarbosa123", new Date(119, 2, 22), new Date(123, 4, 5), new Date(118, 2, 13));
        final Pedido ped5 = new Pedido(os2, "5Ped", av3, new EstadoPedido("processado"), "sim", "RuiBarbosa123", new Date(119, 4, 5), new Date(120, 4, 5), new Date(115, 3, 2));
        final Pedido ped6 = new Pedido(os4, "6Ped", av4, new EstadoPedido("processado"), "sim", "RuiBarbosa123", new Date(119, 4, 5), new Date(120, 4, 5), new Date(119, 3, 2));

        pedidoPersistencia.add(ped1);
        pedidoPersistencia.add(ped2);
        pedidoPersistencia.add(ped3);
        pedidoPersistencia.add(ped4);
        pedidoPersistencia.add(ped5);
        pedidoPersistencia.add(ped6);

    }

}
