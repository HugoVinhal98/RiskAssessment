/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacao;

import Dominio.Cobertura;
import Persistencia.CoberturaRepositorio;
import Persistencia.CoberturaRepositorioJPAImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import Utils.Email;
import Utils.LerFicheiros;

/**
 *
 * @author franc
 */
public class LerCoberturasFicheiroController {

    /**
     * Controller do UC ler coberturas de ficheiro
     * 
     * @param caminhoFicheiro Caminho do ficheiro de onde as coberturas vao ser lidas
     * @return Lista de coberturas lidas
     * @throws IOException 
     */
    public ArrayList<Cobertura> lerCoberturasFicheiro(String caminhoFicheiro) throws IOException {
        
        ArrayList<Cobertura> lc = LerFicheiros.lerCoberturaDeFicheiro(caminhoFicheiro);
        CoberturaRepositorio cRepo = new CoberturaRepositorioJPAImpl();
        List<Cobertura> l = cRepo.findAll();

        boolean flag = true;
        for (Cobertura c : lc) {
            flag = true;
            for (Cobertura c2 : l) {
                if (c.equals(c2)) {
                    System.out.println("Cobertura: " + c + " já existe na BD");
                    flag = false;
                }
            }
            
            if (flag == true) {
                cRepo.add(c);
            }

        }
        String body = "Foram criadas as seguintes coberturas: ";
        for(Cobertura cob : lc){
            body = body + " \n -" + cob.getNome();
        }
        // Comentar para evitar overload de emails!
        //Email.sendFromGMail("testeemailg47@gmail.com", "gandapatrick", "testeemailg47@gmail.com", "Coberturas novas", body);  
        return lc; 

    }

}
