/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bootstrap;

/**
 *
 * @author Jmbosg
 */
public class BootstrapperSistemaExterno {

    public void executeBootstrap() {
        
        BootstrapCobertura bootstrapCobertura = new BootstrapCobertura();
        bootstrapCobertura.registerCobertura();

        BootstrapEnvolvente bootstrapEnvolvente = new BootstrapEnvolvente();
        bootstrapEnvolvente.registerEnvolvente();

        BootstrapFatorRisco bootstrapFatorRisco = new BootstrapFatorRisco();
        bootstrapFatorRisco.registerFatorRisco();

        BootstrapLinhaMatrizBase bootstrapLinhaMatrizBase = new BootstrapLinhaMatrizBase();
        bootstrapLinhaMatrizBase.registerLinhasMatrizBase();

        BootstrapLinhaMatrizCaraterizada bootstrapLinhaMatrizCaraterizada = new BootstrapLinhaMatrizCaraterizada();
        bootstrapLinhaMatrizCaraterizada.registerLinhasMatrizCaraterizada();

        BootstrapLinhaMatrizDetalhada bootstrapLinhaMatrizDetalhada = new BootstrapLinhaMatrizDetalhada();
        bootstrapLinhaMatrizDetalhada.registerLinhasMatrizDetalhada();

        BootstrapMatrizRisco bootstrapMatrizRisco = new BootstrapMatrizRisco();
        bootstrapMatrizRisco.registerMatrizRisco();
        
        BoostrapPedidoExterno bpe = new BoostrapPedidoExterno();
        bpe.bootstrap();
        
    }
}
