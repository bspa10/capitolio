package br.capitolio.engine.core.render.backend.shader;

import br.capitolio.engine.core.logging.Logger;
import br.capitolio.engine.core.logging.LoggerFactory;
import org.joml.Matrix4f;

public abstract class ShaderProgram {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShaderProgram.class);

    protected abstract void doInit();
    public final void init() {
        doInit();
    }

    protected abstract void doBind();
    public final void bind() {
        doBind();
    }

    public abstract void createUniform(Uniform uniform);
    public abstract void setUniform(Uniform uniform, Matrix4f value);
    public abstract void setUniform(Uniform uniform, int value);

    protected abstract void doUnbind();
    public final void unbind() {
        doUnbind();
    }

    protected abstract void doCleanup();
    public final void cleanup() {
        LOGGER.debug("Destroying");
        unbind();
        doCleanup();
    }
}
