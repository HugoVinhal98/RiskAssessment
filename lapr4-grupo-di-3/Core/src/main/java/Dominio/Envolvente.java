/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import GeoReferenceAPI.HTTPClient;
import java.awt.Desktop;
import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Scanner;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Diogo Rolo
 */
@Entity
public class Envolvente implements Serializable {

    /**
     * Id auto gerado para identificar uma envolvdnte
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome da envolvente (ex: floresta, policia)
     */
    private String nome;

    /**
     * tipo da envolvente
     */
    private String tipo;

    /**
     * localização da envolvente
     */
    private String localizacao;

    /**
     * Construtor vazio para ser usado pelo JPA
     */
    protected Envolvente() {
    }

    /**
     * Construtor de envolvente
     *
     * @param nome Nome da envolvente
     */
    public Envolvente(String nome) {
        this.nome = nome;
    }

    public Envolvente(String nome, String tipo, String localizacao) {
        this.nome = nome;
        this.tipo = tipo;
        this.localizacao = localizacao;
    }

    public Long getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public String getNome() {
        return nome;
    }

    /**
     * Metodo toString() do objeto envolvente
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        final Envolvente other = (Envolvente) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }

    /**
     * Metodo hashCode() do objeto envolvente
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.nome);
        return hash;
    }

    @Override
    public String toString() {
        return "Envolvente{" + "id=" + id + ", nome=" + nome + ", tipo=" + tipo + ", localizacao=" + localizacao + '}';
    }

    public void retificarNome(String novoNome) {
        Scanner in = new Scanner(System.in);
        novoNome = novoNome.split("/")[0];

        if (this.nome.equalsIgnoreCase(novoNome)) {
            System.out.println("O nome da envolvente inserido está correto!");
        } else {
            System.out.println("\n Atendendo ao tipo e à localização foi encontrada a respetiva envolvente: '" + novoNome + "'\n Deseja retificar o nome?\n\n Inserir '1' caso pretenda retificar");
            String res = in.nextLine();

            if (res.equalsIgnoreCase("1")) {
                this.nome = novoNome;
                System.out.println("Nome da envolvente retificada com sucesso!");
            }
        }

    }

    public void mostrarImagem() {
        String url = "";
        Scanner in = new Scanner(System.in);
        System.out.println("\n Pretende visualizar uma imagem aérea centrada na envolvente registada?\n Inserir '1' caso pretenda visualizar");
        String input = in.nextLine();
        if (input.equalsIgnoreCase("1")) {
            System.out.println("\n Pretende uma imagem mais ou menos centrada? \n 1 - Mais Centrada \n 2 - Menos Centrada");
            String zoom = in.nextLine();
            if (zoom.equalsIgnoreCase("1")) {
                url = HTTPClient.prepareDynamicURLPhotoPlaceMais(this.nome);
            } else if (zoom.equalsIgnoreCase("2")) {
                url = HTTPClient.prepareDynamicURLPhotoPlaceMenos(this.nome);
            }
            
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    //this is the only way that convert special characters into ascii to open the browser with the uri because with special caracters the method doesn't work
                    URI parsedUri = parseURIToAsciiNormalized(url);
                    desktop.browse(parsedUri);

                } catch (IOException | URISyntaxException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (Exception ex) {
                    ex.printStackTrace();

                }
            } else {
                Runtime runtime = Runtime.getRuntime();
                try {
                    runtime.exec("xdg-open " + url);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     *
     * @param url url created without special characters converted to ascii
     * @return URL normalized with ascii
     * @throws URISyntaxException
     */
    public static URI parseURIToAsciiNormalized(String url) throws URISyntaxException {
        URI uri = new URI(url);
        String parsedUri = uri.toASCIIString();
        return new URI(parsedUri);
    }
}
