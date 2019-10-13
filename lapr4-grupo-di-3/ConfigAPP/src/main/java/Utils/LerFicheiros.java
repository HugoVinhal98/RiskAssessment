/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Dominio.Cobertura;
import Dominio.Contribuicao;
import Dominio.Envolvente;
import Dominio.EscalaRisco;
import Dominio.FatorRisco;
import Dominio.LinhaMatrizBase;
import Dominio.LinhaMatrizCaraterizada;
import Dominio.LinhaMatrizDetalhada;
import Dominio.MatrizRisco;
import Dominio.Metrica;
import Dominio.Necessidade;
import Dominio.Peso;
import Persistencia.CoberturaRepositorio;
import Persistencia.CoberturaRepositorioJPAImpl;
import Persistencia.EnvolventeRepositorio;
import Persistencia.EnvolventeRepositorioJPAImpl;
import Persistencia.FatorDeRiscoRepositorio;
import Persistencia.FatorDeRiscoRepositorioJPAImpl;
import Persistencia.LinhaMatrizBaseRepositorio;
import Persistencia.LinhaMatrizBaseRepositorioJPAImpl;
import Persistencia.LinhaMatrizCaraterizadaRepositorio;
import Persistencia.LinhaMatrizCaraterizadaRepositorioJPAImpl;
import Persistencia.MatrizRiscoRepositorio;
import Persistencia.MatrizRiscoRepositorioJPAImpl;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author hugov
 */
public class LerFicheiros {

    /**
     * Le coberturas de ficheiro e guarda-as numa lista
     *
     * @param caminhoFicheiro Caminho do ficheiro de onde as coberturas serao
     * importadas
     * @return Lista de coberturas lidas
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static ArrayList<Cobertura> lerCoberturaDeFicheiro(String caminhoFicheiro) throws FileNotFoundException, IOException {

        String csvFile = caminhoFicheiro;
        BufferedReader br = null;
        String line = "";
        String csvSplitBy = ",";
        String[] coberturas = null;
        ArrayList<Cobertura> listaCoberturas = new ArrayList<>();

        br = new BufferedReader(new FileReader(csvFile));

        while ((line = br.readLine()) != null) {

            coberturas = line.split(csvSplitBy);

        }

        for (String c : coberturas) {

            Cobertura c1 = new Cobertura(c);
            listaCoberturas.add(c1);

        }

        return listaCoberturas;

    }

    /**
     * Le envolventes de ficheiro e guarda-as numa lista
     *
     * @param caminhoFicheiro Caminho do ficheiro de onde as envolvente serao
     * lidas
     * @return Lista de envolventes lidas
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static ArrayList<Envolvente> lerEnvolventesDeFicheiro(String caminhoFicheiro) throws FileNotFoundException, IOException {

        String csvFile = caminhoFicheiro;
        BufferedReader br = null;
        String line = "";
        String csvSplitBy = ",";
        String[] envolventes = null;
        ArrayList<Envolvente> listaEnvolventes = new ArrayList<>();

        br = new BufferedReader(new FileReader(csvFile));

        while ((line = br.readLine()) != null) {

            envolventes = line.split(csvSplitBy);

        }

        for (String e : envolventes) {

            Envolvente e1 = new Envolvente(e);
            listaEnvolventes.add(e1);

        }

        return listaEnvolventes;

    }

    /**
     * Le fatores de risco através de um ficheiro.
     *
     * @param nomeFicheiro nome do ficheiro com os fatores de risco
     * @return lista de fatores de risco lidos
     * @throws FileNotFoundException caso o ficheiro não exista
     * @throws IOException error
     */
    public static ArrayList<FatorRisco> lerFatoresDeRiscoDeFicheiro(String nomeFicheiro) throws FileNotFoundException, IOException {

        BufferedReader br = null;
        String line = "";
        String csvSplitBy = ",";
        String[] fatores = null;
        ArrayList<FatorRisco> listaFatoresRisco = new ArrayList<>();

        EnvolventeRepositorio en = new EnvolventeRepositorioJPAImpl();
        List<Envolvente> listaEnvolventes = en.findAll();
        for (Envolvente e : listaEnvolventes) {
            System.out.println(e.getNome());
        }
        br = new BufferedReader(new FileReader(nomeFicheiro));

        while ((line = br.readLine()) != null) {

            fatores = line.split(csvSplitBy);
            Envolvente e = verificaEnvolvente(fatores[0], listaEnvolventes);
            if (e != null) {
                FatorRisco fa = new FatorRisco(e, new Metrica(fatores[1]));
                listaFatoresRisco.add(fa);
            } else {
                System.out.println("A envolvente " + fatores[0] + " não existe!");
            }

        }

        return listaFatoresRisco;

    }

    /**
     * Exporta linhas de matriz base vazias para um ficheiro para posterior
     * preenchimento
     *
     * @param caminhoFicheiro Caminho do ficheiro para onde as linhas serao
     * exportadas
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void exportarLinhasMatrizBase(String caminhoFicheiro) throws FileNotFoundException, IOException {

        try (PrintWriter writer = new PrintWriter(new File(caminhoFicheiro))) {

            StringBuilder sb = new StringBuilder();

            FatorDeRiscoRepositorio repo1 = new FatorDeRiscoRepositorioJPAImpl();
            List<FatorRisco> l1 = repo1.findAll();

            sb.append("Fatores contribuem cobertura,");
            for (FatorRisco fr : l1) {
                sb.append(fr.getNomeEnvolvente() + "/" + fr.getNomeMetrica() + ",");
            }
            sb.append("\n");

            CoberturaRepositorio repo2 = new CoberturaRepositorioJPAImpl();
            List<Cobertura> l2 = repo2.findAll();

            for (Cobertura cob : l2) {
                sb.append(cob.getNome() + "\n");
            }
            writer.write(sb.toString());

            System.out.println("Done!");

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Importa linhas de matriz base preenchidas atraves de um ficheiro
     *
     * @param caminhoFicheiro Caminho do ficheiro atraves do qual as linhas de
     * matriz base serao preenchidas
     * @param idMatriz Id da matriz a qual as linhas vao ficar associadas
     * @return Lista de linhas de matriz base preenchidas
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static ArrayList<LinhaMatrizBase> importarLinhasMatrizBase(String caminhoFicheiro, Long idMatriz) throws FileNotFoundException, IOException {

        BufferedReader br = null;
        br = new BufferedReader(new FileReader(caminhoFicheiro));
        ArrayList<LinhaMatrizBase> listaLinhasBase = new ArrayList<>();

        String line = "";
        String csvSplitBy = ",";
        String coberturaConsideraveis[] = null;
        line = br.readLine();
        String[] fatores = line.split(csvSplitBy);

        String fatorSplit[];

        while ((line = br.readLine()) != null) {

            coberturaConsideraveis = line.split(csvSplitBy);

            String nomeCobertura = coberturaConsideraveis[0];

            for (int i = 1; i < fatores.length; i++) {
                String consideravel = coberturaConsideraveis[i];
                String fator = fatores[i];
                fatorSplit = fator.split("/");
                Cobertura c = verificaExistenciaCobertura(nomeCobertura);
                FatorRisco fr = verificaExistenciaFatorRisco(fatorSplit);
                if (c != null && fr != null) {
                    LinhaMatrizBase lbd = new LinhaMatrizBase(c, fr, consideravel, idMatriz);
                    listaLinhasBase.add(lbd);
                }

            }
        }

        return listaLinhasBase;

    }

    /**
     * Le linhas de matriz caraterizadas de varios ficheiros e retorna uma lista
     * com as linhas criadas
     *
     * @param caminhoFicheiro1 Caminho do ficheiro da matriz caraterizada com o
     * peso
     * @param caminhoFicheiro2 Caminho do ficheiro da matriz caraterizada com a
     * necessidade
     * @param caminhoFicheiro3 Caminho do ficheiro da matriz caraterizada com a
     * contribuicao
     * @param idMatriz Id da matriz à qual a linha vai pertencer
     * @return Lista de linhas de matriz caraterizadas
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static ArrayList<LinhaMatrizCaraterizada> importarLinhasMatrizCaracterizada(String caminhoFicheiro1, String caminhoFicheiro2, String caminhoFicheiro3, Long idMatriz) throws FileNotFoundException, IOException {

        BufferedReader br = null;
        br = new BufferedReader(new FileReader(caminhoFicheiro1));
        ArrayList<LinhaMatrizCaraterizada> listaLinhasCaraterizadas = new ArrayList<>();

        String line = "";
        String csvSplitBy = ",";
        String coberturaCaracterizacoes[] = null;
        line = br.readLine();
        String[] fatores = line.split(csvSplitBy);

        boolean flag = false;

        LinhaMatrizBaseRepositorio en = new LinhaMatrizBaseRepositorioJPAImpl();
        List<LinhaMatrizBase> listaLinhasBase = en.findAll();

        while ((line = br.readLine()) != null) {
            coberturaCaracterizacoes = line.split(csvSplitBy);

            String nomeCobertura = coberturaCaracterizacoes[0];

            for (int i = 1; i < fatores.length; i++) {
                String caracterizacao = coberturaCaracterizacoes[i];
                if (!caracterizacao.equals("(n/a)")) {
                    String fator = fatores[i];
                    LinhaMatrizBase lmb = verificaLinhaBase(nomeCobertura, fator, listaLinhasBase, idMatriz);
                    if (lmb != null) {
                        for (LinhaMatrizCaraterizada l : listaLinhasCaraterizadas) {
                            if (l.getLinhaMatrizBase().equals(lmb)) {
                                l.setPeso(new Peso(Integer.parseInt(caracterizacao)));
                                flag = true;
                            }
                        }
                        if (flag == false) {
                            LinhaMatrizCaraterizada l2 = new LinhaMatrizCaraterizada(lmb, new Peso(Integer.parseInt(caracterizacao)), idMatriz);
                            listaLinhasCaraterizadas.add(l2);
                        }
                    }
                    flag = false;
                }

            }

        }

        br = new BufferedReader(new FileReader(caminhoFicheiro2));

        while ((line = br.readLine()) != null) {
            coberturaCaracterizacoes = line.split(csvSplitBy);

            String nomeCobertura = coberturaCaracterizacoes[0];

            for (int i = 1; i < fatores.length; i++) {
                String caracterizacao = coberturaCaracterizacoes[i];
                if (!caracterizacao.equals("(n/a)")) {
                    String fator = fatores[i];
                    LinhaMatrizBase lmb = verificaLinhaBase(nomeCobertura, fator, listaLinhasBase, idMatriz);
                    if (lmb != null) {
                        for (LinhaMatrizCaraterizada l : listaLinhasCaraterizadas) {
                            if (l.getLinhaMatrizBase().equals(lmb)) {
                                l.setNecessidade(new Necessidade(caracterizacao));
                                flag = true;
                            }
                        }
                        if (flag == false) {
                            LinhaMatrizCaraterizada l2 = new LinhaMatrizCaraterizada(lmb, new Necessidade(caracterizacao), idMatriz);
                            listaLinhasCaraterizadas.add(l2);
                        }
                    }
                    flag = false;
                }

            }

        }

        br = new BufferedReader(new FileReader(caminhoFicheiro3));

        while ((line = br.readLine()) != null) {
            coberturaCaracterizacoes = line.split(csvSplitBy);

            String nomeCobertura = coberturaCaracterizacoes[0];

            for (int i = 1; i < fatores.length; i++) {
                String caracterizacao = coberturaCaracterizacoes[i];
                if (!caracterizacao.equals("(n/a)")) {
                    String fator = fatores[i];
                    LinhaMatrizBase lmb = verificaLinhaBase(nomeCobertura, fator, listaLinhasBase, idMatriz);
                    if (lmb != null) {
                        for (LinhaMatrizCaraterizada l : listaLinhasCaraterizadas) {
                            if (l.getLinhaMatrizBase().equals(lmb)) {
                                l.setContribuicao(new Contribuicao(caracterizacao));
                                flag = true;
                            }
                        }
                        if (flag == false) {
                            LinhaMatrizCaraterizada l2 = new LinhaMatrizCaraterizada(lmb, new Contribuicao(caracterizacao), idMatriz);
                            listaLinhasCaraterizadas.add(l2);
                        }
                    }
                    flag = false;
                }

            }

        }

        return listaLinhasCaraterizadas;

    }

    /**
     * Le de um ficheiro as linhas de matriz detalhadas
     *
     * @param nomeFicheiro nome do ficheiro com as linhas detalhadas
     * @param idMatriz Id da matriz à qual a linha vai pertencer
     * @return lista com todas as linhas detalhadas lidas
     * @throws FileNotFoundException caso não encontre o ficheiro
     * @throws IOException erro
     */
    public static ArrayList<LinhaMatrizDetalhada> importarLinhasMatrizDetalhada(String nomeFicheiro, Long idMatriz) throws FileNotFoundException, IOException {

        BufferedReader br = null;
        br = new BufferedReader(new FileReader(nomeFicheiro));
        ArrayList<LinhaMatrizDetalhada> listaLinhasDetalhadas = new ArrayList<>();

        String line = "";
        String csvSplitBy = ";";
        String coberturaEscalas[] = null;
        line = br.readLine();
        String[] fatores = line.split(csvSplitBy); //Escala; Fator; Fator; Fator; Fator

        LinhaMatrizCaraterizadaRepositorio en = new LinhaMatrizCaraterizadaRepositorioJPAImpl();
        List<LinhaMatrizCaraterizada> listaLinhasCaraterizadas = en.findAll();

        while ((line = br.readLine()) != null) {
            coberturaEscalas = line.split(csvSplitBy); //Cobertura; Escala; Escala; Escala; Escala

            String nomeCobertura = coberturaEscalas[0];

            for (int i = 1; i < fatores.length; i++) {
                String escala = coberturaEscalas[i];
                if (!escala.equals("(n/a)")) {
                    String[] escalas = escala.split(",");
                    String fator = fatores[i];
                    LinhaMatrizCaraterizada lmc = verificaLinhaDetalhada(nomeCobertura, fator, listaLinhasCaraterizadas, idMatriz);
                    if (lmc != null) {
                        LinhaMatrizDetalhada lmd = new LinhaMatrizDetalhada(lmc, new EscalaRisco(Integer.parseInt(escalas[0]), Integer.parseInt(escalas[1]), Integer.parseInt(escalas[2])), idMatriz);
                        listaLinhasDetalhadas.add(lmd);
                    } else {
                        System.out.println("Fator " + fator + " e cobertura " + nomeCobertura);
                    }
                }

            }
        }
        return listaLinhasDetalhadas;
    }

    /**
     * Verifica se um determinado fator de risco já existe na base de dados.
     *
     * @param envolvente nome da envolvente
     * @param lista lista de envolventes da base de dados
     * @return Caso encontre a envolvente, retorna a mesma, caso contrário
     * retorna null
     */
    public static Envolvente verificaEnvolvente(String envolvente, List<Envolvente> lista) {
        for (Envolvente e : lista) {
            if (envolvente.equals(e.getNome())) {
                return e;
            }
        }
        return null;
    }

    /**
     * Verifica se uma cobertura já existe na base de dados
     *
     * @param cobertura Cobertura a verificar
     * @param lista Coberturas existentes na base de dados
     * @return A propria cobertura se existir na base de dados ou null se nao
     * existir
     */
    public static Cobertura verificaCobertura(String cobertura, List<Cobertura> lista) {

        for (Cobertura c : lista) {
            if (cobertura.equals(c.getNome())) {
                return c;
            }
        }
        return null;

    }

    /**
     * Verifica se determinada linha detalhada ja existe na base de dados
     *
     * @param cobertura nome da cobertura
     * @param fatorRisco nome do fator de risco
     * @param lista lista de linhas caraterizadas existentes na base de dados
     * @param idMatriz Valor do id da matriz a qual a linha vai pertencer
     * @return a linha detalhada caso encontre e null se não encontrar
     */
    public static LinhaMatrizCaraterizada verificaLinhaDetalhada(String cobertura, String fatorRisco, List<LinhaMatrizCaraterizada> lista, Long idMatriz) {
        for (LinhaMatrizCaraterizada e : lista) {
            if (cobertura.equals(e.getCobertura().getNome()) && fatorRisco.equals(e.getFatorDeRisco().getNomeEnvolvente() + "/" + e.getFatorDeRisco().getNomeMetrica()) && idMatriz == e.getIdMatriz()) {
                return e;
            }
        }
        return null;
    }

    /**
     * Verifica se uma cobertura ja existe no repositorio
     *
     * @param cobertura Cobertura a verificar
     * @return A propria cobertura se ela ja existe na base de dados ou null
     * caso nao exista
     */
    public static Cobertura verificaExistenciaCobertura(String cobertura) {

        CoberturaRepositorio repo = new CoberturaRepositorioJPAImpl();
        List<Cobertura> lc = repo.findAll();

        for (Cobertura c : lc) {

            if (cobertura.equals(c.getNome())) {
                return c;
            }
        }

        return null;

    }

    /**
     * Verifica se um fator de risco ja existe no repositorio
     *
     * @param fatorRisco Fator de risco a verificar
     * @return O proprio fator de risco se existir ou null se nao existir
     */
    public static FatorRisco verificaExistenciaFatorRisco(String[] fatorRisco) {

        FatorDeRiscoRepositorio repo2 = new FatorDeRiscoRepositorioJPAImpl();
        List<FatorRisco> lf = repo2.findAll();

        for (FatorRisco f : lf) {

            if (fatorRisco[0].equals(f.getEnvolvente().getNome()) && fatorRisco[1].equals(f.getMetrica().getNome())) {
                return f;
            }
        }

        return null;

    }

    /**
     * Verifica se uma determinada linha de matriz base ja existe numa lista
     *
     * @param cobertura Cobertura da linha a verificar
     * @param fatorRisco Fator de risco da linha a verificar
     * @param lista Lista a iterar
     * @param idMatriz Valor do id da matriz a qual a linha vai pertencer
     * @return A propria linha de matriz base se esta ja existia ou null se nao
     * existia
     */
    public static LinhaMatrizBase verificaLinhaBase(String cobertura, String fatorRisco, List<LinhaMatrizBase> lista, Long idMatriz) {
        for (LinhaMatrizBase e : lista) {
            if (cobertura.equals(e.getCobertura().getNome()) && fatorRisco.equals(e.getFatorRisco().getNomeEnvolvente() + "/" + e.getFatorRisco().getNomeMetrica()) && idMatriz == e.getIdMatriz()) {
                return e;
            }
        }
        return null;
    }

    /**
     * Le linhas de matriz caraterizadas de varios ficheiros e retorna uma lista
     * com as linhas criadas para recaraterizacao
     *
     * @param caminhoFicheiro1 Caminho do ficheiro da matriz caraterizada com o
     * peso
     * @param caminhoFicheiro2 Caminho do ficheiro da matriz caraterizada com a
     * necessidade
     * @param caminhoFicheiro3 Caminho do ficheiro da matriz caraterizada com a
     * contribuicao
     * @param idMatriz Id da matriz à qual a linha vai pertencer
     * @return Lista de linhas de matriz caraterizadas
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static ArrayList<LinhaMatrizCaraterizada> recaraterizarLinhasMatrizCaracterizada(String caminhoFicheiro1, String caminhoFicheiro2, String caminhoFicheiro3, Long idMatriz) throws FileNotFoundException, IOException {

        BufferedReader br = null;
        br = new BufferedReader(new FileReader(caminhoFicheiro1));
        ArrayList<LinhaMatrizCaraterizada> listaLinhasCaraterizadas = new ArrayList<>();

        String line = "";
        String csvSplitBy = ",";
        String coberturaCaracterizacoes[] = null;
        line = br.readLine();
        String[] fatores = line.split(csvSplitBy);

        while ((line = br.readLine()) != null) {
            coberturaCaracterizacoes = line.split(csvSplitBy);

            for (int i = 1; i < fatores.length; i++) {
                String caracterizacao = coberturaCaracterizacoes[i];
                if (!caracterizacao.equals("(n/a)")) {
                    LinhaMatrizCaraterizada l2 = new LinhaMatrizCaraterizada(new Peso(Integer.parseInt(caracterizacao)));
                    listaLinhasCaraterizadas.add(l2);
                }

            }

        }

        br = new BufferedReader(new FileReader(caminhoFicheiro2));

        br.readLine();
        int cont = 0;

        while ((line = br.readLine()) != null) {

            coberturaCaracterizacoes = line.split(csvSplitBy);

            for (int i = 1; i < fatores.length; i++) {
                String caracterizacao = coberturaCaracterizacoes[i];
                if (!caracterizacao.equals("(n/a)")) {
                    listaLinhasCaraterizadas.get(cont).setNecessidade(new Necessidade(caracterizacao));
                    cont++;
                }
            }

        }

        br = new BufferedReader(new FileReader(caminhoFicheiro3));

        br.readLine();
        cont = 0;

        while ((line = br.readLine()) != null) {

            coberturaCaracterizacoes = line.split(csvSplitBy);

            for (int i = 1; i < fatores.length; i++) {
                String caracterizacao = coberturaCaracterizacoes[i];
                if (!caracterizacao.equals("(n/a)")) {
                    listaLinhasCaraterizadas.get(cont).setContribuicao(new Contribuicao(caracterizacao));
                    cont++;
                }
            }

        }

        return listaLinhasCaraterizadas;

    }

    /**
     * Vai buscar todos os fatores de risco existentes na MatrizRisco e adiciona
     * a uma lista. Verifica se o fator de risco existe na MatrizRisco, senão
     * adiciona à lista a retornar
     *
     * @param mr Matriz de risco a verificar
     * @return
     */
    public HashSet<FatorRisco> fatoresNaoExistentes(MatrizRisco mr) {

        MatrizRiscoRepositorio m = new MatrizRiscoRepositorioJPAImpl();

        FatorDeRiscoRepositorio mrr = new FatorDeRiscoRepositorioJPAImpl();
        List<FatorRisco> all = mrr.findAll();

        // Para testar caso haja um fator de risco inexistente
        //EnvolventeRepositorio en = new EnvolventeRepositorioJPAImpl();
        //List<Envolvente> listaEnvolventes = en.findAll();
        //Envolvente ee = Envolvente.verificaEnvolvente("bombeiros", listaEnvolventes);
        //Metrica met = new Metrica();
        //FatorRisco frr = new FatorRisco(ee, met);
        //all.add(frr);
        HashSet<FatorRisco> fatoresNexistentes = new HashSet<>();
        List<Long> fatoresExistentes = new ArrayList<>();

        for (LinhaMatrizDetalhada l : mr.linhasMatrizDetalhada.getList()) {
            fatoresExistentes.add(l.getFatorRisco().getId());
        }

        for (FatorRisco fr : all) {
            if (!(fatoresExistentes.contains(fr.getId()))) {
                fatoresNexistentes.add(fr);
            }
        }
        return fatoresNexistentes;
    }

    public MatrizRisco verificarIdMatrizRisco(Long id) {

        MatrizRiscoRepositorio mrepo = new MatrizRiscoRepositorioJPAImpl();

        MatrizRisco mr1 = mrepo.findById(id);

        return mr1;
    }

}
