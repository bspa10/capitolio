package br.capitolio.engine.renderer;

import br.capitolio.engine.WindowManager;

public abstract class RenderManager {
    protected WindowManager window;

    public void setWindow(WindowManager window) {
        this.window = window;
    }

    public final void init() {

    }

    public final void render() {

    }

    protected abstract void doClear();
    public final void clear() {
        doClear();
    }

    protected abstract void doCleanup();
    public final void cleanup() {
        doCleanup();
    }
}
