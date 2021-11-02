package br.capitolio.engine;

import lombok.Getter;
import org.joml.Math;
import org.joml.Matrix4f;

@Getter
public abstract class WindowManager {
    public static final float FOV = (float) Math.toRadians(60);
    public static final float Z_NEAR = 0.01f;
    public static final float Z_FAR = 1000f;

    protected long window;

    protected boolean resize, vsync;
    protected final Matrix4f projection;

    public WindowManager() {
        projection = new Matrix4f();
    }

    public abstract void setViewPort(int bottom, int left);

    public abstract void setBgColor(float red, float green, float blue, float alfa);

    public abstract void init();

    public abstract void render();

    public abstract void update();

    public abstract void cleanup();

    public abstract boolean isKeyPressed(int keycode);

    public abstract boolean shouldClose();

    public abstract void setTitle(String title);

    public abstract Matrix4f updateProjection();

    public abstract Matrix4f updateProjection(Matrix4f matrix, int width, int height);

    public float getAspectRatio() {
        return (float) EngineSettings.getWindowSize().x / EngineSettings.getWindowSize().y;
    }
}
