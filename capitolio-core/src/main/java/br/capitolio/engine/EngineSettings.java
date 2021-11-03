package br.capitolio.engine;

import org.joml.Vector2i;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class EngineSettings {
    private static final Logger LOGGER = LoggerFactory.getLogger(EngineSettings.class);

    private EngineSettings(){}

    private static final Vector2i windowSize = new Vector2i();
    public static Vector2i getWindowSize() {
        return windowSize;
    }
    public static void setWindowSize(int width, int height) {
        setWindowSize(new Vector2i(width, height));
    }
    public static void setWindowSize(Vector2i desired) {
        LOGGER.debug("Configuring Window Size [{}, {}]", desired.x, desired.y);
        windowSize.set(desired);
    }

    private static boolean vsync;
    public static boolean isVsync() {
        return vsync;
    }
    public static void setVsync(boolean desired) {
        LOGGER.debug("Configuring VSYNC [{}]", desired);
        vsync = desired;
    }

    private static String windowTitle = "Game";
    public static String getWindowTitle() {
        return windowTitle;
    }
    public static void setWindowTitle(String desired) {
        LOGGER.debug("Configuring Window Title [{}]", desired);
        windowTitle = desired;
    }
}
