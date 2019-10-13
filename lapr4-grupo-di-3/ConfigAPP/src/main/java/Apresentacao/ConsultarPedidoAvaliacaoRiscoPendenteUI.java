/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apresentacao;

import Aplicacao.ConsultarPedidoDeAvaliacaoRiscoPendenteController;
import Dominio.AnalistaRisco;
import Dominio.Pedido;
import Persistencia.AnalistaRiscoRepositorioJPAImpl;
import Persistencia.PedidoRepositorioJPAImpl;
import eapli.framework.util.Console;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Francisco Negrão
 */
public class ConsultarPedidoAvaliacaoRiscoPendenteUI {

    private final ConsultarPedidoDeAvaliacaoRiscoPendenteController controller = new ConsultarPedidoDeAvaliacaoRiscoPendenteController();
    private LoginARUI ui = new LoginARUI();
    

    public void consultarPedidoAvaliacaoRiscoPendenteLocalizacao() {
        List<Pedido> lista = new ArrayList<>();
        

        String cidade = Console.readLine("Indique a cidade");
        lista = controller.pedidosPendentesAvaliacaoLocalizacao(cidade);

        System.out.println("A lista é : ");
        for (int i = 0; i < lista.size(); i++) {
            Pedido p = lista.get(i);
            System.out.println(p.toString());
        }
    }

    public void consultarPedidoAvaliacaoRiscoPendenteOrdenados() {
        List<Pedido> lista = new ArrayList<>();
        lista = controller.pedidosPendentesAvaliacaoOrdenados();

        System.out.println("A lista é : ");
        for (int i = 0; i < lista.size(); i++) {
            Pedido p = lista.get(i);
            System.out.println(p.toString());

        }

    }
    public void atribuirPedidoAAnalista(){
        Pedido pedidoFinal = new Pedido();
        List<Pedido> lista = new ArrayList<>();
       
       lista = controller.pedidosPendentesAvaliacaoOrdenados();
       for (int i = 0; i < lista.size(); i++) {
            Pedido p = lista.get(i);
            System.out.println(p.toString());

        }
        System.out.println("Selecione o id do Pedido que pretende atribuir");
        long id = Console.readLong("Indique o id");
         for (int i = 0; i < lista.size(); i++) {
            Pedido p = lista.get(i);
            
             long idp = p.getId();
             if(idp == id){
                 pedidoFinal = p;
             }
        }  
         boolean valida = false;
           AnalistaRiscoRepositorioJPAImpl repo = new AnalistaRiscoRepositorioJPAImpl();
            List<AnalistaRisco> listaRisco= repo.findAll();
         String nomeUtilizador = "";
            while(valida == false){
           nomeUtilizador = Console.readLine("Indique o seu nome de utilizador");
          
           for(int i = 0; i<listaRisco.size(); i++){
               AnalistaRisco ar = listaRisco.get(i);
               if(nomeUtilizador.equalsIgnoreCase(ar.getNomeUtilizador().getNomeUtilizador())){
                   valida = true;
               }
           }
         }
           pedidoFinal.setNomeUtilizador(nomeUtilizador);
          registarPedido(pedidoFinal);
         List<Pedido> lista2 = new ArrayList<>();
         System.out.println("Operação com sucesso"); 
        
         
    }
    public void registarPedido(Pedido p){
        PedidoRepositorioJPAImpl repo = new PedidoRepositorioJPAImpl();
        List<Pedido> lista = repo.findAll();
        for(int i =0; i < lista.size(); i++ ){
             Pedido p2 = lista.get(i);
             if(p2.getId().equals(p.getId())){
                 repo.remove(p2.getId());
                 repo.add(p);
             }
          
            
        }
        
    }
    
}
