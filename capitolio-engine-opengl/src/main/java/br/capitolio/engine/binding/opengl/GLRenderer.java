package br.capitolio.engine.binding.opengl;

import br.capitolio.engine.render.backend.mesh.Mesh;
import br.capitolio.engine.core.logging.Logger;
import br.capitolio.engine.core.logging.LoggerFactory;
import br.capitolio.engine.render.Renderer;
import org.lwjgl.opengl.*;

public final class GLRenderer extends Renderer {
    private static final Logger LOGGER = LoggerFactory.getLogger(GLRenderer.class);
    private final GLShader shader = new GLShader();
    private GLRenderer(){}

    @Override
    protected void doInit() {
        shader.init();
    }

    @Override
    protected void doRender(Mesh mesh) {
        shader.bind();

        GL30.glBindVertexArray(mesh.getIdentity());
        GL20.glEnableVertexAttribArray(GLMesh.POSITION);
        GL20.glEnableVertexAttribArray(GLMesh.COLOR);
        GL20.glEnableVertexAttribArray(GLMesh.TEXTURE);

        GL11.glDrawElements(GL11.GL_TRIANGLES, ((GLMesh) mesh).getIndex().getLength(), GL11.GL_UNSIGNED_INT, 0);

        GL20.glDisableVertexAttribArray(GLMesh.POSITION);
        GL20.glDisableVertexAttribArray(GLMesh.COLOR);
        GL20.glDisableVertexAttribArray(GLMesh.TEXTURE);
        GL30.glBindVertexArray(0);

        shader.unbind();
    }

    @Override
    protected void doClear() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    }

    @Override
    protected void doCleanup() {
        shader.cleanup();
    }
}
