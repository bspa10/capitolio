package br.capitolio.binding.opengl.render.mesh;

import br.capitolio.binding.opengl.render.GLMesh;
import br.capitolio.engine.graphics.Vertex;
import org.lwjgl.system.MemoryUtil;

public class ColorBufferObject extends VertexBufferObject {
    private static final int SIZE = 3;

    public ColorBufferObject(Vertex[] vertices) {
        final var buffer = MemoryUtil.memAllocFloat(vertices.length * SIZE);
        final var data = new float[vertices.length * SIZE];

        for (int i = 0; i < vertices.length; i++) {
            data[i * SIZE] = vertices[i].getColor().getRed();
            data[i * SIZE + 1] = vertices[i].getColor().getGreen();
            data[i * SIZE + 2] = vertices[i].getColor().getBlue();
        }

        buffer.put(data);
        buffer.flip();

        storeDate(buffer, GLMesh.COLOR, SIZE);
    }
}
