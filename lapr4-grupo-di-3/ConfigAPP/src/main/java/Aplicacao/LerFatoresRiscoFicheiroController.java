/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacao;

import Dominio.FatorRisco;
import Persistencia.FatorDeRiscoRepositorio;
import Persistencia.FatorDeRiscoRepositorioJPAImpl;
import Utils.LerFicheiros;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hugov
 */
public class LerFatoresRiscoFicheiroController {

    /**
     * Lê de um ficheiro os fatores de risco que pretende persistir
     *
     * @param nomeFicheiro nome do ficheiro que contém os fatores de risco
     * @return lista dos fatores de risco criados
     * @throws IOException gera uma mensagem com o erro ocurrido
     */
    public ArrayList<FatorRisco> lerFatoresRiscoDeFicheiro(String nomeFicheiro) throws IOException {
        
        ArrayList<FatorRisco> lc = LerFicheiros.lerFatoresDeRiscoDeFicheiro(nomeFicheiro);
        FatorDeRiscoRepositorio cRepo = new FatorDeRiscoRepositorioJPAImpl();
        
        List<FatorRisco> l = cRepo.findAll();

        boolean flag = true;
        for (FatorRisco fr : lc) {
            flag = true;
            for (FatorRisco c2 : l) {
                if (fr.getEnvolvente().equals(c2.getEnvolvente()) || fr.getMetrica().equals(c2.getMetrica())) {
                    System.out.println("Fator de Risco: " + fr + " já existe na BD");
                    flag = false;
                }
            }
            
            if (flag == true) {
                cRepo.add(fr);
            }

        }

        return lc;

    }
}
