package br.capitolio.engine.binding;

import br.capitolio.engine.binding.opengl.*;
import br.capitolio.engine.Engine;
import br.capitolio.engine.core.render.backend.mesh.Mesh;
import br.capitolio.engine.core.render.backend.Renderer;
import br.capitolio.engine.core.Window;
import br.capitolio.engine.core.render.backend.shader.ShaderProgram;
import br.capitolio.tools.cdi.annotation.Module;
import br.capitolio.tools.cdi.annotation.Provider;
import br.capitolio.tools.reflection.Reflections;

@Module
public final class GLBinding {

    @Provider(overridable = false)
    public Window window() {
        return Reflections.Classes.newInstance(GLWindow.class);
    }

    @Provider(overridable = false)
    public Engine engine() {
        return Reflections.Classes.newInstance(GLEngine.class);
    }

    @Provider(overridable = false)
    public Renderer render() {
        return Reflections.Classes.newInstance(GLRenderer.class);
    }

    @Provider(overridable = false)
    public Mesh mesh() {
        return Reflections.Classes.newInstance(GLMesh.class);
    }

    @Provider(overridable = false)
    public ShaderProgram shaderProgram() {
        return Reflections.Classes.newInstance(GLShaderProgram.class);
    }
}
