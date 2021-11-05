package br.capitolio.minecraft;

import br.capitolio.binding.opengl.GLSettings;
import br.capitolio.engine.EngineManager;
import br.capitolio.engine.EngineSettings;
import br.capitolio.engine.platform.linux.PlatformLinuxModule;
import br.capitolio.framework.cdi.Injector;
import br.capitolio.framework.cdi.context.DefaultContext;
import br.capitolio.binding.GLBinding;
import br.capitolio.tools.FileUtils;

public class Application {

    public static void main(String[] args) {
        DefaultContext.load(GLBinding.class);
        DefaultContext.load(GameModule.class);


        final var os = System.getProperty("os.name");
        if (os.contains("nix") || os.contains("nux")) {
            DefaultContext.load(PlatformLinuxModule.class);
        }

        EngineSettings.setWindowTitle("Minecraft");
        EngineSettings.setWindowSize(800, 600);
        GLSettings.setVertexShaderCode(FileUtils.load("shaders/vertex.glsl"));
        GLSettings.setFragmentShaderCode(FileUtils.load("shaders/fragment.glsl"));

        Injector.inject(EngineManager.class).start();
    }

}
