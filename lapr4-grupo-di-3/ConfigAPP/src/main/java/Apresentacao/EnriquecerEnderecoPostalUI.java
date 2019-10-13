/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apresentacao;

import Aplicacao.EnriquecerEnderecoPostalController;
import eapli.framework.util.Console;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diogo Rolo
 */
public class EnriquecerEnderecoPostalUI {

    /**
     * Controller do UC importar matriz detalhada
     */
    private final EnriquecerEnderecoPostalController controller = new EnriquecerEnderecoPostalController();

    public void obterDados() throws Exception {
        System.out.println("Enriquecer Endereço Postal: \n\n");

        String local = Console.readLine("Qual a localidade?");

        String cod = Console.readLine("Qual o codigo postal?");

        String rua = Console.readLine("Qual o nome da rua e/ou porta?");

        String service = Console.readLine("Qual o servico que pretende utilizar? (Bing ou Google)");

        List<String> lista = new ArrayList<>();
        // qual dos serviços o utilizador escolhe 
        if (service.trim().compareToIgnoreCase("google") == 0) {
            lista = controller.obterDadosGoogle(cod, rua, local);
            //System.out.println(lista);
        } else if (service.trim().compareToIgnoreCase("bing") == 0) {
            lista = controller.obterDadosBing(rua, local, cod);
            //System.out.println(lista);
        } else {
            System.out.println("Serviço escolhido inválido!");
        }
        //Persistir objeto seguro com a sua informação (end, rua, local, distrito, pais)
        if (lista.size() == 2) {
            controller.persistirObjetoSeguro(cod, rua, local, lista.get(0), lista.get(1));
            System.out.println("Dados Persistidos!");
        } else if (lista.isEmpty()) {
            System.out.println("Não foram encontrados resultados para os dados inseridos!");
        }
        lista = null;
    }
}
