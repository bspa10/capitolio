package br.capitolio.engine.logging;

/**
 * Interface para captura de log.
 */
public interface Logger {

    void fatal (String message, Object ... args);

    void error (String message, Object ... args);

    void warn (String message, Object ... args);

    void info (String message, Object ... args);

    void debug (String message, Object ... args);

    void trace (String message, Object ... args);

}
