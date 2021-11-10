package br.capitolio.engine.binding;

import br.capitolio.binding.opengl.*;
import br.capitolio.engine.binding.opengl.GLEngine;
import br.capitolio.engine.binding.opengl.control.output.GLWindow;
import br.capitolio.engine.binding.opengl.graphics.GLMesh;
import br.capitolio.engine.binding.opengl.render.GLRenderer;
import br.capitolio.engine.EngineManager;
import br.capitolio.engine.render.backend.mesh.Mesh;
import br.capitolio.engine.render.Renderer;
import br.capitolio.engine.core.control.output.Window;
import br.capitolio.tools.cdi.annotation.Module;
import br.capitolio.tools.cdi.annotation.Provider;
import br.capitolio.tools.reflection.Reflections;

@Module
public class GLBinding {

    @Provider(overridable = false)
    public Window windowManager() {
        return Reflections.Classes.newInstance(GLWindow.class);
    }

    @Provider(overridable = false)
    public EngineManager engineManager() {
        return Reflections.Classes.newInstance(GLEngine.class);
    }

    @Provider(overridable = false)
    public Renderer renderManager() {
        return Reflections.Classes.newInstance(GLRenderer.class);
    }

    @Provider(overridable = false)
    public Mesh mesh() {
        return Reflections.Classes.newInstance(GLMesh.class);
    }
}
