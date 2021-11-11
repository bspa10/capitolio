package br.capitolio;

public abstract class CapitolioException extends RuntimeException {

    public CapitolioException(String message, Throwable cause) {
        super(message, cause);
    }

    public CapitolioException(String message) {
        this(message, null);
    }
}
