/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CompararAvaliacoesDeRisco;

import DTO.ComparacaoArDTO;
import DTO.ImprimirResultadoDTO;
import Dominio.MatrizRisco;
import Dominio.ObjetoSeguro;
import Dominio.Pedido;
import Persistencia.MatrizRiscoRepositorioJPAImpl;
import Persistencia.PedidoRepositorio;
import Persistencia.PedidoRepositorioJPAImpl;
import Utils.AvaliacaoRiscoService;
import java.util.List;
import org.xml.sax.SAXException;

/**
 *
 * @author hugov
 */
public class CompararARController {

    String content = null;
    String status = null;
    PedidoRepositorio p = new PedidoRepositorioJPAImpl();
    List<Pedido> listaP = p.findAll();
    MatrizRiscoRepositorioJPAImpl mr = new MatrizRiscoRepositorioJPAImpl();
    List<MatrizRisco> listaMr = mr.findAll();

    /**
     * Controller do UC Comparar Avaliacao Risco
     *
     * @param idMatriz1 Id da matriz1 a ser usada para a comparação com a
     * matriz2
     * @param idMatriz2 Id da matriz2 a ser usada para a comparação com a
     * matriz1
     * @param idPedido Id do pedido a ser avaliado
     */
    public ComparacaoArDTO criarComparacaoArDTO(Long idMatriz1, Long idMatriz2, Long idPedido) {
        PedidoRepositorioJPAImpl pedidoRepo = new PedidoRepositorioJPAImpl();

        Pedido p = pedidoRepo.findById(idPedido);

        ObjetoSeguro os = p.getObjetoSeguro();

        Object[] scoreMax = AvaliacaoRiscoService.obterScoreMaximo(os, idMatriz1);
        Object[] scoreObtido = AvaliacaoRiscoService.obterScoreObtido(os, idMatriz1);
        float indiceAvaliacao = AvaliacaoRiscoService.calculaIndiceRiscoAvaliacao((int) scoreObtido[0], (int) scoreMax[0]);

        int scoreO = (int) scoreObtido[0];
        int scoreM = (int) scoreMax[0];

        Object[] scoreMax2 = AvaliacaoRiscoService.obterScoreMaximo(os, idMatriz2);
        Object[] scoreObtido2 = AvaliacaoRiscoService.obterScoreObtido(os, idMatriz2);
        float indiceAvaliacao2 = AvaliacaoRiscoService.calculaIndiceRiscoAvaliacao((int) scoreObtido2[0], (int) scoreMax2[0]);

        int scoreO2 = (int) scoreObtido2[0];
        int scoreM2 = (int) scoreMax2[0];

//        System.out.println("Id Pedido:" + idPedido);
//        System.out.println("Id Matriz 1:" + idMatriz1);
//        System.out.println("Score Obtido Matriz1:" + scoreO);
//        System.out.println("Score Maximo Matriz1:" + scoreM);
//        System.out.println("Indice Avaliacao Risco1:" + indiceAvaliacao);
//        System.out.println("Id Matriz2:" + idMatriz2);
//        System.out.println("Score Obtido Matriz2:" + scoreO2);
//        System.out.println("Score Maximo Matriz2:" + scoreM2);
//        System.out.println("Indice Avaliacao Risco2:" + indiceAvaliacao2);
        ComparacaoArDTO dto = new ComparacaoArDTO(idPedido, idMatriz1, idMatriz2, scoreO, scoreM, scoreO2, scoreM2, indiceAvaliacao, indiceAvaliacao2, p.getObjetoSeguro().getNomeObjeto(), p.getObjetoSeguro().getListaCoberturas());
        return dto;
    }

    public ImprimirResultadoDTO exportarInfoComparacao(Long idMatriz1, Long idMatriz2, Long idPedido, String contentType) throws SAXException {

        if (p.findById(idPedido) != null && mr.findById(idMatriz1) != null && mr.findById(idMatriz2) != null) {
            ComparacaoArDTO dto = criarComparacaoArDTO(idMatriz1, idMatriz2, idPedido);
            status = "200";
            if (contentType.equalsIgnoreCase("XML")) {
                content = CompararAvaliacoesDeRisco.ExportarInfo.exportarInfoXML(dto);
                if (content == "XML invalido") {
                    status = "404";
                }
            } else if (contentType.equalsIgnoreCase("JSON")) {
                content = CompararAvaliacoesDeRisco.ExportarInfo.exportarInfoJSON(dto);
            }else {
                content = "Content type invalido! Opcoes validas: XML ou JSON";
                status = "404";
            }
        } else {
            status = "404";
            content = "Pedido nao foi processado - verificar os ID's inseridos";
        }

        ImprimirResultadoDTO resultDTO = new ImprimirResultadoDTO(content, status);

        return resultDTO;
    }
}
