package br.capitolio.tools.reflection;

import br.capitolio.tools.ToolsException;

public class ReflectionException extends ToolsException {

    public ReflectionException(String code, String message) {
        this(code, message, null);
    }

    public ReflectionException(String code, String message, Throwable cause) {
        super(code, message, cause);
    }
}
