package br.capitolio.engine.render;

import br.capitolio.engine.EngineException;

public final class RenderException extends EngineException {

    public RenderException(String message, Throwable cause) {
        super(message, cause);
    }

    public RenderException(String message) {
        super(message);
    }
}
