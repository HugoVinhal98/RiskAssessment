/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExportarInfoPedido;

import DTO.BuscarPedidoDTO;
import DTO.ImprimirResultadoDTO;
import Dominio.Pedido;
import Persistencia.PedidoRepositorio;
import Persistencia.PedidoRepositorioJPAImpl;
import java.util.List;
import org.xml.sax.SAXException;

/**
 *
 * @author franc
 */
public class ExportarInfoPedidoController {

    String content = null;
    String status = null;
    PedidoRepositorio p = new PedidoRepositorioJPAImpl();
    List<Pedido> listaP = p.findAll();
    Boolean flag = false;

    /**
     * Porcura se o pedido existe e retorna a informação do pedido nas
     * diferentes formas possiveis( XML,JSON)
     *
     * @param pedido id do Pedido
     * @param contentType ( XML / JSON )
     *
     * @return resultDTO composto por content ( toda info de um pedido nas
     * diferentes formas possiveis) e composto por status (200 com sucesso,404
     * insucesso )
     */
    public ImprimirResultadoDTO exportarInfoPedido(String pedido, String contentType) throws SAXException {

        long idPedido = Long.parseLong(pedido);
        //Pedido pedidoAUsar = null;

        for (Pedido p : listaP) {
            //&& (p.getEstadoPedido().getEstadoPedido() == "Processado")
            if ((p.getId() == idPedido)) {
                flag = true;
                // pedidoAUsar = p;
                status = "200";
                BuscarPedidoDTO pDTO = new BuscarPedidoDTO(p.getId(), p.getAvaliacaoRisco().getId(), p.getAvaliacaoRisco().getScoreMaximo(), p.getAvaliacaoRisco().getScoreObtido(), p.getAvaliacaoRisco().getIndiceAvalicaoRisco(), p.getObjetoSeguro().getId(), p.getObjetoSeguro().getNomeObjeto(), p.getObjetoSeguro().getListaCoberturas(), status);

                if (contentType.equalsIgnoreCase("XML")) {
                    content = ExportarInfo.exportarInfoXML(pDTO);
                    if (content == "XML invalido") {
                        status = "404";
                    }
                } else if (contentType.equalsIgnoreCase("JSON")) {
                    content = ExportarInfo.exportarInfoJSON(pDTO);
                }

            }

        }

        if (flag == false) {
            status = "404";
            content = "Pedido não existe ou Pedido ainda não está processado";
        }

        ImprimirResultadoDTO resultDTO = new ImprimirResultadoDTO(content, status);

        
        return resultDTO;
    }
}
