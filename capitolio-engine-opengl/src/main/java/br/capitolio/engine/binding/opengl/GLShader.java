package br.capitolio.engine.binding.opengl;

import br.capitolio.engine.core.logging.Logger;
import br.capitolio.engine.core.logging.LoggerFactory;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryUtil;

final class GLShader {
    private static final Logger LOGGER = LoggerFactory.getLogger(GLShader.class);
    private int pid;
    private int vid;
    private int fid;

    public void init() {
        LOGGER.debug("Initializing Shader");
        pid = GL20.glCreateProgram();

        LOGGER.trace("Loading Vertex Shader");
        vid = create(GL20.GL_VERTEX_SHADER, GLSettings.getVertexShaderCode());

        LOGGER.trace("Loading Fragment Shader");
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
        LOGGER.trace("Binding Shader Attributes");
        GL20.glBindAttribLocation(pid, GLMesh.POSITION, "position");
        GL20.glBindAttribLocation(pid, GLMesh.COLOR, "color");
        GL20.glBindAttribLocation(pid, GLMesh.TEXTURE, "texture");
    }

    private void linkProgram() {
        LOGGER.trace("Linking Shader Program");
        GL20.glLinkProgram(pid);
        if (GL20.glGetProgrami(pid, GL20.GL_LINK_STATUS) == GL11.GL_FALSE)
            throw new GLException("Error linking shader code: %s".formatted(GL20.glGetProgramInfoLog(pid, 1024)));

        if (vid != 0) {
            LOGGER.trace("Detaching Vertex Shader");
            GL20.glDetachShader(pid, vid);
        }

        if (fid != 0) {
            LOGGER.trace("Detaching Fragment Shader");
            GL20.glDetachShader(pid, fid);
        }

        GL20.glValidateProgram(pid);
        if (GL20.glGetProgrami(pid, GL20.GL_VALIDATE_STATUS) == GL11.GL_FALSE)
            throw new GLException("Unable validating shader code: %s".formatted(GL20.glGetProgramInfoLog(pid, 1024)));
    }

    public void bind() {
        GL20.glUseProgram(pid);
    }

    public void unbind() {
        GL20.glUseProgram(0);
    }

    public void cleanup() {
        unbind();

        if (pid != 0)
            GL20.glDeleteProgram(pid);
    }
}
