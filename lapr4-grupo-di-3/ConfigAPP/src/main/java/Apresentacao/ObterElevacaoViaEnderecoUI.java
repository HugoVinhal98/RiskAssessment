/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apresentacao;

import Aplicacao.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.json.simple.parser.ParseException;

/**
 *
 * @author GilTrindade
 */
public class ObterElevacaoViaEnderecoUI {

    public void obterElevacaoViaEndereco() throws ParseException {
        ObterLocalizacaoViaEnderecoController controller1 = new ObterLocalizacaoViaEnderecoController();
        ObterElevacaoViaEnderecoController controller2 = new ObterElevacaoViaEnderecoController();
        List<Double> lista1 = new ArrayList<>();
        List<Double> lista2 = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        System.out.println("Insira o nome da rua e o numero da porta: ");
        String rua = in.nextLine();

        System.out.println("Insira a localidade: ");
        String localidade = in.nextLine();

        System.out.println("Insira o codigo de postal: ");
        String zipCode = in.nextLine();

        lista1 = controller1.obterLocalizacaoGPSBing(rua, localidade, zipCode);

        lista2 = controller1.obterLocalizacaoGPSGoogle(rua, localidade, zipCode);

        if (lista2 != null) {

            double elevacao = controller2.obterElevacao(lista2.get(0), lista2.get(1));
            System.out.println("A elevação no local de coordenadas " + lista2.get(0) + "," + lista2.get(1) + " é de " + elevacao);
        } else {
            System.out.println("Opção invalida!");
        }

    }
}
