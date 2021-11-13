package br.capitolio.engine.core.render.backend;

import br.capitolio.engine.core.Scene;
import br.capitolio.engine.core.logging.Logger;
import br.capitolio.engine.core.logging.LoggerFactory;
import br.capitolio.engine.core.profile.Profiler;
import br.capitolio.engine.core.render.RendererSettings;
import br.capitolio.engine.core.render.backend.mesh.Mesh;
import br.capitolio.engine.core.render.backend.shader.ShaderProgram;
import br.capitolio.engine.core.render.backend.shader.Uniform;
import br.capitolio.engine.gameplay.Camera;
import br.capitolio.engine.gameplay.GameObject;
import br.capitolio.tools.cdi.Injector;
import org.joml.Matrix4f;

public abstract class Renderer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Renderer.class);
    protected final ShaderProgram program = Injector.inject(ShaderProgram.class);

    protected abstract void doInit();
    public final void init() {
        LOGGER.debug("Initializing");
        program.init();

        program.createUniform(Uniform.TEXTURE);
        program.createUniform(Uniform.FRUSTUM);
        program.createUniform(Uniform.MODEL_VIEW_MATRIX);

        doInit();
    }

    public final void render(Scene scene) {
        Profiler.mark("Renderer.doClear()");
        doClear();
        Profiler.release("Renderer.doClear()");

        Profiler.mark("Renderer.render(%s)".formatted(scene.getClass().getTypeName()));
        program.bind();
        program.setUniform(Uniform.TEXTURE, 0);
        program.setUniform(Uniform.FRUSTUM, RendererSettings.getFrustum());
        scene.getChildren().forEach(go -> render(scene.getCamera(), go));
        program.unbind();
        Profiler.release("Renderer.render(%s)".formatted(scene.getClass().getTypeName()));
    }

    protected abstract void doRender(Mesh mesh);
    private void render(Camera camera, GameObject go) {
        if (go.getMesh() != null) {
            Profiler.mark("Renderer.render(%s)".formatted(go.getClass().getTypeName()));
            program.setUniform(Uniform.MODEL_VIEW_MATRIX, go.getModelViewMatrix(camera.getViewMatrix()));
            doRender(go.getMesh());
            Profiler.release("Renderer.render(%s)".formatted(go.getClass().getTypeName()));
        }

        go.getChildren().forEach(child -> render(camera, child));
    }

    protected abstract void doClear();

    protected abstract void doCleanup();
    public final void cleanup() {
        LOGGER.debug("Destroying");
        doCleanup();
    }

}
