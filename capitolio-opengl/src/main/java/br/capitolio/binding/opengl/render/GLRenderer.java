package br.capitolio.binding.opengl.render;

import br.capitolio.binding.opengl.graphics.GLMesh;
import br.capitolio.engine.graphics.Mesh;
import br.capitolio.engine.render.Renderer;
import org.lwjgl.opengl.*;
import org.lwjgl.system.MemoryUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        GL30.glBindVertexArray(mesh.getIdentity());
        GL20.glEnableVertexAttribArray(GLMesh.POSITION);
        GL20.glEnableVertexAttribArray(GLMesh.COLOR);
        GL20.glEnableVertexAttribArray(GLMesh.TEXTURE);

        shader.bind();
        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0,  ((GLMesh) mesh).getIndex().getLength());

//        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, ((GLMesh) mesh).getIndex().getIdentity());
//        GL11.glDrawElements(GL11.GL_TRIANGLES, ((GLMesh) mesh).getIndex().getLength(), GL11.GL_UNSIGNED_INT, MemoryUtil.NULL);
//        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);

        shader.unbind();

        GL20.glDisableVertexAttribArray(GLMesh.POSITION);
        GL20.glDisableVertexAttribArray(GLMesh.COLOR);
        GL20.glDisableVertexAttribArray(GLMesh.TEXTURE);
        GL30.glBindVertexArray(0);
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
