package br.capitolio.binding.opengl.graphics;

import br.capitolio.binding.opengl.graphics.mesh.*;
import br.capitolio.engine.graphics.Mesh;
import br.capitolio.engine.graphics.Vertex;
import org.lwjgl.opengl.GL30;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public final class GLMesh extends Mesh {
    private static final Logger LOGGER = LoggerFactory.getLogger(GLMesh.class);

    public static final int INDICES = 0;
    public static final int POSITION = 1;
    public static final int COLOR = 2;
    public static final int TEXTURE = 3;

    private Integer identity;
    private VertexBufferObject[] buffers;
    private Vertex[] vertices;
    private int[] indices;

    @Override
    protected void doInit() {
        identity = GL30.glGenVertexArrays();
        LOGGER.debug("Created VAO [{}]", identity);
        GL30.glBindVertexArray(identity);

        // The ordering matters
        buffers = new VertexBufferObject[4];
        buffers[INDICES] = new IndexBufferObject(indices);
        buffers[POSITION] = new PositionBufferObject(vertices);
        buffers[COLOR] = new ColorBufferObject(vertices);
        buffers[TEXTURE] = new TextureBufferObject(vertices);
    }

    @Override
    public Integer getIdentity() {
        return identity;
    }

    @Override
    protected void setVertices(Vertex[] vertices) {
        this.vertices = vertices;
    }

    @Override
    protected void setIndices(int[] indices) {
        this.indices = indices;
    }

    public IndexBufferObject getIndex() {
        return (IndexBufferObject) buffers[INDICES];
    }

    public PositionBufferObject getPosition() {
        return (PositionBufferObject) buffers[POSITION];
    }

    public ColorBufferObject getColor() {
        return (ColorBufferObject) buffers[COLOR];
    }

    public TextureBufferObject getTexture() {
        return (TextureBufferObject) buffers[TEXTURE];
    }

    @Override
    protected void doCleanup() {
        if (identity == null)
            return;

        LOGGER.debug("Destroying VAO [{}]", identity);
        Arrays.stream(buffers).forEach(VertexBufferObject::destroy);
        GL30.glBindVertexArray(0);
        GL30.glDeleteVertexArrays(identity);
        identity = null;
    }
}
