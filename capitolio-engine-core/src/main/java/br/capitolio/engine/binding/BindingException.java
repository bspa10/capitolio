package br.capitolio.engine.binding;

import br.capitolio.CapitolioException;

public abstract class BindingException extends CapitolioException {
    public BindingException(String message, Throwable cause) {
        super(message, cause);
    }

    public BindingException(String message) {
        this(message, null);
    }
}
