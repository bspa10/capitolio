package br.capitolio.framework;

import br.capitolio.CapitolioException;

public class FrameworkException extends CapitolioException {
    public FrameworkException(String code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public FrameworkException(String code, String message) {
        super(code, message);
    }

    public FrameworkException(String message) {
        super(message);
    }
}
