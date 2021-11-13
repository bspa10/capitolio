package br.capitolio.engine;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class GlobalState {
    private GlobalState(){}

    private static final AtomicBoolean running = new AtomicBoolean(false);
    public static boolean isRunning() {
        return running.get();
    }
    static void setRunning() {
        GlobalState.running.getAndSet(true);
    }
    static void setStopped() {
        GlobalState.running.getAndSet(false);
    }

    private static final AtomicBoolean shouldStop = new AtomicBoolean(false);
    public static boolean isShouldStop() {
        return shouldStop.get();
    }
    public static void setShouldStop() {
        GlobalState.shouldStop.getAndSet(true);
    }
}
