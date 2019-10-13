/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Dominio.EnvolventeOS;
import Dominio.TipoDistancia;
import Dominio.TipoTempo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GilTrindade
 */
public class GeoReferenceService {

    /**
     * Devolve lista de envolventes detetadas pelo servico de geo referenciacao
     * com os dados detetados para serem usados na avaliacao de risco
     *
     * @return Lista de envolventes e os seus dados
     */
    public static List<EnvolventeOS> getEnvolventeOSList() {

        EnvolventeOS eos1 = new EnvolventeOS("bombeiros", "envolvente bombeiros", new TipoDistancia("baixo"), new TipoTempo("baixo"));
        EnvolventeOS eos2 = new EnvolventeOS("policia", "envolvente policia", null, new TipoTempo("baixo"));

        ArrayList<EnvolventeOS> list = new ArrayList<>();
        list.add(eos1);
        list.add(eos2);

        return list;

    }

}
