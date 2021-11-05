package br.capitolio.engine.core.graphics;

import br.capitolio.engine.logging.Logger;
import br.capitolio.engine.logging.LoggerFactory;

public abstract class Mesh {
    private static final Logger LOGGER = LoggerFactory.getLogger(Mesh.class);

    public abstract Integer getIdentity();

    protected abstract void setVertices(Vertex[] vertices);
    protected abstract void setIndices(int[] indices);
    protected abstract void setTexture(Texture texture);

    protected abstract void doInit();
    public void init() {
        LOGGER.debug("Initializing");
        doInit();
    }

    protected abstract void doCleanup();
    public final void cleanup() {
        LOGGER.debug("Destroying");
        doCleanup();
    }
}
