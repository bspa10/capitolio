package br.capitolio.engine.logging;

public abstract class AbstractLogger {
    protected String loggerName;
    public final void setLoggerName(String desired) {
        loggerName = desired;
    }

}
