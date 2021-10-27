package br.capitolio;

public abstract class CapitolioException extends RuntimeException {
    private final String code;

    public CapitolioException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public CapitolioException(String code, String message) {
        this(code, message, null);
    }

    public CapitolioException(String message) {
        this(null, message);
    }

    public String getCode() {
        return code;
    }
}
