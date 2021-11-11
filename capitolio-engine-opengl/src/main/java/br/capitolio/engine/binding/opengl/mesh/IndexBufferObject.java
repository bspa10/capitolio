package br.capitolio.engine.binding.opengl.mesh;

import br.capitolio.engine.binding.opengl.BufferUtils;
import org.lwjgl.opengl.GL15;
import org.lwjgl.system.MemoryUtil;

public class IndexBufferObject extends VertexBufferObject {
    private final int length;

    public IndexBufferObject(int[] indices) {
        setIdentity(GL15.glGenBuffers());
        final var buffer = BufferUtils.store(indices);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, getIdentity());
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
//        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
        MemoryUtil.memFree(buffer);

        length = indices.length;
    }

    public int getLength() {
        return length;
    }
}
