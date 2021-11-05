package br.capitolio.minecraft;

import br.capitolio.binding.GLBinding;
import br.capitolio.binding.opengl.GLSettings;
import br.capitolio.engine.EngineManager;
import br.capitolio.engine.EngineSettings;
import br.capitolio.framework.cdi.Injector;
import br.capitolio.framework.cdi.context.DefaultContext;
import br.capitolio.minecraft.scene.WorldScene;
import br.capitolio.tools.FileUtils;

public class Application {

    public static void main(String[] args) {
        EngineSettings.setWindowTitle("Minecraft");
        EngineSettings.setWindowSize(1024, 768);

        DefaultContext.load(GLBinding.class);
        GLSettings.setVertexShaderCode(FileUtils.load("shaders/vertex.glsl"));
        GLSettings.setFragmentShaderCode(FileUtils.load("shaders/fragment.glsl"));

        Injector
                .inject(EngineManager.class)
                .start(new WorldScene());
    }

}
