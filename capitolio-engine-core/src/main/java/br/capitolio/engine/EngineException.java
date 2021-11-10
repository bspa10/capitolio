package br.capitolio.engine;

import br.capitolio.CapitolioException;

public class EngineException extends CapitolioException {
    public EngineException(String code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public EngineException(String code, String message) {
        super(code, message);
    }

    public EngineException(String message) {
        super(message);
    }
}
