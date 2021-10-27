package br.capitolio.minecraft;

import br.capitolio.binding.OpenglModule;
import br.capitolio.engine.RenderManager;
import br.capitolio.engine.Settings;
import br.capitolio.engine.Game;
import br.capitolio.engine.system.Capitolio;
import br.capitolio.engine.render.ShaderType;
import br.capitolio.minecraft.scene.LoginScene;
import br.capitolio.tools.FileUtils;

import java.util.concurrent.Executors;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        Capitolio.getInstance().load(OpenglModule.class);

        Settings.setWindowTitle("Minecraft");
        Settings.setWindowSize(1024, 768);
        Settings.setStartScene(LoginScene.class);

        RenderManager.setShader(ShaderType.VERTEX, FileUtils.load("shaders/vertex.glsl"));
        RenderManager.setShader(ShaderType.FRAGMENT, FileUtils.load("shaders/fragment.glsl"));

        final var game = new Game();
        final var executor = Executors.newSingleThreadExecutor();
        executor.execute(game::run);
        executor.shutdown();

        while (! executor.isTerminated()) {
            Thread.sleep(250L);
        }
    }

}
