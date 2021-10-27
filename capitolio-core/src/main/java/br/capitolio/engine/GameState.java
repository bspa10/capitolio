package br.capitolio.engine;

public abstract class GameState {
    private GameState(){}

    private static boolean shouldStop = false;
    public static boolean shouldStop() {
        return shouldStop;
    }

    public static void setShouldStop() {
        shouldStop = true;
    }

}
