package br.capitolio.minecraft;

import br.capitolio.engine.EngineManager;
import br.capitolio.framework.cdi.Injector;
import br.capitolio.framework.cdi.context.DefaultContext;
import br.capitolio.binding.GLBinding;

public class Application {

    public static void main(String[] args) {
        DefaultContext.load(GLBinding.class);
        DefaultContext.load(GameModule.class);

        final var engine = Injector.inject(EngineManager.class);

        engine.start();
    }

}
