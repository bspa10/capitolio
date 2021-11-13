package br.capitolio.engine.core.render.backend.mesh;

import br.capitolio.engine.core.logging.Logger;
import br.capitolio.engine.core.logging.LoggerFactory;
import br.capitolio.engine.core.render.backend.Texture;
import br.capitolio.tools.cdi.Injector;

public abstract class ObjectLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger(ObjectLoader.class);

    private ObjectLoader(){}

    public static Mesh load(Vertex[] vertices, int[] indices) {
        LOGGER.trace("Loading Mesh");
        final var mesh = Injector.inject(Mesh.class);
        mesh.setVertices(vertices);
        mesh.setIndices(indices);
        mesh.init();

        return mesh;
    }

    public static Mesh load(Vertex[] vertices, int[] indices, Texture texture, float[] textureCoords) {
        LOGGER.trace("Loading Mesh");
        final var mesh = Injector.inject(Mesh.class);
        mesh.setVertices(vertices);
        mesh.setIndices(indices);
        mesh.setTexture(texture, textureCoords);
        mesh.init();

        return mesh;
    }

}
