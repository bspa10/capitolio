package br.capitolio.engine.binding.opengl.mesh;

import br.capitolio.engine.binding.opengl.GLMesh;
import br.capitolio.engine.binding.opengl.BufferUtils;
import br.capitolio.engine.render.backend.mesh.Vertex;

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
