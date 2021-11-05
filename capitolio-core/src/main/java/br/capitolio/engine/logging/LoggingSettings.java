package br.capitolio.engine.logging;

public abstract class LoggingSettings {
    private LoggingSettings(){}

    private static boolean trace = true;
    public static boolean isTrace() {
        return trace;
    }
    public static void setTrace(boolean desired) {
        trace = desired;
    }

    private static boolean debug = true;
    public static boolean isDebug() {
        return debug;
    }
    public static void setDebug(boolean desired) {
        debug = desired;
    }

    private static boolean info = true;
    public static boolean isInfo() {
        return info;
    }
    public static void setInfo(boolean desired) {
        info = desired;
    }

    private static boolean warning = true;
    public static boolean isWarning() {
        return warning;
    }
    public static void setWarning(boolean desired) {
        warning = desired;
    }

}

