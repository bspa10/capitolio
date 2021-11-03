package br.capitolio.engine.scene;

import br.capitolio.control.output.Window;
import br.capitolio.engine.render.Renderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene {
    private static final Logger LOGGER = LoggerFactory.getLogger(Scene.class);
    protected Window window;
    protected Renderer render;

    private final List<GameObject> children = new ArrayList<>();
    public void addObject(GameObject go) {
        children.add(go);
    }
    public List<GameObject> getChildren() {
        return children;
    }

    public final void setWindow(Window manager) {
        this.window = manager;
    }

    public final void setRenderer(Renderer manager) {
        this.render = manager;
    }

    protected abstract void doInit();
    public void init() {
        LOGGER.debug("Initializing");
        doInit();
    }
    public abstract void input();
    public abstract void update();
    public abstract void render();

    public void cleanup() {
        LOGGER.debug("Destroying");
        doCleanup();
    }
    protected abstract void doCleanup();

}
