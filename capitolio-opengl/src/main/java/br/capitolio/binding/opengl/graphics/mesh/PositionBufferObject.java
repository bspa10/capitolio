package br.capitolio.binding.opengl.graphics.mesh;

import br.capitolio.binding.opengl.graphics.GLMesh;
import br.capitolio.binding.opengl.utils.BufferUtils;
import br.capitolio.engine.core.graphics.Vertex;

public class PositionBufferObject extends VertexBufferObject {
    private static final int SIZE = 3;

    public PositionBufferObject(Vertex[] vertices) {
        final var data = new float[vertices.length * SIZE];
        for (int i = 0; i < vertices.length; i++) {
            data[i * SIZE] = vertices[i].getPosition().x;
            data[i * SIZE + 1] = vertices[i].getPosition().y;
            data[i * SIZE + 2] = vertices[i].getPosition().z;
        }

        storeDate(BufferUtils.store(data), GLMesh.POSITION, SIZE);
    }
}
