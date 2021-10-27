package br.capitolio.engine;

import br.capitolio.engine.scene.Scene;

public abstract class Settings {
    private Settings() {}


    private static String windowTitle = "Game";
    public static void setWindowTitle(String windowTitle) {
        Settings.windowTitle = windowTitle;
    }
    public static String getWindowTitle() {
        return Settings.windowTitle;
    }

    private static final int[] windowSize = new int[]{ 640, 480 };
    public static void setWindowSize(int width, int height) {
        Settings.windowSize[0] = width;
        Settings.windowSize[1] = height;
    }
    public static int[] getWindowSize() {
        return Settings.windowSize;
    }

    private static Class<? extends Scene> startScene;
    public static void setStartScene(Class<? extends Scene> scene) {
        Settings.startScene = scene;
    }
    public static Class<? extends Scene> getStartScene() {
        return Settings.startScene;
    }
}
