/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Persistencia.PedidoRepositorioJPAImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Jmbosg
 */
@Entity
public class Pedido implements Serializable, Comparable<Pedido> {

    /**
     * Id auto gerado para identificar uma cobertura
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomePedido;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ObjetoSeguro")
    /**
     * Objeto seguro a ser processado neste pedido;
     */
    private ObjetoSeguro objetoSeguro;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "AvaliacaoRisco")
    /**
     * Avaliacao de risco associada a este pedido;
     */
    private AvaliacaoRisco avaliacaoRisco;

    /**
     * Valor da prioridade (0-4) de um pedido
     */
    @OneToOne
    @Embedded
    private Prioridade prioridade;

    /**
     * Estado do pedido (nao processado/processado)
     */
    @OneToOne
    @Embedded
    private EstadoPedido estadoPedido;

    /**
     * Necessidade de um analista ( sim/nao )
     */
    private String necessidadeAnalista;
    /**
     * utilizador que valida o pedido
     */
    private String nomeUtilizador;
    /**
     * Lista de ocurrencias quando analisado um pedido
     */
    private List<String> listaOcurrencias = new ArrayList<>();

    /**
     * Data de atribuição de um Analista de Risco
     */
    @Temporal(TemporalType.DATE)
    private Date dataAtribuicaoAnalista;
    /**
     * Data final de atribuição de um Analista de Risco
     */
    @Temporal(TemporalType.DATE)
    private Date dataFinalAtribuicaoAnalista;

    @Temporal(TemporalType.DATE)
    private Date dataPedido;

    /**
     * Construtor de pedido
     *
     * @param objetoSeguro Objeto seguro associado a um pedido
     * @param prioridade Prioridade do pedido
     * @param estadoPedido Estado do pedido
     */
    public Pedido(ObjetoSeguro objetoSeguro, Prioridade prioridade, EstadoPedido estadoPedido) {
        this.objetoSeguro = objetoSeguro;
        this.prioridade = prioridade;
        this.estadoPedido = estadoPedido;
    }

    public Pedido(ObjetoSeguro objetoSeguro, String nomePedido, AvaliacaoRisco avaliacaoRisco, EstadoPedido estadoPedido, String necessidadeAnalista, String nomeUtilizador, Date dataAtribuicaoAnalista, Date dataPedido) {
        this.objetoSeguro = objetoSeguro;
        this.nomePedido = nomePedido;
        this.avaliacaoRisco = avaliacaoRisco;
        this.estadoPedido = estadoPedido;
        this.necessidadeAnalista = necessidadeAnalista;
        this.nomeUtilizador = nomeUtilizador;
        this.dataAtribuicaoAnalista = dataAtribuicaoAnalista;
        this.dataPedido = dataPedido;
    }

    public Pedido(ObjetoSeguro objetoSeguro, AvaliacaoRisco avaliacaoRisco, EstadoPedido estadoPedido, String necessidadeAnalista) {
        this.objetoSeguro = objetoSeguro;
        this.avaliacaoRisco = avaliacaoRisco;
        this.estadoPedido = estadoPedido;
        this.necessidadeAnalista = necessidadeAnalista;
    }

    /**
     *
     * @param objetoSeguro
     * @param prioridade
     * @param estadoPedido
     * @param necessidadeAnalista
     */
    public Pedido(ObjetoSeguro objetoSeguro, Prioridade prioridade, EstadoPedido estadoPedido, String necessidadeAnalista) {
        this.objetoSeguro = objetoSeguro;
        this.prioridade = prioridade;
        this.estadoPedido = estadoPedido;
        this.necessidadeAnalista = necessidadeAnalista;
    }

    public Pedido(ObjetoSeguro objetoSeguro, AvaliacaoRisco avaliacaoRisco, Prioridade prioridade, EstadoPedido estadoPedido, String necessidadeAnalista, String nomeUtilizador, Date dataPedido) {
        this.objetoSeguro = objetoSeguro;
        this.avaliacaoRisco = avaliacaoRisco;
        this.prioridade = prioridade;
        this.estadoPedido = estadoPedido;
        this.necessidadeAnalista = necessidadeAnalista;
        this.nomeUtilizador = nomeUtilizador;
        this.dataPedido = dataPedido;
    }

    public Pedido(ObjetoSeguro objetoSeguro, AvaliacaoRisco avaliacaoRisco, EstadoPedido estadoPedido, Date dataPedido) {
        this.objetoSeguro = objetoSeguro;
        this.avaliacaoRisco = avaliacaoRisco;
        this.estadoPedido = estadoPedido;
        this.dataPedido = dataPedido;
    }

    public Pedido(ObjetoSeguro objetoSeguro, String nomePedido, AvaliacaoRisco avaliacaoRisco, EstadoPedido estadoPedido, String necessidadeAnalista, String nomeUtilizador, Date dataAtribuicaoAnalista, Date dataFinalAtribuicaoAnalista, Date dataPedido) {
        this.objetoSeguro = objetoSeguro;
        this.nomePedido = nomePedido;
        this.avaliacaoRisco = avaliacaoRisco;
        this.estadoPedido = estadoPedido;
        this.necessidadeAnalista = necessidadeAnalista;
        this.nomeUtilizador = nomeUtilizador;
        this.dataAtribuicaoAnalista = dataAtribuicaoAnalista;
        this.dataFinalAtribuicaoAnalista = dataFinalAtribuicaoAnalista;
        this.dataPedido = dataPedido;
    }

    public Pedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    /**
     * Construtor vazio para ser usado pelo JPA
     */
    public Pedido() {
    }

    public ObjetoSeguro getObjetoSeguro() {
        return objetoSeguro;
    }

    public Long getId() {
        return id;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public String getNomePedido() {
        return nomePedido;
    }

    public void setAvaliacaoRisco(AvaliacaoRisco avaliacaoRisco) {
        this.avaliacaoRisco = avaliacaoRisco;
    }

    public AvaliacaoRisco getAvaliacaoRisco() {
        return avaliacaoRisco;
    }

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public String getNomeUtilizador() {
        return nomeUtilizador;
    }

    public Date getDataFinalAtribuicaoAnalista() {
        return dataFinalAtribuicaoAnalista;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public Date getDataAtribuicaoAnalista() {
        return dataAtribuicaoAnalista;
    }

    public void setDataAtribuicaoAnalista(Date dataAtribuicaoAnalista) {
        this.dataAtribuicaoAnalista = dataAtribuicaoAnalista;
    }

    public String getNecessidadeAnalista() {
        return necessidadeAnalista;
    }

    public List<String> getListaOcurrencias() {
        return listaOcurrencias;
    }

    public void setNomeUtilizador(String nomeUtilizador) {
        this.nomeUtilizador = nomeUtilizador;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setObjetoSeguro(ObjetoSeguro objetoSeguro) {
        this.objetoSeguro = objetoSeguro;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public void setNecessidadeAnalista(String necessidadeAnalista) {
        this.necessidadeAnalista = necessidadeAnalista;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public void setListaOcurrencias(List<String> listaOcurrencias) {
        this.listaOcurrencias = listaOcurrencias;
    }

    public void setDataFinalAtribuicaoAnalista(Date dataFinalAtribuicaoAnalista) {
        this.dataFinalAtribuicaoAnalista = dataFinalAtribuicaoAnalista;
    }

    @Override
    public int compareTo(Pedido ped) {
        return (int) (this.getId() - ped.getId());
    }

    /**
     * Vai buscar todos os pedidos se encontram no estado pendente.
     *
     * @return
     */
    public List<Pedido> pedidosPendentesAvaliacao() {
        ArrayList<Pedido> pendentes = new ArrayList<Pedido>();
        PedidoRepositorioJPAImpl repo = new PedidoRepositorioJPAImpl();

        List<Pedido> all = repo.findAll();
        for (int i = 0; i < all.size(); i++) {
            Pedido p = all.get(i);
            if (p.getEstadoPedido().getEstadoPedido().equalsIgnoreCase("pendente")) {
                pendentes.add(p);
            }
        }
        return pendentes;
    }

    /**
     * Método que vai buscar todos os pedidos comforme o seu distrito.
     *
     * @param cidade
     * @return
     */
    public List<Pedido> pedidosCidade(String cidade) {
        ArrayList<Pedido> cidades = new ArrayList<Pedido>();
        List<Pedido> all = pedidosPendentesAvaliacao();
        for (int i = 0; i < all.size(); i++) {
            Pedido p = all.get(i);
            if (p.getObjetoSeguro().getEndereco().getDistrito().equalsIgnoreCase(cidade)) {
                cidades.add(p);
            }
        }
        return cidades;
    }

    /**
     * Método que filtra os pedidos por um determinado periodo
     *
     * @param pedidos
     * @return
     */
    public List<Pedido> filtrarPorData(List<Pedido> listaPedidos, Date dataInicial, Date dataFinal) {
        List<Pedido> listaFiltrada = new ArrayList<>();
        for (Pedido p : listaPedidos) {
            int i = compareToDatas(dataInicial, dataFinal, p.dataPedido);
            if (i == 1) {
                listaFiltrada.add(p);
            }
        }
        return listaFiltrada;
    }

    /**
     * se a data do pedido se encontrar dentro do periodo devolve 1 senão 1
     *
     * @param d1
     * @param d2
     * @param dpedido
     * @return
     */
    public int compareToDatas(Date d1, Date d2, Date dpedido) {

        if (d1.before(dpedido) && d2.after(dpedido)) {
            return 1;
        } else {
            return -1;
        }

    }

    /**
     * Método que ordena os pedidos do mais antigo ao mais recente.
     *
     * @param pedidos
     * @return
     */
    public List<Pedido> ordenar(List<Pedido> pedidos) {
        Collections.sort(pedidos, new ComparatorPedido());
        return pedidos;

    }

    /**
     * Método que ordena os pedidos do mais antigo ao mais recente.
     *
     * @param pedidos
     * @return
     */
    public List<Pedido> ordenarCrescente(List<Pedido> pedidos) {
        Collections.sort(pedidos, new ComparatorPedido1());
        return pedidos;

    }

    @Override
    public String toString() {
        return "Pedido{" + "id=" + id + ", objetoSeguro=" + objetoSeguro + '}';
    }

    public static void ordenarListaPedidosPorData(List<Pedido> lista) {
        Collections.sort(lista, new ComparatorPedido());
    }

    public long getTempoMedioPedido(int cont, long tempoMedio) {
        return tempoMedio / cont;
    }

}

class ComparatorPedido implements Comparator<Pedido> {

    @Override
    public int compare(Pedido p1, Pedido p2) {
        return p1.getDataPedido().compareTo(p2.getDataPedido());
    }
}

class ComparatorPedido1 implements Comparator<Pedido> {

    @Override
    public int compare(Pedido p1, Pedido p2) {
        return p2.getDataPedido().compareTo(p1.getDataPedido());
    }

}
