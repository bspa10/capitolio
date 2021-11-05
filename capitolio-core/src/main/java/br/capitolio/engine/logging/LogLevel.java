package br.capitolio.engine.logging;

public enum LogLevel {
    FATAL("FATAL  "),
    ERROR("ERROR  "),
    WARNING("WARNING"),
    INFO("INFO   "),
    DEBUG("DEBUG  "),
    TRACE("TRACE  ");

    private final String formated;
    LogLevel(String formated) {
        this.formated = formated;
    }

    public String formated() {
        return formated;
    }
}
