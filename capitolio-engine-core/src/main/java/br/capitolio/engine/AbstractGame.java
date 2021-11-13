package br.capitolio.engine;

import br.capitolio.engine.core.logging.Logger;
import br.capitolio.engine.core.logging.LoggerFactory;
import br.capitolio.engine.platform.linux.PlatformLinuxModule;
import br.capitolio.tools.cdi.Injector;
import br.capitolio.tools.cdi.context.DefaultContext;
import br.capitolio.tools.reflection.CLTools;

public abstract class AbstractGame {
    private final Logger LOGGER = LoggerFactory.getLogger(AbstractGame.class);

    static {
        final var os = System.getProperty("os.name");
        if (os.contains("nix") || os.contains("nux")) {
            DefaultContext.load(PlatformLinuxModule.class);
        }
    }

    protected final Engine engine;

    public AbstractGame() {
        final var classes = CLTools.getAllKnownClassesName();
        if (classes.contains("br.capitolio.engine.binding.GLBinding"))
            try {
                LOGGER.info("Using OpenGL Binding");
                DefaultContext.load(Class.forName("br.capitolio.engine.binding.GLBinding"));
            } catch (Exception exception) {
                LOGGER.fatal("Unable to load engine binding [%s]", exception.getLocalizedMessage());
            }

        engine = Injector.inject(Engine.class);
    }
}
