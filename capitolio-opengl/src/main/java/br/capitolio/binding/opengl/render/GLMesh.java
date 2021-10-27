package br.capitolio.binding.opengl.render;

import br.capitolio.binding.opengl.GLException;
import br.capitolio.binding.opengl.render.mesh.*;
import br.capitolio.engine.graphics.Mesh;
import br.capitolio.engine.graphics.Vertex;
import org.lwjgl.opengl.GL30;

import java.util.Arrays;

public final class GLMesh implements Mesh {
    public static final int INDICES = 0;
    public static final int POSITION = 1;
    public static final int COLOR = 2;
    public static final int TEXTURE = 3;

    private Integer identity;
    private VertexBufferObject[] buffers;

    private Vertex[] vertices;
    private int[] indices;

    private GLMesh() {}

    @Override
    public void setVertices(Vertex[] vertices) {
        if (this.vertices != null)
            throw new GLException("Vertices already defined");

        this.vertices = vertices;
    }

    @Override
    public void setIndices(int[] indices) {
        if (this.indices != null)
            throw new GLException("Indices already defined");

        this.indices = indices;
    }

    @Override
    public void create() {
        identity = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(identity);

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
    public void destroy() {
        if (identity == null)
            return;

        Arrays.stream(buffers).forEach(VertexBufferObject::destroy);
        GL30.glDeleteVertexArrays(identity);
        identity = null;
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
}
