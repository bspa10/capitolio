package br.capitolio.engine.core.render.backend;

import br.capitolio.engine.core.Window;
import br.capitolio.engine.core.profile.Profiler;
import br.capitolio.engine.core.render.RenderException;
import br.capitolio.engine.core.render.backend.mesh.Mesh;
import br.capitolio.engine.core.logging.Logger;
import br.capitolio.engine.core.logging.LoggerFactory;
import br.capitolio.engine.gameplay.GameObject;
import br.capitolio.engine.core.scene.Scene;
import br.capitolio.engine.core.render.backend.shader.ShaderProgram;
import br.capitolio.engine.core.render.backend.shader.Uniform;
import br.capitolio.tools.cdi.Injector;

public abstract class Renderer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Renderer.class);

    protected Window window;
    protected final ShaderProgram program = Injector.inject(ShaderProgram.class);

    public final void setWindow(Window window) {
        if (this.window != null)
            throw new RenderException("Window already set");

        this.window = window;
    }

    protected abstract void doInit();
    public final void init() {
        LOGGER.debug("Initializing");
        program.init();

        program.createUniform(Uniform.TEXTURE);
        program.createUniform(Uniform.FRUSTUM);
        program.createUniform(Uniform.WORLD_MATRIX);

        doInit();
    }

    public final void render(Scene scene) {
        if (window.isResize())
            window.setViewPort(0, 0);

        Profiler.mark("Renderer.doClear()");
        doClear();
        Profiler.release("Renderer.doClear()");

        Profiler.mark("Renderer.render(%s)".formatted(scene.getClass().getTypeName()));
        program.bind();
        program.setUniform(Uniform.FRUSTUM, window.getFrustum());
        scene.getChildren().forEach(this::render);
        program.unbind();
        Profiler.release("Renderer.render(%s)".formatted(scene.getClass().getTypeName()));
    }

    protected abstract void doRender(Mesh mesh);
    private void render(GameObject go) {
        if (go.getMesh() != null) {
            Profiler.mark("Renderer.render(%s)".formatted(go.getClass().getTypeName()));
            program.setUniform(Uniform.TEXTURE, 0);
            program.setUniform(Uniform.WORLD_MATRIX, go.getWorldMatrix());
            doRender(go.getMesh());
            Profiler.release("Renderer.render(%s)".formatted(go.getClass().getTypeName()));
        }

        go.getChildren().forEach(this::render);
    }

    protected abstract void doClear();

    protected abstract void doCleanup();
    public final void cleanup() {
        LOGGER.debug("Destroying");
        doCleanup();
    }

}
