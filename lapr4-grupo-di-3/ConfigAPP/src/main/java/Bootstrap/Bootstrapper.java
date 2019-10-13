/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bootstrap;

/**
 *
 * @author GilTrindade
 */
public class Bootstrapper {

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

        BootstrapPedido bootstrapPedido = new BootstrapPedido();
        bootstrapPedido.bootstrap();

        BootstrapAnalistaRisco bootstrapAnalistaRisco = new BootstrapAnalistaRisco();
        bootstrapAnalistaRisco.bootstrap();

    }
}
