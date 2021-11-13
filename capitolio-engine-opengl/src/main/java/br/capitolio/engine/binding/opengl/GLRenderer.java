package br.capitolio.engine.binding.opengl;

import br.capitolio.engine.core.render.backend.mesh.Mesh;
import br.capitolio.engine.core.render.backend.Renderer;
import org.lwjgl.opengl.*;

public final class GLRenderer extends Renderer {
    private GLRenderer(){}

    @Override
    protected void doInit() {
    }

    @Override
    protected void doRender(Mesh mesh) {
        GL30.glBindVertexArray(mesh.getIdentity());
        GL20.glEnableVertexAttribArray(GLMesh.POSITION);
        GL20.glEnableVertexAttribArray(GLMesh.COLOR);
        GL20.glEnableVertexAttribArray(GLMesh.TEXTURE);

        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, mesh.getTexture());

        GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);

        GL20.glDisableVertexAttribArray(GLMesh.TEXTURE);
        GL20.glDisableVertexAttribArray(GLMesh.COLOR);
        GL20.glDisableVertexAttribArray(GLMesh.POSITION);
        GL30.glBindVertexArray(0);
    }

    @Override
    protected void doClear() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    }

    @Override
    protected void doCleanup() {
        GL20.glDisableVertexAttribArray(GLMesh.POSITION);
        GL20.glDisableVertexAttribArray(GLMesh.COLOR);
        GL20.glDisableVertexAttribArray(GLMesh.TEXTURE);
        GL30.glBindVertexArray(0);
    }
}
