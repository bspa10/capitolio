package br.capitolio.binding.opengl.render;

import br.capitolio.engine.RenderManager;
import br.capitolio.engine.render.ShaderType;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class GLShader {

    private int pid;
    private int vid;
    private int fid;

    public void create() {
        pid = GL20.glCreateProgram();

        vid = process(GL20.GL_VERTEX_SHADER, ShaderType.VERTEX);
        fid = process(GL20.GL_FRAGMENT_SHADER, ShaderType.FRAGMENT);

        // Vincula o buffer do VAO ao script do shared
        GL20.glBindAttribLocation(pid, GLMesh.POSITION, "position");
        GL20.glBindAttribLocation(pid, GLMesh.COLOR, "color");
        GL20.glBindAttribLocation(pid, GLMesh.TEXTURE, "texture");

        GL20.glLinkProgram(pid);
        if (GL20.glGetProgrami(pid, GL20.GL_LINK_STATUS) == GL11.GL_FALSE) {
            return;
        }

        GL20.glValidateProgram(pid);
        if (GL20.glGetProgrami(pid, GL20.GL_VALIDATE_STATUS) == GL11.GL_FALSE) {
            return;
        }

        if (vid != 0) GL20.glDeleteShader(vid);
        if (fid != 0) GL20.glDeleteShader(fid);
    }

    private int process(int kind, ShaderType type) {
        final var identity = GL20.glCreateShader(kind);

        final var name = type.name();
        final var source = RenderManager.getShader(type);

        GL20.glShaderSource(identity, source);
        GL20.glCompileShader(identity);

        if (GL20.glGetShaderi(identity, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
            return 0;
        }

        GL20.glAttachShader(pid, identity);

        return identity;
    }

    public void bind() {
        GL20.glUseProgram(pid);
    }

    public void unbind() {
        GL20.glUseProgram(0);
    }

    public void destroy() {
        GL20.glDetachShader(pid, vid);
        GL20.glDeleteShader(vid);

        GL20.glDetachShader(pid, fid);
        GL20.glDeleteShader(fid);

        GL20.glDeleteProgram(pid);
    }
}


