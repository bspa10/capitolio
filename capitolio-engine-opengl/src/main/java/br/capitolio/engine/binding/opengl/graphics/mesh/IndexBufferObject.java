package br.capitolio.engine.binding.opengl.graphics.mesh;

import br.capitolio.engine.binding.opengl.utils.BufferUtils;
import org.lwjgl.opengl.GL15;

public class IndexBufferObject extends VertexBufferObject {
    private final int length;

    public IndexBufferObject(int[] indices) {
        setIdentity(GL15.glGenBuffers());
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, getIdentity());
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, BufferUtils.store(indices), GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);

        length = indices.length;
    }

    public int getLength() {
        return length;
    }
}
