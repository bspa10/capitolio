package br.capitolio.engine;

public abstract class GlobalState {
    private GlobalState(){}

    private static boolean running = false;
    public static boolean isRunning() {
        return running;
    }
    static void setRunning() {
        GlobalState.running = true;
    }
    static void setStopped() {
        GlobalState.running = false;
    }

    private static boolean shouldStop = false;
    public static boolean isShouldStop() {
        return shouldStop;
    }
    public static void setShouldStop() {
        GlobalState.shouldStop = true;
    }
}
