package br.capitolio.engine.core.logging;

import br.capitolio.engine.EngineException;

public abstract class AbstractLogger {
    protected String loggerName;

    public final void setLoggerName(String desired) {
        if (loggerName != null)
            throw new EngineException("Logger Name already set");

        loggerName = desired;
    }

}
