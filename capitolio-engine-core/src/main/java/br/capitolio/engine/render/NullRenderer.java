package br.capitolio.engine.render;

import br.capitolio.engine.render.backend.mesh.Mesh;
import br.capitolio.engine.render.backend.shader.Uniform;
import org.joml.Matrix4f;

public final class NullRenderer extends Renderer {

    @Override
    protected void doInit() {

    }

    @Override
    protected void doRender(Mesh mesh) {

    }

    @Override
    protected void doClear() {

    }

    @Override
    protected void doCleanup() {

    }
}
