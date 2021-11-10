package br.capitolio.engine.binding.opengl.mesh;

import br.capitolio.engine.binding.opengl.GLMesh;
import br.capitolio.engine.binding.opengl.BufferUtils;
import br.capitolio.engine.render.backend.mesh.Vertex;

public class ColorBufferObject extends VertexBufferObject {
    private static final int SIZE = 3;

    public ColorBufferObject(Vertex[] vertices) {
        final var data = new float[vertices.length * SIZE];
        for (int i = 0; i < vertices.length; i++) {
            data[i * SIZE] = vertices[i].getColor().getRed();
            data[i * SIZE + 1] = vertices[i].getColor().getGreen();
            data[i * SIZE + 2] = vertices[i].getColor().getBlue();
        }

        storeDate(BufferUtils.store(data), GLMesh.COLOR, SIZE);
    }
}
