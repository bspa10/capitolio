package br.capitolio.engine.sdk.noop;

import br.capitolio.engine.core.render.backend.Renderer;
import br.capitolio.engine.core.render.backend.mesh.Mesh;

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
