package br.capitolio.tools;

import br.capitolio.CapitolioException;

public abstract class ToolsException extends CapitolioException {

    public ToolsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ToolsException(String message) {
        this(message, null);
    }

}
