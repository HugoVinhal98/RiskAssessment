/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Dominio.CoberturaOS;
import Dominio.EnvolventeOS;
import Dominio.FatorRisco;
import Dominio.LinhaMatrizDetalhada;
import Dominio.MatrizRisco;
import Dominio.ObjetoSeguro;
import Persistencia.MatrizRiscoRepositorio;
import Persistencia.MatrizRiscoRepositorioJPAImpl;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jmbosg
 */
public class AvaliacaoRiscoService {

    /**
     * Calcula o score maximo para um dado objeto seguro
     *
     * @param os Objeto seguro a avaliar
     * @param idMatriz Id da matriz a ser usada na avaliacao de risco
     * @return Valor do score maximo e sua construcao
     */
    public static Object[] obterScoreMaximo(ObjetoSeguro os, long idMatriz) {

        MatrizRiscoRepositorio mrr = new MatrizRiscoRepositorioJPAImpl();
        MatrizRisco mr = mrr.findById(idMatriz);
        int valorMaxEscala = 0;
        int scoreMaximo = 0;
        int valorPeso;
        StringBuilder sb = new StringBuilder();

        int temp;

        List<String> l1 = os.getListaCoberturas();
        List<EnvolventeOS> l2 = GeoReferenceService.getEnvolventeOSList();

        // Para linhas cobertas pelo GeoReference
        for (String cOS : l1) {

            String nomeCoberturaOS = cOS;

            for (LinhaMatrizDetalhada lmd : mr.getLinhasMatrizDetalhada()) {

                if (nomeCoberturaOS.equalsIgnoreCase(lmd.getCobertura().getNome())) {

                    valorPeso = lmd.getLinhaMatrizCaraterizada().getPeso().getPeso();

                    FatorRisco fr = lmd.getLinhaMatrizCaraterizada().getLinhaMatrizBase().getFatorRisco();

                    for (EnvolventeOS e : l2) {

                        if (fr.getEnvolvente().getNome().equalsIgnoreCase(e.getNome())) {

                            if (fr.getMetrica().getNome().equalsIgnoreCase("distancia minima")) {

                                if (e.getTipoDistancia() != null) {

                                    valorMaxEscala = lmd.getEscala().getAlto();

                                    temp = valorMaxEscala * valorPeso;

                                    scoreMaximo += temp;

                                    sb.append("+(" + valorPeso + "*" + valorMaxEscala + ")");

                                } else {

                                    if (lmd.getLinhaMatrizCaraterizada().getNecessidade().getNecessidade().equalsIgnoreCase("obrigatorio")) {

                                        valorMaxEscala = lmd.getEscala().getAlto();

                                        temp = valorMaxEscala * valorPeso;

                                        scoreMaximo += temp;

                                        sb.append("+(" + valorPeso + "*" + valorMaxEscala + ")");
                                    }

                                }

                            }

                            if (fr.getMetrica().getNome().equalsIgnoreCase("tempo minimo")) {

                                if (e.getTipoTempo() != null) {

                                    valorMaxEscala = lmd.getEscala().getAlto();

                                    temp = valorMaxEscala * valorPeso;

                                    scoreMaximo += temp;

                                    sb.append("+(" + valorPeso + "*" + valorMaxEscala + ")");

                                } else {

                                    if (lmd.getLinhaMatrizCaraterizada().getNecessidade().getNecessidade().equalsIgnoreCase("obrigatorio")) {

                                        valorMaxEscala = lmd.getEscala().getAlto();

                                        temp = valorMaxEscala * valorPeso;

                                        scoreMaximo += temp;

                                        sb.append("+(" + valorPeso + "*" + valorMaxEscala + ")");

                                    }

                                }

                            }

                        }

                    }

                }

            }

        }

        // Para linhas não cobertas pelo GeoReference
        for (String cOS : l1) {

            String nomeCoberturaOS = cOS;

            for (LinhaMatrizDetalhada lmd : mr.getLinhasMatrizDetalhada()) {

                if (nomeCoberturaOS.equalsIgnoreCase(lmd.getCobertura().getNome())) {

                    valorPeso = lmd.getLinhaMatrizCaraterizada().getPeso().getPeso();

                    FatorRisco fr = lmd.getLinhaMatrizCaraterizada().getLinhaMatrizBase().getFatorRisco();

                    boolean flag = false;

                    for (EnvolventeOS e1 : l2) {

                        if (fr.getEnvolvente().getNome().equalsIgnoreCase(e1.getNome())) {

                            flag = true;

                        }

                    }

                    if (flag == false) {

                        if (lmd.getLinhaMatrizCaraterizada().getNecessidade().getNecessidade().equalsIgnoreCase("obrigatorio")) {

                            valorMaxEscala = lmd.getEscala().getAlto();

                            temp = valorPeso * valorMaxEscala;

                            scoreMaximo += temp;

                            valorMaxEscala = 0;
                            valorPeso = 0;

                            sb.append("+(" + valorPeso + "*" + valorMaxEscala + ")");

                        }

                    }

                }

            }

        }

        String details = sb.toString();

        Object[] objectsToReturn = new Object[2];

        objectsToReturn[0] = (Object) scoreMaximo;
        objectsToReturn[1] = (Object) details;

        return objectsToReturn;

    }

    /**
     * Calcula o score obtido para um dado objeto seguro
     *
     * @param os Objeto Seguro a avaliar
     * @param idMatriz Id da matriz a ser usada na avaliacao de risco
     * @return Valor do score obtido e sua construcao
     */
    public static Object[] obterScoreObtido(ObjetoSeguro os, long idMatriz) {

        MatrizRiscoRepositorio mrr = new MatrizRiscoRepositorioJPAImpl();
        MatrizRisco mr = mrr.findById(idMatriz);
        int valorEscala = 0;
        int valorPeso;
        int scoreObtido = 0;
        int temp;
        StringBuilder sb = new StringBuilder();

        List<String> l1 = os.getListaCoberturas();
        List<EnvolventeOS> l2 = GeoReferenceService.getEnvolventeOSList();

        // Para linhas cobertas pelo GeoReference
        for (String cOS : l1) {

            String nomeCoberturaOS = cOS;

            for (LinhaMatrizDetalhada lmd : mr.getLinhasMatrizDetalhada()) {

                if (nomeCoberturaOS.equalsIgnoreCase(lmd.getCobertura().getNome())) {

                    valorPeso = lmd.getLinhaMatrizCaraterizada().getPeso().getPeso();

                    FatorRisco fr = lmd.getLinhaMatrizCaraterizada().getLinhaMatrizBase().getFatorRisco();

                    for (EnvolventeOS e : l2) {

                        if (fr.getEnvolvente().getNome().equalsIgnoreCase(e.getNome())) {

                            if (fr.getMetrica().getNome().equalsIgnoreCase("distancia minima")) {

                                if (e.getTipoDistancia() != null) {

                                    String distancia = e.getTipoDistancia().getTipoDistancia();

                                    if (distancia.equalsIgnoreCase("baixo")) {

                                        valorEscala = lmd.getEscala().getBaixo();

                                    }

                                    if (distancia.equalsIgnoreCase("medio")) {

                                        valorEscala = lmd.getEscala().getMedio();

                                    }

                                    if (distancia.equalsIgnoreCase("alto")) {

                                        valorEscala = lmd.getEscala().getAlto();

                                    }

                                    temp = valorEscala * valorPeso;

                                    scoreObtido += temp;

                                    sb.append("+(" + valorPeso + "*" + valorEscala + ")");

                                } else {

                                    if (lmd.getLinhaMatrizCaraterizada().getNecessidade().getNecessidade().equalsIgnoreCase("obrigatorio")) {

                                        if (lmd.getLinhaMatrizCaraterizada().getContribuicao().getContribuicao().equalsIgnoreCase("negativa")) {

                                            valorEscala = lmd.getEscala().getAlto();

                                        }

                                        if (lmd.getLinhaMatrizCaraterizada().getContribuicao().getContribuicao().equalsIgnoreCase("positiva")) {

                                            valorEscala = lmd.getEscala().getBaixo();

                                        }

                                    }

                                }

                            }

                            if (fr.getMetrica().getNome().equalsIgnoreCase("tempo minimo")) {

                                if (e.getTipoTempo() != null) {

                                    String tempo = e.getTipoTempo().getTipoTempo();

                                    if (tempo.equalsIgnoreCase("baixo")) {

                                        valorEscala = lmd.getEscala().getBaixo();

                                    }

                                    if (tempo.equalsIgnoreCase("medio")) {

                                        valorEscala = lmd.getEscala().getMedio();

                                    }

                                    if (tempo.equalsIgnoreCase("alto")) {

                                        valorEscala = lmd.getEscala().getAlto();

                                    }

                                    temp = valorEscala * valorPeso;

                                    scoreObtido += temp;

                                    sb.append("+(" + valorPeso + "*" + valorEscala + ")");

                                } else {

                                    if (lmd.getLinhaMatrizCaraterizada().getNecessidade().getNecessidade().equalsIgnoreCase("obrigatorio")) {

                                        if (lmd.getLinhaMatrizCaraterizada().getContribuicao().getContribuicao().equalsIgnoreCase("negativa")) {

                                            valorEscala = lmd.getEscala().getAlto();

                                        }

                                        if (lmd.getLinhaMatrizCaraterizada().getContribuicao().getContribuicao().equalsIgnoreCase("positiva")) {

                                            valorEscala = lmd.getEscala().getBaixo();

                                        }

                                    }

                                }

                            }

                        }

                    }

                }

            }

        }

        // Para linhas não cobertas pelo GeoReference
        for (String cOS : l1) {

            String nomeCoberturaOS = cOS;

            for (LinhaMatrizDetalhada lmd : mr.getLinhasMatrizDetalhada()) {

                if (nomeCoberturaOS.equalsIgnoreCase(lmd.getCobertura().getNome())) {

                    valorPeso = lmd.getLinhaMatrizCaraterizada().getPeso().getPeso();

                    FatorRisco fr = lmd.getLinhaMatrizCaraterizada().getLinhaMatrizBase().getFatorRisco();

                    boolean flag = false;

                    for (EnvolventeOS e1 : l2) {

                        if (fr.getEnvolvente().getNome().equalsIgnoreCase(e1.getNome())) {

                            flag = true;

                        }

                    }

                    if (flag == false) {

                        if (lmd.getLinhaMatrizCaraterizada().getNecessidade().getNecessidade().equalsIgnoreCase("obrigatorio")) {

                            if (lmd.getLinhaMatrizCaraterizada().getContribuicao().getContribuicao().equalsIgnoreCase("negativa")) {

                                valorEscala = lmd.getEscala().getAlto();

                            }

                            if (lmd.getLinhaMatrizCaraterizada().getContribuicao().getContribuicao().equalsIgnoreCase("positiva")) {

                                valorEscala = lmd.getEscala().getBaixo();

                            }

                            temp = valorPeso * valorEscala;

                            scoreObtido += temp;

                            valorEscala = 0;
                            valorPeso = 0;

                            sb.append("+(" + valorPeso + "*" + valorEscala + ")");
                            
                        }

                    }

                }

            }

        }

        String details = sb.toString();

        Object[] objectsToReturn = new Object[2];

        objectsToReturn[0] = (Object) scoreObtido;
        objectsToReturn[1] = (Object) details;

        return objectsToReturn;

    }

    /**
     * Calcula o Indice de risco de avaliacao atraves do score obtido e do score
     * maximo
     *
     * @param scoreObtido Valor do score obtido no processamento de risco de um
     * objeto seguro
     * @param scoreMaximo Valor do score maximo no processamento de risco de um
     * objeto seguro
     * @return Valor do indice de risco de avaliacao
     */
    public static float calculaIndiceRiscoAvaliacao(int scoreObtido, int scoreMaximo) {

        return (float) scoreObtido / (float) scoreMaximo;

    }

}
