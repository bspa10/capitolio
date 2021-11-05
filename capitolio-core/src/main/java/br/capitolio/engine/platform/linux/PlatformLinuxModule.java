package br.capitolio.engine.platform.linux;

import br.capitolio.engine.logging.Logger;
import br.capitolio.engine.platform.linux.logging.ConsoleLogger;
import br.capitolio.framework.cdi.annotation.Module;
import br.capitolio.framework.cdi.annotation.Provider;
import br.capitolio.tools.reflection.Reflections;

@Module
public final class PlatformLinuxModule {

    @Provider(overridable = false)
    public Logger logger() {
        return Reflections.Classes.newInstance(ConsoleLogger.class);
    }

}
