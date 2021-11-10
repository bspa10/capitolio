package br.capitolio.engine.binding.opengl;

import br.capitolio.engine.binding.BindingException;

public final class GLException extends BindingException {
    public GLException(String code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public GLException(String code, String message) {
        super(code, message);
    }

    public GLException(String message) {
        super(message);
    }
}
