package br.capitolio.engine;

import br.capitolio.engine.scene.Scene;
import org.joml.Vector2i;

public abstract class Settings {
    private Settings() {}

    private static String windowTitle = "Game";
    public static void setWindowTitle(String windowTitle) {
        Settings.windowTitle = windowTitle;
    }
    public static String getWindowTitle() {
        return Settings.windowTitle;
    }

    private static final Vector2i windowSize = new Vector2i(640, 480);
    public static void setWindowSize(Vector2i size) {
        Settings.windowSize.set(size);
    }
    public static void setWindowSize(int width, int height) {
        Settings.windowSize.set(width, height);
    }
    public static Vector2i getWindowSize() {
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
