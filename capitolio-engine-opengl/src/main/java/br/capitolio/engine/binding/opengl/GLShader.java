package br.capitolio.engine.binding.opengl;

import br.capitolio.engine.core.logging.Logger;
import br.capitolio.engine.core.logging.LoggerFactory;
import br.capitolio.engine.render.RenderException;
import br.capitolio.engine.render.backend.shader.ShaderProgram;
import br.capitolio.engine.render.backend.shader.Uniform;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import java.util.HashMap;
import java.util.Map;

public final class GLShader extends ShaderProgram  {
    private static final Logger LOGGER = LoggerFactory.getLogger(GLShader.class);
    private int pid;
    private int vid;
    private int fid;
    private final Map<Uniform, Integer> uniforms = new HashMap<>();

    private GLShader(){}

    @Override
    protected void doInit() {
        LOGGER.debug("Initializing ShaderProgram");
        pid = GL20.glCreateProgram();

        LOGGER.trace("Loading Vertex ShaderProgram");
        vid = create(GL20.GL_VERTEX_SHADER, GLSettings.getVertexShaderCode());

        LOGGER.trace("Loading Fragment ShaderProgram");
        fid = create(GL20.GL_FRAGMENT_SHADER, GLSettings.getFragmentShaderCode());

        bindAttributes();
        linkProgram();
    }

    private int create(int kind, String code) {
        final var identity = GL20.glCreateShader(kind);
        if (identity == MemoryUtil.NULL)
            throw new GLException("Error creating shader: %s".formatted(kind));

        GL20.glShaderSource(identity, code);
        GL20.glCompileShader(identity);

        if (GL20.glGetShaderi(identity, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE)
            throw new GLException("Error creating shader: %s\nreason: %s".formatted(kind, GL20.glGetShaderInfoLog(identity, 1024)));

        GL20.glAttachShader(pid, identity);

        return identity;
    }

    private void bindAttributes() {
        LOGGER.trace("Binding ShaderProgram Attributes");
        GL20.glBindAttribLocation(pid, GLMesh.POSITION, "position");
        GL20.glBindAttribLocation(pid, GLMesh.COLOR, "color");
        GL20.glBindAttribLocation(pid, GLMesh.TEXTURE, "texture");
    }

    private void linkProgram() {
        LOGGER.trace("Linking ShaderProgram Program");
        GL20.glLinkProgram(pid);
        if (GL20.glGetProgrami(pid, GL20.GL_LINK_STATUS) == GL11.GL_FALSE)
            throw new GLException("Error linking shader code: %s".formatted(GL20.glGetProgramInfoLog(pid, 1024)));

        if (vid != 0) {
            LOGGER.trace("Detaching Vertex ShaderProgram");
            GL20.glDetachShader(pid, vid);
        }

        if (fid != 0) {
            LOGGER.trace("Detaching Fragment ShaderProgram");
            GL20.glDetachShader(pid, fid);
        }

        GL20.glValidateProgram(pid);
        if (GL20.glGetProgrami(pid, GL20.GL_VALIDATE_STATUS) == GL11.GL_FALSE)
            throw new GLException("Unable validating shader code: %s".formatted(GL20.glGetProgramInfoLog(pid, 1024)));
    }

    @Override
    protected void doBind() {
        GL20.glUseProgram(pid);
    }

    @Override
    protected void doUnbind() {
        GL20.glUseProgram(0);
    }

    @Override
    public void createUniform(Uniform uniform) {
        final var uniformId = GL20.glGetUniformLocation(pid, uniform.getName());
        if (uniformId < 0)
            throw new RenderException("Uniform not found");

        uniforms.put(uniform, uniformId);
    }

    @Override
    public void setUniform(Uniform uniform, Matrix4f value) {
        try (final var stack = MemoryStack.stackPush()) {
            final var buffer = stack.mallocFloat(16);
            value.get(buffer);

            GL20.glUniformMatrix4fv(uniforms.get(uniform), false, buffer);
        }
    }

    @Override
    protected void doCleanup() {
        unbind();

        if (pid != 0)
            GL20.glDeleteProgram(pid);
    }
}
