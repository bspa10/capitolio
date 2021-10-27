package br.capitolio.binding;

import br.capitolio.binding.opengl.device.output.GLWindow;
import br.capitolio.binding.opengl.render.GLMesh;
import br.capitolio.binding.opengl.render.GLRenderer;
import br.capitolio.binding.system.GLBinding;
import br.capitolio.engine.device.output.Window;
import br.capitolio.engine.graphics.Mesh;
import br.capitolio.engine.render.Renderer;
import br.capitolio.engine.system.Binding;
import br.capitolio.engine.system.CapitolioBinding;
import br.capitolio.framework.cdi.annotation.Module;
import br.capitolio.framework.cdi.annotation.Provider;
import br.capitolio.tools.reflection.Reflections;

@Module
public final class OpenglModule extends CapitolioBinding {

    @Provider(overridable = false)
    public Window window() {
        return Reflections.Classes.newInstance(GLWindow.class);
    }

    @Provider(overridable = false)
    public Binding binding() {
        return Reflections.Classes.newInstance(GLBinding.class);
    }

    @Provider(overridable = false)
    public Renderer renderer() {
        return Reflections.Classes.newInstance(GLRenderer.class);
    }

    @Provider(overridable = false)
    public Mesh mesh() {
        return Reflections.Classes.newInstance(GLMesh.class);
    }
}
