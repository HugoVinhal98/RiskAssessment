/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacao;

import Dominio.AvaliacaoRisco;
import Dominio.Cobertura;
import Dominio.EstadoPedido;
import Dominio.IndiceAvaliacaoRisco;
import Dominio.ObjetoSeguro;
import Dominio.Pedido;
import Dominio.ScoreMaximo;
import Dominio.ScoreObtido;
import Persistencia.AvaliacaoRiscoRepositorio;
import Persistencia.AvaliacaoRiscoRepositorioJPAImpl;
import Persistencia.CoberturaRepositorio;
import Persistencia.CoberturaRepositorioJPAImpl;
import Persistencia.ObjetoSeguroRepositorio;
import Persistencia.ObjetoSeguroRepositorioJPAImpl;
import Persistencia.PedidoRepositorio;
import Persistencia.PedidoRepositorioJPAImpl;
import Utils.AvaliacaoRiscoService;
import DTO.PedidoBuscarDTO;
import Utils.DateUtil;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author franc
 */
public class AnalisarPedidoC {

    PedidoRepositorio pr = new PedidoRepositorioJPAImpl();
    AvaliacaoRiscoRepositorio aRepo = new AvaliacaoRiscoRepositorioJPAImpl();
    ObjetoSeguroRepositorio osRepo = new ObjetoSeguroRepositorioJPAImpl();
    Pedido ped = new Pedido();

    private CoberturaRepositorio cRepo = new CoberturaRepositorioJPAImpl();
    List<Cobertura> lc = cRepo.findAll();

    Pedido p = null;
    PedidoBuscarDTO pDTO = null;
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Exporta toda a informação relativa a um Pedido com interesse a um
     * Analista de Risco AR04.1
     *
     * @param p
     * @return
     */
    public PedidoBuscarDTO ExportarDetalhesPedido(Pedido p) {

        if (p.getNecessidadeAnalista().equalsIgnoreCase("sim")) {

            pDTO = new PedidoBuscarDTO(p.getId(), p.getAvaliacaoRisco().getId(), p.getAvaliacaoRisco().getScoreMaximo(), p.getAvaliacaoRisco().getScoreObtido(), p.getAvaliacaoRisco().getIndiceAvalicaoRisco(), p.getObjetoSeguro().getId(), p.getObjetoSeguro().getNomeObjeto(), p.getObjetoSeguro().getListaCoberturas());

            return pDTO;
        }

        return null;

    }

    /**
     * Exporta informação de um pedido para HTML AR04.2
     *
     * @param p
     * @throws IOException
     */
    public void ExportarInfoHTML(Pedido p) throws IOException {

        ObjetoSeguro os = p.getObjetoSeguro();

        File htmlTemplateFile = new File("templatePedido.html");
        String htmlString = FileUtils.readFileToString(htmlTemplateFile);

        String title = "Detalhes do Pedido de Avaliação de Risco " + p.getNomePedido();
        String pedido = p.getNomePedido();
        String scoreObtido = Integer.toString(p.getAvaliacaoRisco().getScoreObtido().getScoreObtido());
        String scoreMaximo = Integer.toString(p.getAvaliacaoRisco().getScoreMaximo().getScoreMaximo());
        String indiceAR = Float.toString(p.getAvaliacaoRisco().getIndiceAvalicaoRisco().getIndiceAvalicaoRisco());
        String nomeObjeto = os.getNomeObjeto();

        StringBuilder lCob = new StringBuilder();

        for (String s : os.getListaCoberturas()) {
            lCob.append(s).append(" || ");
        }

        StringBuilder lOccurs = new StringBuilder();

        for (String s : p.getListaOcurrencias()) {
            lOccurs.append(s).append(" || ");
        }

        String listaCoberturas = lCob.toString();
        String listaOcurrencias = lOccurs.toString();

        htmlString = htmlString.replace("$title", title);
        htmlString = htmlString.replace("$pedido", pedido);
        htmlString = htmlString.replace("$scoreObtido", scoreObtido);
        htmlString = htmlString.replace("$scoreMaximo", scoreMaximo);
        htmlString = htmlString.replace("$indiceAR", indiceAR);
        htmlString = htmlString.replace("$nomeObjeto", nomeObjeto);
        htmlString = htmlString.replace("$listaCoberturas", listaCoberturas);
        htmlString = htmlString.replace("$listaOcurrencias", listaOcurrencias);

        File newHtmlFile = new File("pedido" + pedido + ".html");
        FileUtils.writeStringToFile(newHtmlFile, htmlString);

    }

    /**
     * Exporta informação de um pedido para HTML AR04.2
     *
     * @param p
     * @throws IOException
     */
    public void exportarInfoPedidosHTML(List<Pedido> listaConcluidos, String nome) throws IOException {
        File newHtmlFile = new File("pedidos" + nome + ".html");
        DateUtil du = new DateUtil();
        Pedido ped = new Pedido();

        File htmlTemplateFile = new File("templatePedido.html");
        String htmlString1 = "";
        StringBuilder sb2 = new StringBuilder();

        int cont = listaConcluidos.size();

        String contagemPedidos = Integer.toString(cont);

        for (Pedido p : listaConcluidos) {
            String htmlString = FileUtils.readFileToString(htmlTemplateFile);

            String title = "Detalhes do Pedido de Avaliação de Risco " + p.getNomePedido();
            String pedido = p.getNomePedido();
            String scoreObtido = Integer.toString(p.getAvaliacaoRisco().getScoreObtido().getScoreObtido());
            String scoreMaximo = Integer.toString(p.getAvaliacaoRisco().getScoreMaximo().getScoreMaximo());
            String indiceAR = Float.toString(p.getAvaliacaoRisco().getIndiceAvalicaoRisco().getIndiceAvalicaoRisco());
            String nomeObjeto = p.getObjetoSeguro().getNomeObjeto();

            StringBuilder lCob = new StringBuilder();

            for (String s : p.getObjetoSeguro().getListaCoberturas()) {
                lCob.append(s).append(" || ");
            }

            StringBuilder lOccurs = new StringBuilder();

            for (String s : p.getListaOcurrencias()) {
                lOccurs.append(s).append(" || ");
            }

            String listaCoberturas = lCob.toString();
            String listaOcurrencias = lOccurs.toString();

            htmlString = htmlString.replace("$title", title);
            htmlString = htmlString.replace("$pedido", pedido);
            htmlString = htmlString.replace("$scoreObtido", scoreObtido);
            htmlString = htmlString.replace("$scoreMaximo", scoreMaximo);
            htmlString = htmlString.replace("$indiceAR", indiceAR);
            htmlString = htmlString.replace("$nomeObjeto", nomeObjeto);
            htmlString = htmlString.replace("$listaCoberturas", listaCoberturas);
            htmlString = htmlString.replace("$listaOcurrencias", listaOcurrencias);

            htmlString1 = htmlString1 + htmlString;
        }
        long tempoTotal = 0;
        for (Pedido p : listaConcluidos) {
            long tempoPedido = du.getAmmountOfDaysPassedBetweenTwoDates(p.getDataAtribuicaoAnalista(), p.getDataFinalAtribuicaoAnalista());
            tempoTotal = tempoTotal + tempoPedido;
        }
        long tempoMedioPedidos = ped.getTempoMedioPedido(cont, tempoTotal);

        File htmlTemplateFile2 = new File("sumario.html");
        String htmlString2 = FileUtils.readFileToString(htmlTemplateFile2);
        String tempoMed = Long.toString(tempoMedioPedidos);
        htmlString2 = htmlString2.replace("$contagem", contagemPedidos);
        htmlString2 = htmlString2.replace("$tempoMedio", tempoMed);

        htmlString1 = htmlString1 + htmlString2;

        FileUtils.writeStringToFile(newHtmlFile, htmlString1);
        Desktop.getDesktop().browse(URI.create("pedidos" + nome + ".html"));
    }

    /**
     * Confirma os resultados do pedido de avaliaçao de risco AR04.4
     *
     * @param p
     * @param ocurrencia
     */
    public void ConfirmarResultados(Pedido p, String ocurrencia) {

        List<String> lp = p.getListaOcurrencias();

        Date date = new Date();
        String ocurrenciaX = " || " + ocurrencia + " Data: " + dateFormat.format(date);

        lp.add(ocurrenciaX);

        pr.remove(p.getId());

        p.setNecessidadeAnalista("nao");
        p.setEstadoPedido(new EstadoPedido("processado"));
        p.setListaOcurrencias(lp);

        p.setDataFinalAtribuicaoAnalista(date);

        pr.add(p);

    }

    /**
     *
     * Atribui diretamente os resultados do pedido de avaliaçao de risco
     * Introduzindo uma fundamentação obrigatória AR04.5
     *
     * @param p
     * @param ScoreMax
     * @param ScoreO
     * @param fundamentacao
     */
    public void AtribuirResultados(Pedido p, int ScoreMax, int ScoreO, String fundamentacao) {

        List<String> lp = p.getListaOcurrencias();
        Date date = new Date();
        String fundamentacaoX = " || " + fundamentacao + " Data: " + dateFormat.format(date);
        lp.add(fundamentacaoX);
        float indAR = (float) ScoreO / (float) ScoreMax;

        AvaliacaoRisco ar = new AvaliacaoRisco(p.getObjetoSeguro(), new ScoreObtido(ScoreO), new ScoreMaximo(ScoreMax), new IndiceAvaliacaoRisco(indAR));

        aRepo.add(ar);

        pr.remove(p.getId());

        aRepo.remove(p.getAvaliacaoRisco().getId());

        p.setAvaliacaoRisco(ar);
        p.setEstadoPedido(new EstadoPedido("processado"));
        p.setNecessidadeAnalista("nao");
        p.setListaOcurrencias(lp);
        p.setDataFinalAtribuicaoAnalista(date);

        pr.add(p);

    }

    /**
     * Faz uma reavaliaçao automatica do pedido ficando no fim à espera de ser
     * re-analisado pelo proprio AR AR04.6
     *
     * @param p
     * @param novaLista
     */
    public void ReavaliacaoAutomatica(Pedido p, String novaLista) {

        String[] coberturas = novaLista.split(";");
        List<String> lc = new ArrayList<>();
        Date date = new Date();
        String ocurrencia = " || Reavaliação automática na Data: " + dateFormat.format(date);
        List<String> lp = p.getListaOcurrencias();
        lp.add(ocurrencia);

        for (int i = 0; i < coberturas.length; i++) {
            lc.add(coberturas[i]);
        }

        ObjetoSeguro os1 = p.getObjetoSeguro();
        os1.setListaCoberturas(lc);

        Object[] scoreMax = AvaliacaoRiscoService.obterScoreMaximo(os1, 1);
        Object[] scoreObtido = AvaliacaoRiscoService.obterScoreObtido(os1, 1);
        float indiceAvaliacao = AvaliacaoRiscoService.calculaIndiceRiscoAvaliacao((int) scoreObtido[0], (int) scoreMax[0]);

        int scoreO = (int) scoreObtido[0];
        int scoreM = (int) scoreMax[0];

        ObjetoSeguro os = new ObjetoSeguro(os1.getNomeObjeto(), os1.getListaCoberturas(), os1.getEndereco());

        AvaliacaoRisco ar = new AvaliacaoRisco(os, new ScoreObtido(scoreO), new ScoreMaximo(scoreM), new IndiceAvaliacaoRisco(indiceAvaliacao));

        osRepo.add(os);

        aRepo.add(ar);

        pr.remove(p.getId());

        aRepo.remove(p.getAvaliacaoRisco().getId());

        osRepo.remove(p.getObjetoSeguro().getId());

        p.setAvaliacaoRisco(ar);

        p.setListaOcurrencias(lp);

        p.setObjetoSeguro(os);

        pr.add(p);

    }

    /**
     * Lista todas as coberturas no sistema
     *
     * @return
     */
    public List<Cobertura> listarCoberturas() {

        List<Cobertura> lc = cRepo.findAll();

        return lc;

    }

    /**
     * Lista todas as coberturas de um dado pedido
     *
     * @param p
     * @return
     */
    public List<String> listarCoberturasPedido(Pedido p) {

        return p.getObjetoSeguro().getListaCoberturas();

    }

}
