package br.capitolio.engine.render;

import br.capitolio.control.output.Window;
import br.capitolio.engine.graphics.Mesh;
import br.capitolio.engine.scene.GameObject;
import br.capitolio.engine.scene.Scene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Renderer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Renderer.class);
    protected Window window;

    public void setWindow(Window window) {
        this.window = window;
    }

    protected abstract void doInit();
    public final void init() {
        LOGGER.debug("Initializing");
        doInit();
    }

    public final void render(Scene scene) {
        clear();
        scene.getChildren().forEach(this::render);
    }
    private void render(GameObject go) {
        if (go.getMesh() != null)
            doRender(go.getMesh());

        go.getChildren().forEach(this::render);
    }
    protected abstract void doRender(Mesh mesh);

    protected abstract void doClear();
    public final void clear() {
        doClear();
    }

    protected abstract void doCleanup();
    public final void cleanup() {
        LOGGER.debug("Destroying");
        doCleanup();
    }
}
