package br.capitolio.engine;

import br.capitolio.CapitolioException;

public class EngineException extends CapitolioException {
    public EngineException(String message, Throwable cause) {
        super(message, cause);
    }


    public EngineException(String message) {
        super(message);
    }
}
