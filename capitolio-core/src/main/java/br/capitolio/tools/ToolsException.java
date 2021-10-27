package br.capitolio.tools;

import br.capitolio.CapitolioException;

public abstract class ToolsException extends CapitolioException {

    public ToolsException(String code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public ToolsException(String code, String message) {
        this(code, message, null);
    }

}
