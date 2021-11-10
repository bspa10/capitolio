package br.capitolio.engine.render;

import br.capitolio.engine.core.control.output.Window;
import br.capitolio.engine.render.backend.mesh.Mesh;
import br.capitolio.engine.core.logging.Logger;
import br.capitolio.engine.core.logging.LoggerFactory;
import br.capitolio.engine.gameplay.GameObject;
import br.capitolio.engine.core.scene.Scene;

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
