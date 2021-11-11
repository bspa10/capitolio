package br.capitolio.engine.binding.opengl;

import br.capitolio.engine.binding.BindingException;

public final class GLException extends BindingException {
    public GLException(String message, Throwable cause) {
        super(message, cause);
    }

    public GLException(String message) {
        this(message, null);
    }
}
