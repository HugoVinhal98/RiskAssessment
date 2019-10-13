/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImportarPedido;

import DTO.ImportarPedidoDTO;
import Dominio.AvaliacaoRisco;
import Dominio.EstadoPedido;
import Dominio.IndiceAvaliacaoRisco;
import Dominio.ObjetoSeguro;
import Dominio.Pedido;
import Dominio.Prioridade;
import Dominio.ScoreMaximo;
import Dominio.ScoreObtido;
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
 * @author Jmbosg
 */
public class ImportarPedidoController {

    /**
     * Controller do UC Importar Pedido (External System)
     *
     * @param toImport Conteudo do pedido a importar
     * @param contentType The type of content (XML/JSON that is going to be
     * read)
     * @return The adequate HTTP status to the operation
     */
    public String importarPedidoController(String toImport, String contentType) throws SAXException, IOException, ParserConfigurationException, ParseException {

        ArrayList<ImportarPedidoDTO> dtoList = new ArrayList<>();
        String status = null;

        if (contentType.equalsIgnoreCase("text/xml")) {
            dtoList = InterpretarFicheiro.lerPedidoDeXML(toImport);
        } 
        
        if (contentType.equalsIgnoreCase("application/json")) {
            dtoList = InterpretarFicheiro.lerPedidoDeJSON(toImport);
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

        status = "201";
        return status;

    }

}
