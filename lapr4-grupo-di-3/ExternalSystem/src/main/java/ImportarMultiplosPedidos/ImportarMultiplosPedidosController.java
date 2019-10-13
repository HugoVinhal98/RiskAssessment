/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImportarMultiplosPedidos;

import DTO.ImportarPedidoDTO;
import Dominio.AvaliacaoRisco;
import Dominio.EstadoPedido;
import Dominio.IndiceAvaliacaoRisco;
import Dominio.ObjetoSeguro;
import Dominio.Pedido;
import Dominio.Prioridade;
import Dominio.ScoreMaximo;
import Dominio.ScoreObtido;
import ImportarPedido.InterpretarFicheiro;
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
 * @author Gil Trindade
 */
public class ImportarMultiplosPedidosController {

    /**
     *Método para importar uma lista de pedidos de um ficheiro json/xml.
     * @param toImport
     * @param contentType
     * @return
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws ParseException
     */
    public String importarMultiplosPedidoController(String toImport, String contentType) throws SAXException, IOException, ParserConfigurationException, ParseException {

        ArrayList<ImportarPedidoDTO> dtoList = new ArrayList<>();
        String status = null;

        if (contentType.equalsIgnoreCase("text/xml")) {
            dtoList = InterpretarMultiplosPedidos.lerPedidoDeXML(toImport);
        } 
        
        if (contentType.equalsIgnoreCase("application/json")) {
            dtoList = InterpretarMultiplosPedidos.lerPedidoDeJSON(toImport);
        }
        
        if (dtoList == null) {
            status = "400";
            return status;
        }

        ObjetoSeguroRepositorio objRepo = new ObjetoSeguroRepositorioJPAImpl();
        PedidoRepositorio pedidoRepo = new PedidoRepositorioJPAImpl();

        for (ImportarPedidoDTO d : dtoList) {

            ObjetoSeguro o = new ObjetoSeguro(d.getNomeObjeto(), d.getCoberturas());

            objRepo.add(o);

            Pedido p = new Pedido(o, new Prioridade(0), new EstadoPedido("nao processado"), d.getNecessidadeAnalista());

            pedidoRepo.add(p);

        }

        List<Pedido> pedidosAProcessar = pedidoRepo.findAll();

        for (Pedido p : pedidosAProcessar) {

            ObjetoSeguro os = p.getObjetoSeguro();

            Object[] scoreMax = AvaliacaoRiscoService.obterScoreMaximo(os, 1);
            Object[] scoreObtido = AvaliacaoRiscoService.obterScoreObtido(os, 1);
            float indiceAvaliacao = AvaliacaoRiscoService.calculaIndiceRiscoAvaliacao((int) scoreObtido[0], (int) scoreMax[0]);

            int scoreO = (int) scoreObtido[0];
            int scoreM = (int) scoreMax[0];

            AvaliacaoRisco ar = new AvaliacaoRisco(os, new ScoreObtido(scoreO), new ScoreMaximo(scoreM), new IndiceAvaliacaoRisco(indiceAvaliacao));

            AvaliacaoRiscoRepositorio arepo = new AvaliacaoRiscoRepositorioJPAImpl();

            arepo.add(ar);

            pedidoRepo.remove(p.getId());

            p.setAvaliacaoRisco(ar);

            pedidoRepo.add(p);

        }
      List<Pedido> lista2 = pedidoRepo.findAll();
      for(int i = 0; i< lista2.size(); i++){
          System.out.println(lista2.get(i));
      }
        status = "201";
        return status;
  
    
     }
            
}
