package br.capitolio.engine;

import br.capitolio.engine.device.output.Window;
import br.capitolio.engine.render.Renderer;
import br.capitolio.engine.scene.Scene;
import br.capitolio.engine.system.Binding;
import br.capitolio.framework.cdi.Injector;

public class Game {
    private Scene scene;
    private Window window;
    private final Renderer renderer = Injector.inject(Renderer.class);
    private final Binding binding = Injector.inject(Binding.class);

    public Game() {
        binding.initialize();
    }

    public void run() {
        window = Injector.inject(Window.class);

        renderer.create();

        window.show();
        try {
            loop();
        } catch (Exception ex){
            ex.printStackTrace();
        }
        window.hide();

        scene.destroy();
        renderer.destroy();
        window.destroy();
        binding.destroy();
    }

    private void loop() throws Exception{
        var frames = 0;
        var time = System.currentTimeMillis();

        scene = Settings.getStartScene().getConstructor().newInstance();
        scene.initialize();

        SceneManager.setCurrent(Settings.getStartScene());
        while (! GameState.shouldStop() ) {
            frames++;

            if (System.currentTimeMillis() > time) {
                window.setTitle(Settings.getWindowTitle().split("FPS")[0] + "FPS: " + frames);
                time = System.currentTimeMillis() + 1000;
                frames = 0;
            }

            // --------------------------------------------------
            // Clear the screen
            // --------------------------------------------------
            window.clear(scene.getBgColor());
            // --------------------------------------------------
            // Update the Scene
            // --------------------------------------------------
            window.update();
            scene.update();
            // --------------------------------------------------
            // Render the Scene
            // --------------------------------------------------
            renderer.render(scene);
            window.render();
            // --------------------------------------------------
            // Check And Change the current scene
            // --------------------------------------------------
            final var klass = SceneManager.getCurrent().orElse(null);
            if (scene.getClass().equals(klass))
                continue;

            scene.destroy();
            scene = klass.getConstructor().newInstance();
            scene.initialize();
        }
    }
}
