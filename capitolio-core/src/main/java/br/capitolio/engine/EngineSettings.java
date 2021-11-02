package br.capitolio.engine;

import org.joml.Vector2i;

public abstract class EngineSettings {

    private EngineSettings(){}

    private static final Vector2i windowSize = new Vector2i();
    public static Vector2i getWindowSize() {
        return windowSize;
    }
    public static void setWindowSize(int width, int height) {
        windowSize.set(width, height);
    }
    public static void setWindowSize(Vector2i desired) {
        windowSize.set(desired);
    }

    private static boolean vsync;
    public static boolean isVsync() {
        return vsync;
    }
    public static void setVsync(boolean desired) {
        vsync = desired;
    }

    private static String windowTitle = "Game";
    public static String getWindowTitle() {
        return windowTitle;
    }
    public static void setWindowTitle(String desired) {
        windowTitle = desired;
    }
}
