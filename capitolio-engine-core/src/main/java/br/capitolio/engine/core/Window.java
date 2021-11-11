package br.capitolio.engine.core;

import br.capitolio.engine.EngineSettings;
import br.capitolio.engine.core.logging.Logger;
import br.capitolio.engine.core.logging.LoggerFactory;
import br.capitolio.engine.core.profile.Profiler;
import org.joml.Matrix4f;

public abstract class Window {
    private static final Logger LOGGER = LoggerFactory.getLogger(Window.class);

    protected long window;

    protected boolean resize;
    protected final Matrix4f projection;

    public Window() {
        projection = new Matrix4f();
    }

    public abstract void setViewPort(int bottom, int left);

    public abstract void setBgColor(float red, float green, float blue, float alfa);

    public boolean isResize() {
        return resize;
    }

    protected abstract void doInit();
    public final void init() {
        LOGGER.debug("Initializing");
        doInit();
    }

    public abstract void hide();

    protected abstract void doRender();
    public final void render(){
        Profiler.mark("Window.render()");
        doRender();
        Profiler.release("Window.render()");
    }

    public abstract void update();

    protected abstract void doCleanup();
    public final void cleanup() {
        LOGGER.debug("Destroying");
        doCleanup();
    }

    public abstract void setTitle(String title);

    public abstract Matrix4f getProjectionMatrix();

    public float getAspectRatio() {
        return (float) EngineSettings.getWindowSize().x / EngineSettings.getWindowSize().y;
    }
}
