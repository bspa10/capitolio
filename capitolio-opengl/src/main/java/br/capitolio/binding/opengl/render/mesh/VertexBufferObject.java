package br.capitolio.binding.opengl.render.mesh;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

import java.nio.FloatBuffer;

public abstract class VertexBufferObject {
    private Integer identity;

    protected void setIdentity(int identity) {
        this.identity = identity;
    }

    public final Integer getIdentity() {
        return identity;
    }

    public final void destroy() {
        if (identity == null)
            return;

        GL15.glDeleteBuffers(identity);
        identity = null;
    }

    protected void storeDate(FloatBuffer buffer, int index, int size) {
        final var bufferId = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, bufferId);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(index, size, GL11.GL_FLOAT, false, 0, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

        setIdentity(bufferId);
    }

}
