package br.capitolio.engine;

import br.capitolio.control.output.Window;
import br.capitolio.engine.render.Renderer;
import br.capitolio.engine.scene.Scene;
import br.capitolio.framework.cdi.Injector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class EngineManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(EngineManager.class);
    public static final long NANOSECOND= 1000000000L;
    public static final long FRAMERATE = 1000L;

    protected static int fps;
    protected static float frametime = 1.0f / FRAMERATE;
    protected boolean running;

    private final Scene scene = Injector.inject(Scene.class);
    private final Renderer render = Injector.inject(Renderer.class);
    private final Window window = Injector.inject(Window.class);

    protected abstract void doStart();
    public final void start() {
        if (running)
            return;

        LOGGER.info("Starting the Game Engine");
        render.setWindow(window);
        scene.setWindow(window);
        scene.setRenderer(render);

        window.init();
        render.init();
        scene.init();

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

    public void processPlayerInput() {
        scene.input();
    }

    public void update() {
        scene.update();
        window.update();
    }

    public void render() {
        scene.render();
        window.render();
    }

    protected abstract void doCleanup();
    public final void cleanup() {
        LOGGER.debug("Releasing resources");
        scene.cleanup();
        render.cleanup();
        window.cleanup();
        doCleanup();
    }

}
