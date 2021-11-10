package br.capitolio.tools.cdi;

import br.capitolio.tools.ToolsException;

public final class CDIException extends ToolsException {

    public CDIException(String code, String message) {
        this(code, message, null);
    }

    public CDIException(String code, String message, Throwable cause) {
        super(code, message, cause);
    }
}
