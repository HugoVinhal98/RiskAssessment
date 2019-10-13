/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apresentacao;

import Aplicacao.RegistarEnvolventeController;
import Dominio.Envolvente;
import Persistencia.EnvolventeRepositorioJPAImpl;
import java.util.Scanner;
import org.json.simple.parser.ParseException;

/**
 *
 * @author hugov
 */
public class RegistarEnvolventeUI {

    public void registarEnvolvente() throws ParseException {
        EnvolventeRepositorioJPAImpl repo = new EnvolventeRepositorioJPAImpl();
        RegistarEnvolventeController controller = new RegistarEnvolventeController();
        Scanner in = new Scanner(System.in);

        System.out.println("Insira o tipo de envolvente: (Por exemplo: hospital, police, pharmacy, fire_station)");
        String tipo = in.nextLine();

        System.out.println("Insira o nome da envolvente:");
        String nome = in.nextLine();

        System.out.println("Insira o endereço postal da envolvente");
        String endereco = in.nextLine();

        Envolvente e = controller.registarEnvolvente(tipo, nome, endereco);
        
        System.out.println(e);
    }
}
