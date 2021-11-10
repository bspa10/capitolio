package br.capitolio.engine.core.logging;

import br.capitolio.engine.EngineException;
import br.capitolio.tools.cdi.Injector;

public abstract class LoggerFactory {
    private LoggerFactory() {}

    public static Logger getLogger(Class<?> klass) {
        final var logger = Injector.inject(Logger.class);
        if ( ! (logger instanceof AbstractLogger)) {
            throw new EngineException("%s does not extends %s".formatted(logger.getClass().getCanonicalName(), AbstractLogger.class.getCanonicalName()));
        }

        ((AbstractLogger) logger).setLoggerName(klass.getCanonicalName());
        return logger;
    }

}
