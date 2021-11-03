package br.capitolio.control.output;

import br.capitolio.engine.EngineSettings;
import lombok.Getter;
import org.joml.Math;
import org.joml.Matrix4f;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
public abstract class Window {
    private static final Logger LOGGER = LoggerFactory.getLogger(Window.class);

    public static final float FOV = (float) Math.toRadians(60);
    public static final float Z_NEAR = 0.01f;
    public static final float Z_FAR = 1000f;

    protected long window;

    protected boolean resize, vsync;
    protected final Matrix4f projection;

    public Window() {
        projection = new Matrix4f();
    }

    public abstract void setViewPort(int bottom, int left);

    public abstract void setBgColor(float red, float green, float blue, float alfa);

    protected abstract void doInit();
    public final void init() {
        LOGGER.debug("Initializing");
        doInit();
    }

    public abstract void render();

    public abstract void update();

    protected abstract void doCleanup();
    public final void cleanup() {
        LOGGER.debug("Destroying");
        doCleanup();
    }

    public abstract boolean isKeyPressed(int keycode);

    public abstract boolean shouldClose();

    public abstract void setTitle(String title);

    public abstract Matrix4f updateProjection();

    public abstract Matrix4f updateProjection(Matrix4f matrix, int width, int height);

    public float getAspectRatio() {
        return (float) EngineSettings.getWindowSize().x / EngineSettings.getWindowSize().y;
    }
}
