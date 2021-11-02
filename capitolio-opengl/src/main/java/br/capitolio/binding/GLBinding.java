package br.capitolio.binding;

import br.capitolio.binding.opengl.GLEngineManager;
import br.capitolio.binding.opengl.GLRenderManager;
import br.capitolio.binding.opengl.GLWindowManager;
import br.capitolio.engine.EngineManager;
import br.capitolio.engine.renderer.RenderManager;
import br.capitolio.engine.WindowManager;
import br.capitolio.framework.cdi.annotation.Module;
import br.capitolio.framework.cdi.annotation.Provider;

@Module
public class GLBinding {

    @Provider(overridable = false)
    public WindowManager windowManager() {
        return new GLWindowManager();
    }

    @Provider(overridable = false)
    public EngineManager engineManager() {
        return new GLEngineManager();
    }

    @Provider(overridable = false)
    public RenderManager renderManager() {
        return new GLRenderManager();
    }
}
