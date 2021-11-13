package br.capitolio.engine.core;

import br.capitolio.engine.EngineSettings;
import br.capitolio.engine.core.logging.Logger;
import br.capitolio.engine.core.logging.LoggerFactory;
import br.capitolio.engine.core.profile.Profiler;

public abstract class Window {
    private static final Logger LOGGER = LoggerFactory.getLogger(Window.class);

    protected long window;

    public abstract void setBgColor(float red, float green, float blue, float alfa);

    protected abstract void doInit();
    public final void init() {
        LOGGER.debug("Initializing");
        doInit();
    }

    public abstract void hide();

    protected abstract void doRefresh();
    public final void refresh(){
        Profiler.mark("Window.render()");
        doRefresh();
        Profiler.release("Window.render()");
    }

    public abstract void update();

    protected abstract void doCleanup();
    public final void cleanup() {
        LOGGER.debug("Destroying");
        doCleanup();
    }

    public abstract void setTitle(String title);

    public float getAspectRatio() {
        return (float) EngineSettings.getWindowSize().x / EngineSettings.getWindowSize().y;
    }
}
