package br.capitolio.binding;

import br.capitolio.binding.opengl.*;
import br.capitolio.binding.opengl.control.output.GLWindow;
import br.capitolio.binding.opengl.graphics.GLMesh;
import br.capitolio.binding.opengl.render.GLRenderer;
import br.capitolio.engine.EngineManager;
import br.capitolio.engine.core.graphics.Mesh;
import br.capitolio.engine.render.Renderer;
import br.capitolio.engine.core.control.output.Window;
import br.capitolio.framework.cdi.annotation.Module;
import br.capitolio.framework.cdi.annotation.Provider;
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
