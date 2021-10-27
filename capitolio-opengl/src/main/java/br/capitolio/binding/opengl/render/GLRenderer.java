package br.capitolio.binding.opengl.render;

import br.capitolio.engine.graphics.Mesh;
import br.capitolio.engine.render.Renderer;
import br.capitolio.engine.scene.GameObject;
import br.capitolio.engine.scene.Scene;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

public class GLRenderer implements Renderer {
    private final GLShader shader;

    private GLRenderer() {
        shader = new GLShader();
    }

    public void create() {
        shader.create();
    }

    public void render(Scene object) {
        object
                .getObjects()
                .forEach(this::doRender);
    }

    private void doRender(GameObject object) {
        final var mesh = object.getMesh();
        if (mesh == null)
            return;

        GL30.glBindVertexArray(((GLMesh) mesh).getIdentity());
        GL30.glEnableVertexAttribArray(GLMesh.POSITION);
        GL30.glEnableVertexAttribArray(GLMesh.COLOR);

        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, ((GLMesh) mesh).getIndex().getIdentity());

        shader.bind();
        GL11.glDrawElements(GL11.GL_TRIANGLES, ((GLMesh) mesh).getIndex().getLength(), GL11.GL_UNSIGNED_INT, 0);
        shader.unbind();

        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
        GL30.glDisableVertexAttribArray(GLMesh.POSITION);
        GL30.glDisableVertexAttribArray(GLMesh.COLOR);
        GL30.glBindVertexArray(0);
    }

    public void destroy() {
        shader.destroy();
    }
}
