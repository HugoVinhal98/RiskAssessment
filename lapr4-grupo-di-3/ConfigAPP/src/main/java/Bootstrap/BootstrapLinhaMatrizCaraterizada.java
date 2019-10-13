/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bootstrap;

import Dominio.LinhaMatrizBase;
import Dominio.LinhaMatrizCaraterizada;
import Dominio.*;
import Persistencia.*;
import java.util.List;

/**
 *
 * @author Francisco Negrão
 */
public class BootstrapLinhaMatrizCaraterizada {

    public void registerLinhasMatrizCaraterizada() {

        final LinhaMatrizBaseRepositorio mbr = new LinhaMatrizBaseRepositorioJPAImpl();
        final LinhaMatrizCaraterizadaRepositorio mcr = new LinhaMatrizCaraterizadaRepositorioJPAImpl();

        List<LinhaMatrizBase> listMbr = mbr.findAll();
        
        long idMatriz = 1;
        
        final LinhaMatrizCaraterizada linhaMatrizCaraterizada2 = new LinhaMatrizCaraterizada(listMbr.get(1), new Peso(2), new Necessidade ("obrigatorio"), new Contribuicao("positiva"), idMatriz);
        final LinhaMatrizCaraterizada linhaMatrizCaraterizada4 = new LinhaMatrizCaraterizada(listMbr.get(3), new Peso(3), new Necessidade ("facultativo"), new Contribuicao("positiva"), idMatriz);
        final LinhaMatrizCaraterizada linhaMatrizCaraterizada5 = new LinhaMatrizCaraterizada(listMbr.get(4), new Peso(4), new Necessidade ("facultativo"), new Contribuicao("negativa"), idMatriz);
        
        final LinhaMatrizCaraterizada linhaMatrizCaraterizada6 = new LinhaMatrizCaraterizada(listMbr.get(5), new Peso(5), new Necessidade ("obrigatorio"), new Contribuicao("negativa"), idMatriz);   
        final LinhaMatrizCaraterizada linhaMatrizCaraterizada8 = new LinhaMatrizCaraterizada(listMbr.get(7), new Peso(1), new Necessidade ("obrigatorio"), new Contribuicao("positiva"), idMatriz);
        final LinhaMatrizCaraterizada linhaMatrizCaraterizada9 = new LinhaMatrizCaraterizada(listMbr.get(8), new Peso(3), new Necessidade ("obrigatorio"), new Contribuicao("positiva"), idMatriz);
        final LinhaMatrizCaraterizada linhaMatrizCaraterizada10 = new LinhaMatrizCaraterizada(listMbr.get(9), new Peso(3), new Necessidade ("obrigatorio"), new Contribuicao("positiva"), idMatriz);
        
        final LinhaMatrizCaraterizada linhaMatrizCaraterizada12 = new LinhaMatrizCaraterizada(listMbr.get(11), new Peso(2), new Necessidade ("facultativo"), new Contribuicao("positiva"), idMatriz);
        final LinhaMatrizCaraterizada linhaMatrizCaraterizada13 = new LinhaMatrizCaraterizada(listMbr.get(12), new Peso(5), new Necessidade ("facultativo"), new Contribuicao("positiva"), idMatriz);
        final LinhaMatrizCaraterizada linhaMatrizCaraterizada14 = new LinhaMatrizCaraterizada(listMbr.get(13), new Peso(3), new Necessidade ("facultativo"), new Contribuicao("positiva"), idMatriz);
        final LinhaMatrizCaraterizada linhaMatrizCaraterizada15 = new LinhaMatrizCaraterizada(listMbr.get(14), new Peso(4), new Necessidade ("facultativo"), new Contribuicao("negativa"), idMatriz);
        
        final LinhaMatrizCaraterizada linhaMatrizCaraterizada16 = new LinhaMatrizCaraterizada(listMbr.get(15), new Peso(2), new Necessidade ("obrigatorio"), new Contribuicao("positiva"), idMatriz);  
        final LinhaMatrizCaraterizada linhaMatrizCaraterizada18 = new LinhaMatrizCaraterizada(listMbr.get(17), new Peso(4), new Necessidade ("facultativo"), new Contribuicao("negativa"), idMatriz);
        final LinhaMatrizCaraterizada linhaMatrizCaraterizada19 = new LinhaMatrizCaraterizada(listMbr.get(18), new Peso(2), new Necessidade ("obrigatorio"), new Contribuicao("positiva"), idMatriz);
        final LinhaMatrizCaraterizada linhaMatrizCaraterizada20 = new LinhaMatrizCaraterizada(listMbr.get(19), new Peso(3), new Necessidade ("obrigatorio"), new Contribuicao("positiva"), idMatriz);
        
        final LinhaMatrizCaraterizada linhaMatrizCaraterizada21 = new LinhaMatrizCaraterizada(listMbr.get(20), new Peso(4), new Necessidade ("facultativo"), new Contribuicao("positiva"), idMatriz);
        final LinhaMatrizCaraterizada linhaMatrizCaraterizada22 = new LinhaMatrizCaraterizada(listMbr.get(21), new Peso(2), new Necessidade ("obrigatorio"), new Contribuicao("positiva"), idMatriz);
        final LinhaMatrizCaraterizada linhaMatrizCaraterizada24 = new LinhaMatrizCaraterizada(listMbr.get(23), new Peso(1), new Necessidade ("obrigatorio"), new Contribuicao("positiva"), idMatriz);
        
        final LinhaMatrizCaraterizada linhaMatrizCaraterizada27 = new LinhaMatrizCaraterizada(listMbr.get(26), new Peso(5), new Necessidade ("facultativo"), new Contribuicao("positiva"), idMatriz);
        final LinhaMatrizCaraterizada linhaMatrizCaraterizada28 = new LinhaMatrizCaraterizada(listMbr.get(27), new Peso(2), new Necessidade ("obrigatorio"), new Contribuicao("negativa"), idMatriz);
        final LinhaMatrizCaraterizada linhaMatrizCaraterizada30 = new LinhaMatrizCaraterizada(listMbr.get(29), new Peso(4), new Necessidade ("facultativo"), new Contribuicao("negativa"), idMatriz);
        
        mcr.add(linhaMatrizCaraterizada2);
        mcr.add(linhaMatrizCaraterizada4);
        mcr.add(linhaMatrizCaraterizada5);
        mcr.add(linhaMatrizCaraterizada6);
        mcr.add(linhaMatrizCaraterizada8);
        mcr.add(linhaMatrizCaraterizada9);
        mcr.add(linhaMatrizCaraterizada10);
        mcr.add(linhaMatrizCaraterizada12);
        mcr.add(linhaMatrizCaraterizada13);
        mcr.add(linhaMatrizCaraterizada14);
        mcr.add(linhaMatrizCaraterizada15);
        mcr.add(linhaMatrizCaraterizada16);
        mcr.add(linhaMatrizCaraterizada18);
        mcr.add(linhaMatrizCaraterizada19);
        mcr.add(linhaMatrizCaraterizada20);
        mcr.add(linhaMatrizCaraterizada21);
        mcr.add(linhaMatrizCaraterizada22);
        mcr.add(linhaMatrizCaraterizada24);
        mcr.add(linhaMatrizCaraterizada27);
        mcr.add(linhaMatrizCaraterizada28);
        mcr.add(linhaMatrizCaraterizada30);
        
    }

}
