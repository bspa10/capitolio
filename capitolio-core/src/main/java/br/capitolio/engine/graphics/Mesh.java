package br.capitolio.engine.graphics;

import br.capitolio.framework.cdi.Injector;

public interface Mesh {

    static Mesh newMesh(Vertex[] vertices, int[] indices) {
        final var mesh = Injector.inject(Mesh.class);
        mesh.setVertices(vertices);
        mesh.setIndices(indices);

        return mesh;
    }

    void setVertices(Vertex[] vertices);
    void setIndices(int[] indices);

    Integer getIdentity();

    void create();
    void destroy();
}
