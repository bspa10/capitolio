package br.capitolio.engine;

import br.capitolio.engine.core.control.output.Window;
import br.capitolio.engine.logging.Logger;
import br.capitolio.engine.logging.LoggerFactory;
import br.capitolio.engine.render.Renderer;
import br.capitolio.engine.core.scene.Scene;
import br.capitolio.framework.cdi.Injector;

public abstract class EngineManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(EngineManager.class);
    public static final long NANOSECOND= 1000000000L;
    public static final long FRAMERATE = 1000L;

    protected static int fps;
    protected static float frametime = 1.0f / FRAMERATE;
    protected boolean running;

    private Scene scene;
    private final Renderer render = Injector.inject(Renderer.class);
    private final Window window = Injector.inject(Window.class);

    protected abstract void doStart();
    public final void start(Scene scene) {
        if (running)
            return;

        LOGGER.info("Starting the Game Engine");
        if (Injector.getModules().contains("br.capitolio.binding.GLBinding")) {
            LOGGER.info("Using OpenGL");
        }

        render.setWindow(window);
        scene.setWindow(window);
        scene.setRenderer(render);

        window.init();
        render.init();
        scene.init();
        this.scene = scene;

        doStart();
        run();
    }

    public final void stop() {
        if (!running)
            return;

        LOGGER.info("Stopping the Game Engine");
        running = false;
    }

    private void run() {
        running = true;
        var frames = 0;
        var frameCounter = 0L;
        var lastTime = System.nanoTime();
        var unprocessedTime = 0.0;

        while (running) {
            var shouldRender = false;
            var startTime = System.nanoTime();
            var passedTime = startTime - lastTime;
            lastTime = startTime;

            // Frame elapsed time
            unprocessedTime += passedTime / (double) NANOSECOND;
            frameCounter += passedTime;

            processPlayerInput();
            while (unprocessedTime  > frametime) {
                shouldRender = true;
                unprocessedTime -= frametime;

                if (window.shouldClose())
                    stop();

                if (frameCounter >= NANOSECOND){
                    fps = frames;
                    window.setTitle("%s | FPS: %s".formatted(EngineSettings.getWindowTitle(), fps));
                    frames = 0;
                    frameCounter = 0;
                }
            }

            if (shouldRender) {
                update();
                render();
                frames++;
            }
        }

        cleanup();
    }

    private void processPlayerInput() {
        scene.input();
    }

    private void update() {
        scene.update();
        window.update();
    }

    private void render() {
        scene.render();
        window.render();
    }

    protected abstract void doCleanup();
    private void cleanup() {
        LOGGER.debug("Releasing resources");
        scene.cleanup();
        render.cleanup();
        window.cleanup();
        doCleanup();
    }

}
