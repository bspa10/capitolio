package br.capitolio.binding;

import br.capitolio.CapitolioException;

public abstract class BindingException extends CapitolioException {
    public BindingException(String code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public BindingException(String code, String message) {
        super(code, message);
    }

    public BindingException(String message) {
        super(message);
    }
}
