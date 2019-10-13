package Server;

import Bootstrap.BootstrapperSistemaExterno;
import CompararAvaliacoesDeRisco.CompararARController;
import java.io.*;
import java.net.*;

public class HTTPServer {

    static private final String BASE_FOLDER = "www";
    static private ServerSocket sock;

    public static void main(String args[]) throws Exception {

        BootstrapperSistemaExterno b = new BootstrapperSistemaExterno();
        b.executeBootstrap();
        
        CompararARController c = new CompararARController();
        c.criarComparacaoArDTO(1l, 2l, 1l);
        Socket cliSock;

        if (args.length != 1) {
            System.out.println("Local port number required at the command line.");
            System.exit(1);
        }

        try {
            sock = new ServerSocket(Integer.parseInt(args[0]));
        } catch (IOException ex) {
            System.out.println("Failed to open local port " + args[0]);
            System.exit(1);
        }
        while (true) {
            cliSock = sock.accept();
            //Thread test = new Thread(new HTTPRequest(cliSock, BASE_FOLDER));
            Thread test2 = new Thread(new HTTPRequestMultiplos(cliSock, BASE_FOLDER));
           Thread test3 = new Thread (new HTTPRequestDisponibilidade(cliSock, BASE_FOLDER));
          
                 //  test2.setPriority(4);
                  //test3.setPriority(5);
                 //test.start();
                   test2.start();
                    test3.start();
                    
                   
                    
        }

    }
}
