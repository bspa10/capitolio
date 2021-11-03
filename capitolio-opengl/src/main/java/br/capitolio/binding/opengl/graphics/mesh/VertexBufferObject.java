package br.capitolio.binding.opengl.graphics.mesh;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.FloatBuffer;

public class VertexBufferObject {
    private static final Logger LOGGER = LoggerFactory.getLogger(VertexBufferObject.class);
    private Integer identity;

    protected void setIdentity(int identity) {
        LOGGER.debug("Create VBO [{}]", identity);
        this.identity = identity;
    }

    public final Integer getIdentity() {
        return identity;
    }

    public final void destroy() {
        if (identity == null)
            return;

        LOGGER.debug("Destroying VBO [{}]", identity);
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
