package br.capitolio.engine.render;

import br.capitolio.engine.core.Window;
import br.capitolio.engine.core.profile.Profiler;
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
        Profiler.mark("Renderer.render(%s)".formatted(scene.getClass().getTypeName()));
        Profiler.mark("Renderer.doClear()");
        doClear();
        Profiler.release("Renderer.doClear()");
        scene.getChildren().forEach(this::render);
        Profiler.release("Renderer.render(%s)".formatted(scene.getClass().getTypeName()));
    }
    private void render(GameObject go) {
        if (go.getMesh() != null) {
            Profiler.mark("Renderer.render(%s)".formatted(go.getClass().getTypeName()));
            doRender(go.getMesh());
            Profiler.release("Renderer.render(%s)".formatted(go.getClass().getTypeName()));
        }

        go.getChildren().forEach(this::render);
    }
    protected abstract void doClear();
    protected abstract void doRender(Mesh mesh);

    protected abstract void doCleanup();
    public final void cleanup() {
        LOGGER.debug("Destroying");
        doCleanup();
    }
}
