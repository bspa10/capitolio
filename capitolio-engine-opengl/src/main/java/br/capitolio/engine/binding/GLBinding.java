package br.capitolio.engine.binding;

import br.capitolio.engine.binding.opengl.GLEngine;
import br.capitolio.engine.binding.opengl.GLWindow;
import br.capitolio.engine.binding.opengl.GLMesh;
import br.capitolio.engine.binding.opengl.GLRenderer;
import br.capitolio.engine.Engine;
import br.capitolio.engine.render.backend.mesh.Mesh;
import br.capitolio.engine.render.Renderer;
import br.capitolio.engine.core.Window;
import br.capitolio.tools.cdi.annotation.Module;
import br.capitolio.tools.cdi.annotation.Provider;
import br.capitolio.tools.reflection.Reflections;

@Module
public class GLBinding {

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
}
