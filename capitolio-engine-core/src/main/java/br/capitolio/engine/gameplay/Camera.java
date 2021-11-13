package br.capitolio.engine.gameplay;

import br.capitolio.engine.EngineException;
import br.capitolio.engine.core.Window;

public abstract class Camera {
    protected Window window;

    public final void setWindow(Window window) {
        if (window != null)
            throw new EngineException("Window already set");

        this.window = window;
    }

    protected abstract void doInit();
    public final void init() {
        doInit();
    }
}
