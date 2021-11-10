package br.capitolio.engine;

import br.capitolio.engine.core.control.input.InputHandler;
import br.capitolio.engine.core.time.Clock;
import br.capitolio.engine.core.control.output.Window;
import br.capitolio.engine.core.logging.Logger;
import br.capitolio.engine.core.logging.LoggerFactory;
import br.capitolio.engine.render.Renderer;
import br.capitolio.engine.core.scene.Scene;
import br.capitolio.tools.cdi.Injector;

public abstract class Engine {
    private static final Logger LOGGER = LoggerFactory.getLogger(Engine.class);
    public static final long FRAMERATE = 240L;

    protected int fps;
    protected float frametime = 1.0f / FRAMERATE;

    private Scene scene;
    private final Renderer render = Injector.inject(Renderer.class);
    private final Window window = Injector.inject(Window.class);

    protected abstract void doStart();
    public final void start(Scene scene) {
        if (GlobalState.isRunning()) {
            LOGGER.warn("Trying to start an ingoing game");
            return;
        }

        LOGGER.info("Starting the Game Engine");
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
        if (!GlobalState.isRunning())
            return;

        LOGGER.info("Stopping the Game Engine");
        GlobalState.setStopped();
    }

    private void run() {
        GlobalState.setRunning();
        var frames = 0;
        var frameCounter = 0L;
        var unprocessedTime = 0.0;

        final var clock = new Clock();
        while (GlobalState.isRunning()) {
            final var passedTime = clock.delta();
            clock.mark();

            // Frame elapsed time
            unprocessedTime += passedTime / (double) Clock.NANOSECOND;
            frameCounter += passedTime;

            // Processa as entradas do usuário
            processInput();

            var shouldRender = false;
            while (unprocessedTime  > frametime) {
                shouldRender = true;
                unprocessedTime -= frametime;

                if (GlobalState.isShouldStop())
                    stop();

                if (frameCounter >= Clock.NANOSECOND) {
                    fps = frames;
                    window.setTitle("%s | FPS: %s".formatted(EngineSettings.getWindowTitle(), fps));
                    frames = 0;
                    frameCounter = 0;
                }
            }

            if (shouldRender) {
                // Atualiza a representação do mundo
                update();

                // Desenha o mundo para o usuário
                render();
                frames++;
            }
        }

        window.hide();
        cleanup();
    }

    private void processInput() {
        final var strokes = InputHandler.getActivatedKeys();

        InputHandler
                .getBoundCombinations()
                .forEach(keyBinding -> {
                    if (strokes == keyBinding.getCombination().getActivationCode()) {
                        final var action = keyBinding.getAction();

                        action.setScene(scene);
                        action.process();
                        action.setScene(null);
                    }
                });
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
