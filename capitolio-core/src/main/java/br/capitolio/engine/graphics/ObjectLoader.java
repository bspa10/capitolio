package br.capitolio.engine.graphics;

import br.capitolio.framework.cdi.Injector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

}
