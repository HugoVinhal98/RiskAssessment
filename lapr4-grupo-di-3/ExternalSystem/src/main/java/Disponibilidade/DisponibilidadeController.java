
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Disponibilidade;

import DTO.ImportarPedidoDTO;
import Dominio.AvaliacaoRisco;
import Dominio.EstadoPedido;
import Dominio.IndiceAvaliacaoRisco;
import Dominio.ObjetoSeguro;
import Dominio.Pedido;
import Dominio.Prioridade;
import Dominio.ScoreMaximo;
import Dominio.ScoreObtido;
import ImportarMultiplosPedidos.InterpretarMultiplosPedidos;
import Persistencia.AvaliacaoRiscoRepositorio;
import Persistencia.AvaliacaoRiscoRepositorioJPAImpl;
import Persistencia.ObjetoSeguroRepositorio;
import Persistencia.ObjetoSeguroRepositorioJPAImpl;
import Persistencia.PedidoRepositorio;
import Persistencia.PedidoRepositorioJPAImpl;
import Utils.AvaliacaoRiscoService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

/**
 *
 * @author GilTrindade
 */
public class DisponibilidadeController {
    private static final int MAX_PEDIDOS = 3;

    /**
     *
     * @param toImport
     * @param contentType
     * @return
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws ParseException
     * 
     * 
     * Método para mostrar a disponibilidade do sistema para realizar operações através de uma text 
     */
    public String[] disponibilidadeController(String toImport, String contentType) throws SAXException, IOException, ParserConfigurationException, ParseException {

        String[] status = new String[2];

        if (contentType.equalsIgnoreCase("text/plain")) {
            System.out.println(toImport);
            if(toImport.equalsIgnoreCase("Diga-me a disponibilidade")){
                
            
            int i = java.lang.Thread.activeCount();
                
            if (i < MAX_PEDIDOS) {
                status[0] = "Sim";
                
                status[1] = Integer.toString(i);
            }
            else{
                status[0] = "Não";
                status[1] = Integer.toString(i);
            }
            
            }
            
            else {
                status[0] = "400";
            }
        } else {
            status[0] = "401";
            return status;
        }
        System.out.println(status[0]+status[1]);
        
        return status;
         
    }

}