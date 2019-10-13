/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacao;

import Dominio.Envolvente;
import Persistencia.EnvolventeRepositorio;
import Persistencia.EnvolventeRepositorioJPAImpl;
import Utils.LerFicheiros;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jmbosg
 */
public class LerEnvolventesFicheiroController {
    
    /**
     * Controller do UC ler envolventes de ficheiro
     * 
     * @param caminhoFicheiro Caminho do ficheiro de onde as envolventes vao ser lidas
     * @return Lista de envolventes lidas
     * @throws IOException 
     */
    public ArrayList<Envolvente> lerEnvolventesDeFicheiro(String caminhoFicheiro) throws IOException {
        
        ArrayList<Envolvente> l1 = LerFicheiros.lerEnvolventesDeFicheiro(caminhoFicheiro);
        EnvolventeRepositorio repo = new EnvolventeRepositorioJPAImpl();
        List<Envolvente> l2 = repo.findAll();

        boolean flag = false;

        for (Envolvente e : l1) {
            flag = false;
            for (Envolvente e2 : l2) {
                if(e.equals(e2)) {
                    System.out.println("Envolvente " + e + " já existe!");
                    flag = true;
                }
            }
            if(flag == false) {
                repo.add(e);
            }
        }

        return l1;

    }

}
