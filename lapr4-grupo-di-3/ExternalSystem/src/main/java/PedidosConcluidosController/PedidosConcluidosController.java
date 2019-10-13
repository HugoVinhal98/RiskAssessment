/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PedidosConcluidosController;

import DTO.ImprimirResultadoDTO;
import DTO.BuscarPedidoDTO;
import Dominio.Pedido;
import Persistencia.PedidoRepositorio;
import Persistencia.PedidoRepositorioJPAImpl;
import Dominio.ObjetoSeguro;
import ExportarInfoPedido.ExportarInfo;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.xml.sax.SAXException;

/**
 *
 * @author Diogo Rolo
 */
public class PedidosConcluidosController {

    String content = null;
    String status = null;
    PedidoRepositorio p = new PedidoRepositorioJPAImpl();
    List<Pedido> listaPedidosProcessados = p.findAllConcluidos();
    List<Pedido> listaP = p.findAll();
    Boolean flag = false;

    /**
     * Porcura se o pedido existente dentro de um intervalo e retorna a
     * informação do pedido nas diferentes formas possiveis(XML,JSON)
     *
     * @param pedido id do Pedido
     * @param contentType ( XML / JSON )
     *
     * @return resultDTO composto por content ( toda info de um pedido nas
     * diferentes formas possiveis) e composto por status (200 com sucesso,404
     * insucesso
     */
    public ImprimirResultadoDTO exportarPedConcluidosData(Date dataIni, Date dataFim, String contentType) throws SAXException {

        List<BuscarPedidoDTO> listaConcluidos = new ArrayList<>();

        listaConcluidos = getListaPorDatas(dataIni, dataFim);

        if (listaConcluidos.isEmpty()) {
            return null;
        }

        if (contentType.equalsIgnoreCase("XML")) {
            content = ExportarPedidosConluidosInfo.exportarInfoXML(listaConcluidos);
            if (content == "XML invalido") {
                status = "404";
            }
        } else if (contentType.equalsIgnoreCase("JSON")) {
            content = ExportarPedidosConluidosInfo.exportarInfoJSON(listaConcluidos);
        }

        if (flag == false) {
            status = "404";
            content = "Pedido nao existe ou Pedido ainda nao esta processado! Tente novamente";
        }

        ImprimirResultadoDTO resultDTO = new ImprimirResultadoDTO(content, status);

        return resultDTO;

    }

    /**
     * Porcura se o pedido existente dentro de uma localidade e retorna a
     * informação do pedido nas diferentes formas possiveis(XML,JSON)
     *
     * @param locais
     * @param contentType
     * @return
     */
    public ImprimirResultadoDTO exportarPedConcluidosLocal(List<String> locais, String contentType) throws SAXException {

        List<BuscarPedidoDTO> listaConcluidos = new ArrayList<>();

        listaConcluidos = getListaPorLocal(locais);

        if (listaConcluidos.isEmpty()) {
            return null;
        }

        if (contentType.equalsIgnoreCase("XML")) {
            content = ExportarPedidosConluidosInfo.exportarInfoXML(listaConcluidos);
            if (content == "XML invalido") {
                status = "404";
            }
        } else if (contentType.equalsIgnoreCase("JSON")) {
            content = ExportarPedidosConluidosInfo.exportarInfoJSON(listaConcluidos);
        }

        if (flag == false) {
            status = "404";
            content = "Pedido nao existe ou Pedido ainda nao esta processado! Tente novamente";
        }

        ImprimirResultadoDTO resultDTO = new ImprimirResultadoDTO(content, status);

        return resultDTO;
    }

    /**
     * Porcura se o pedido existente dentro de um intervalo e localidade e
     * retorna a informação do pedido nas diferentes formas possiveis(XML,JSON)
     *
     * @param dataIni
     * @param dataFim
     * @param locais
     * @param contentType
     * @return
     */
    public ImprimirResultadoDTO exportarPedConcluidosAmbos(Date dataIni, Date dataFim, List<String> locais, String contentType) throws SAXException {

        List<BuscarPedidoDTO> listaConcluidos = new ArrayList<>();

        listaConcluidos = getListaPorDataeLocal(locais, dataIni, dataFim);

        if (listaConcluidos.isEmpty()) {
            return null;
        }

        if (contentType.equalsIgnoreCase("XML")) {
            content = ExportarPedidosConluidosInfo.exportarInfoXML(listaConcluidos);
            if (content == "XML invalido") {
                status = "404";
            }
        } else if (contentType.equalsIgnoreCase("JSON")) {
            content = ExportarPedidosConluidosInfo.exportarInfoJSON(listaConcluidos);
        }

        if (flag == false) {
            status = "404";
            content = "Pedido nao existe ou Pedido ainda nao esta processado! Tente novamente";
        }

        ImprimirResultadoDTO resultDTO = new ImprimirResultadoDTO(content, status);

        return resultDTO;
    }

    /**
     * get lista de pedidos que correspondem a determinado local e periodo
     *
     * @param locais
     * @param dataIni
     * @param dataFim
     * @return
     */
    public List<BuscarPedidoDTO> getListaPorDataeLocal(List<String> locais, Date dataIni, Date dataFim) {

        List<BuscarPedidoDTO> listaConcluidos = new ArrayList<>();
        List<BuscarPedidoDTO> listaConcluidosLocais = new ArrayList<>();
        List<BuscarPedidoDTO> listaConcluidosData = new ArrayList<>();

        listaConcluidosLocais = getListaPorLocal(locais);
        listaConcluidosData = getListaPorDatas(dataIni, dataFim);

        for (BuscarPedidoDTO p : listaConcluidosData) {
            if (listaConcluidosLocais.contains(p)) {
                listaConcluidos.add(p);
            }
        }

        return listaConcluidos;
    }

    /**
     * get lista de pedidos que correspondem a determinado local
     *
     * @param locais
     * @return
     */
    private List<BuscarPedidoDTO> getListaPorLocal(List<String> locais) {

        List<BuscarPedidoDTO> listaConcluidos = new ArrayList<>();

        if (listaPedidosProcessados.isEmpty()) {
            return null;
        }

        for (Pedido p : listaPedidosProcessados) {
            for (String s : locais) {
                if (s.equalsIgnoreCase(p.getObjetoSeguro().getEndereco().getLocalidade())) {
                    //if (locais.contains(p.getObjetoSeguro().getEndereco().getLocalidade())) {
                    flag = true;

                    status = "200";
                    BuscarPedidoDTO pDTO = new BuscarPedidoDTO(p.getId(), p.getAvaliacaoRisco().getId(), p.getAvaliacaoRisco().getScoreMaximo(), p.getAvaliacaoRisco().getScoreObtido(), p.getAvaliacaoRisco().getIndiceAvalicaoRisco(), p.getObjetoSeguro().getId(), p.getObjetoSeguro().getNomeObjeto(),p.getObjetoSeguro().getEndereco().getLocalidade() ,p.getObjetoSeguro().getListaCoberturas(),p.getDataPedido(), status);
                    

                    listaConcluidos.add(pDTO);

                }
            }
        }
        return listaConcluidos;
    }

    /**
     * get lista de pedidos que correspondem a determinado periodo
     *
     * @param dataIni
     * @param dataFim
     * @return
     */
    private List<BuscarPedidoDTO> getListaPorDatas(Date dataIni, Date dataFim) {

        List<BuscarPedidoDTO> listaConcluidos = new ArrayList<>();

        if (listaPedidosProcessados.isEmpty()) {
            return null;
        }
        
        List<Pedido> teste = listaPedidosProcessados;

        for (Pedido p : listaPedidosProcessados) {
            if (p.getDataPedido().before(dataFim) && p.getDataPedido().after(dataIni)) {
                flag = true;

                status = "200";
                BuscarPedidoDTO pDTO = new BuscarPedidoDTO(p.getId(), p.getAvaliacaoRisco().getId(), p.getAvaliacaoRisco().getScoreMaximo(), p.getAvaliacaoRisco().getScoreObtido(), p.getAvaliacaoRisco().getIndiceAvalicaoRisco(), p.getObjetoSeguro().getId(), p.getObjetoSeguro().getNomeObjeto(),p.getObjetoSeguro().getEndereco().getLocalidade() ,p.getObjetoSeguro().getListaCoberturas(),p.getDataPedido() ,status);
                
                listaConcluidos.add(pDTO);

            }
        }
        return listaConcluidos;
    }
}
