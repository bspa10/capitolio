package br.capitolio.binding.opengl.graphics.mesh;

import br.capitolio.binding.opengl.graphics.GLMesh;
import br.capitolio.binding.opengl.utils.BufferUtils;
import br.capitolio.engine.core.graphics.Vertex;

public class TextureBufferObject extends VertexBufferObject {
    private static final int SIZE = 2;

    public TextureBufferObject(Vertex[] vertices) {
        final var data = new float[vertices.length * SIZE];
        for (int i = 0; i < vertices.length; i++) {
            data[i * SIZE] = vertices[i].getPosition().x;
            data[i * SIZE + 1] = vertices[i].getPosition().y;
        }

        storeDate(BufferUtils.store(data), GLMesh.TEXTURE, SIZE);
    }
}
