package br.capitolio.engine.binding.opengl;

import br.capitolio.engine.core.render.backend.mesh.Mesh;
import br.capitolio.engine.core.render.backend.Texture;
import br.capitolio.engine.core.render.backend.mesh.Vertex;
import br.capitolio.engine.core.logging.Logger;
import br.capitolio.engine.core.logging.LoggerFactory;
import org.lwjgl.opengl.*;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;

public final class GLMesh extends Mesh {
    private static final Logger LOGGER = LoggerFactory.getLogger(GLMesh.class);

    public static final int INDICES = 0;
    public static final int POSITION = 1;
    public static final int COLOR = 2;
    public static final int TEXTURE = 3;

    private Integer vao;
    private final Integer[] vbos = new Integer[4];
    private Integer tid;
    private Vertex[] vertices;
    private int[] indices;

    private Texture texture;
    private float[] texCoords;

    @Override
    protected void doInit() {
        vao = GL30.glGenVertexArrays();
        LOGGER.debug("Created VAO [%s]", vao);
        GL30.glBindVertexArray(vao);

        // The ordering matters

        vbos[INDICES] = GL15.glGenBuffers();
        final var buffer = BufferUtils.store(indices);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, getIdentity());
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        MemoryUtil.memFree(buffer);

        final var pData = new float[vertices.length * 3];
        for (int i = 0; i < vertices.length; i++) {
            pData[i * 3    ] = vertices[i].getPosition().x;
            pData[i * 3 + 1] = vertices[i].getPosition().y;
            pData[i * 3 + 2] = vertices[i].getPosition().z;
        }
        vbos[POSITION] = storeDate(BufferUtils.store(pData), GLMesh.POSITION, 3);

        final var cData = new float[vertices.length * 3];
        for (int i = 0; i < vertices.length; i++) {
            cData[i * 3    ] = vertices[i].getColor().getRed();
            cData[i * 3 + 1] = vertices[i].getColor().getGreen();
            cData[i * 3 + 2] = vertices[i].getColor().getBlue();
        }
        vbos[COLOR] = storeDate(BufferUtils.store(cData), GLMesh.COLOR, 3);

        if (texture != null) {
            tid = GL11.glGenTextures();
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, tid);
            GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);

//            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
//            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);


            GL11.glTexImage2D(
                    GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA,
                    texture.getWidth(), texture.getHeight(), 0, GL11.GL_RGBA,
                    GL11.GL_UNSIGNED_BYTE, texture.getBuffer()
            );

            GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
            vbos[TEXTURE] = storeDate(BufferUtils.store(texCoords), GLMesh.TEXTURE, 2);
        }
    }

    @Override
    public Integer getIdentity() {
        return vao;
    }

    @Override
    protected void setVertices(Vertex[] vertices) {
        this.vertices = vertices;
    }

    @Override
    protected void setIndices(int[] indices) {
        this.indices = indices;
    }

    @Override
    public void setTexture(Texture texture, float[] coords) {
        this.texture = texture;
        texCoords = coords;
    }

    @Override
    public Integer getTexture() {
        return tid;
    }

    @Override
    public int getVertexCount() {
        return indices.length;
    }

    private int storeDate(FloatBuffer buffer, int attrNo, int size) {
        final var bufferId = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, bufferId);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attrNo, size, GL11.GL_FLOAT, false, 0, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

        MemoryUtil.memFree(buffer);
        return bufferId;
    }

    @Override
    protected void doCleanup() {
        if (vao == null)
            return;

        LOGGER.debug("Destroying VAO [%s]", vao);
        for (var vbo : vbos)
            if (vbo != null)
                GL15.glDeleteBuffers(vbo);

        if (tid != null)
            GL11.glDeleteTextures(tid);

        GL30.glBindVertexArray(0);
        GL30.glDeleteVertexArrays(vao);
        vao = null;
    }
}
