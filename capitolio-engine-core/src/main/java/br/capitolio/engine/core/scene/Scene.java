package br.capitolio.engine.core.scene;

import br.capitolio.engine.EngineException;
import br.capitolio.engine.core.Window;
import br.capitolio.engine.core.profile.Profiler;
import br.capitolio.engine.gameplay.GameObject;
import br.capitolio.engine.core.logging.Logger;
import br.capitolio.engine.core.logging.LoggerFactory;
import br.capitolio.engine.core.render.backend.Renderer;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene {
    private static final Logger LOGGER = LoggerFactory.getLogger(Scene.class);
    private Window window;
    protected Renderer renderer;

    private final List<GameObject> children = new ArrayList<>();
    public void addObject(GameObject go) {
        children.add(go);
    }
    public List<GameObject> getChildren() {
        return children;
    }

    public final void setWindow(Window window) {
        if (this.window != null)
            throw new EngineException("Window already set");

        this.window = window;
    }

    public final void setRenderer(Renderer renderer) {
        if (this.renderer != null)
            throw new EngineException("Renderer already set");

        this.renderer = renderer;
    }

    protected abstract void doInit();
    public void init() {
        LOGGER.debug("Initializing");
        doInit();
    }

    protected abstract void doUpdate();
    public final void update() {
        doUpdate();

        children.forEach(GameObject::update);
    }

    public final void render() {
        Profiler.mark("Scene.render()");
        renderer.render(this);
        Profiler.release("Scene.render()");
    }

    protected abstract void doCleanup();
    public void cleanup() {
        LOGGER.debug("Destroying");
        doCleanup();
    }
}
