package br.capitolio.tools.reflection;

import br.capitolio.tools.ToolsException;

public class ReflectionException extends ToolsException {

    public ReflectionException(String message) {
        this(message, null);
    }

    public ReflectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
