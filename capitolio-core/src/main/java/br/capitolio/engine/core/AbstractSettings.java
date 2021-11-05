package br.capitolio.engine.core;

import br.capitolio.engine.platform.linux.PlatformLinuxModule;
import br.capitolio.framework.cdi.context.DefaultContext;

public class AbstractSettings {
    static {
        final var os = System.getProperty("os.name");
        if (os.contains("nix") || os.contains("nux")) {
            DefaultContext.load(PlatformLinuxModule.class);
        }
    }
}
