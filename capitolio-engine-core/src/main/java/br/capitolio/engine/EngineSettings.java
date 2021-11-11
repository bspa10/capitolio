package br.capitolio.engine;

import br.capitolio.engine.core.AbstractSettings;
import br.capitolio.engine.core.logging.Logger;
import br.capitolio.engine.core.logging.LoggerFactory;
import org.joml.Math;
import org.joml.Vector2i;

public abstract class EngineSettings extends AbstractSettings {
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
        LOGGER.debug("Configuring Window Size [%s, %s]", desired.x, desired.y);
        windowSize.set(desired);
    }

    private static boolean vsync;
    public static boolean isVsync() {
        return vsync;
    }
    public static void setVsync(boolean desired) {
        LOGGER.debug("Configuring VSYNC [%s]", desired);
        vsync = desired;
    }

    private static String windowTitle = "Game";
    public static String getWindowTitle() {
        return windowTitle;
    }
    public static void setWindowTitle(String desired) {
        LOGGER.debug("Configuring Window Title [%s]", desired);
        windowTitle = desired;
    }

    private static boolean profile = false;
    public static boolean isProfile() {
        return profile;
    }
    public static void setProfile() {
        EngineSettings.profile = true;
    }
    public static void unsetProfile() {
        EngineSettings.profile = false;
    }

    private static long framerate = 240L;
    public static long getFramerate() {
        return framerate;
    }
    public static void setFramerate(long framerate) {
        EngineSettings.framerate = framerate;
    }

    private static float fieldOfView = Math.toRadians(60.0f);
    public static float getFieldOfView() {
        return fieldOfView;
    }
    public static void setFieldOfView(float fieldOfView) {
        EngineSettings.fieldOfView = Math.toRadians(fieldOfView);
    }

    private static float zNear = 0.01f;
    public static float getzNear() {
        return zNear;
    }
    public static void setzNear(float zNear) {
        EngineSettings.zNear = zNear;
    }

    private static float zFar = 1000f;
    public static float getzFar() {
        return zFar;
    }
    public static void setzFar(float zFar) {
        EngineSettings.zFar = zFar;
    }

}
