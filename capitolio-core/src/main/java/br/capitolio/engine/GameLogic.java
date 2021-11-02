package br.capitolio.engine;

import br.capitolio.engine.renderer.RenderManager;

public abstract class GameLogic {
    protected WindowManager window;
    protected RenderManager render;

    public void setWindow(WindowManager manager) {
        this.window = manager;
    }

    public void setRenderer(RenderManager manager) {
        this.render = manager;
    }

    public abstract void init();
    public abstract void input();
    public abstract void update();
    public abstract void render();
    public abstract void cleanup();

}
