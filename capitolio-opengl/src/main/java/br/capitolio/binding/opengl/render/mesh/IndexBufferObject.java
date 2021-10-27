package br.capitolio.binding.opengl.render.mesh;

import org.lwjgl.opengl.GL15;
import org.lwjgl.system.MemoryUtil;

public class IndexBufferObject extends VertexBufferObject {
    private final int length;

    public IndexBufferObject(int[] indices) {
        length = indices.length;

        final var buffer = MemoryUtil.memAllocInt(indices.length);
        buffer.put(indices);
        buffer.flip();

        setIdentity(GL15.glGenBuffers());
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, getIdentity());
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    public int getLength() {
        return length;
    }

}
