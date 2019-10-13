/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apresentacao;

import Aplicacao.ObterLocalizacaoViaEnderecoController;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.json.simple.parser.ParseException;

/**
 *
 * @author hugov
 */
public class ObterLocalizacaoViaEnderecoUI {

    public void obterCoordenadasPorEndereco() throws ParseException {
        ObterLocalizacaoViaEnderecoController cont = new ObterLocalizacaoViaEnderecoController();

        List<Double> lista = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        System.out.println("Bing ou Google?");
        String option = in.nextLine();

        if (option.equalsIgnoreCase("bing") || option.equalsIgnoreCase("google")) {
            System.out.println("Insira o nome da rua e o numero da porta: ");
            String rua = in.nextLine();

            System.out.println("Insira a localidade: ");
            String localidade = in.nextLine();

            System.out.println("Insira o codigo de postal: ");
            String zipCode = in.nextLine();

            if (option.equalsIgnoreCase("bing")) {
                lista = cont.obterLocalizacaoGPSBing(rua, localidade, zipCode);
            } else if (option.equalsIgnoreCase("google")) {
                lista = cont.obterLocalizacaoGPSGoogle(rua, localidade, zipCode);
            }

            if (lista != null) {
                System.out.println("\nLocalizações GPS encontradas através do endereço postal " + ":");
                System.out.println("Latitude: " + lista.get(0) + "\nLongitude: " + lista.get(1));
            }
        } else {
            System.out.println("Opção invalida!");
        }
        
    }
}
