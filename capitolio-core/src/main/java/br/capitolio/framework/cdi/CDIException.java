package br.capitolio.framework.cdi;

import br.capitolio.framework.FrameworkException;

public final class CDIException extends FrameworkException {

    public CDIException(String code, String message) {
        this(code, message, null);
    }

    public CDIException(String code, String message, Throwable cause) {
        super(code, message, cause);
    }
}
