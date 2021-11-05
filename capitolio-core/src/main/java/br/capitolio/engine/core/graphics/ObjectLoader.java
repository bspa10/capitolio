package br.capitolio.engine.core.graphics;

import br.capitolio.engine.logging.Logger;
import br.capitolio.engine.logging.LoggerFactory;
import br.capitolio.framework.cdi.Injector;
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
