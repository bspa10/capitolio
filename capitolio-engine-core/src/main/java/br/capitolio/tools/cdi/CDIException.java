package br.capitolio.tools.cdi;

import br.capitolio.tools.ToolsException;

public final class CDIException extends ToolsException {

    public CDIException(String message) {
        this(message, null);
    }

    public CDIException(String message, Throwable cause) {
        super(message, cause);
    }
}
