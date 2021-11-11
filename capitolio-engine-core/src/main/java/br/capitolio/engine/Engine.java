package br.capitolio.engine;

import br.capitolio.engine.core.input.InputHandler;
import br.capitolio.engine.core.profile.Profiler;
import br.capitolio.engine.core.time.Clock;
import br.capitolio.engine.core.Window;
import br.capitolio.engine.core.logging.Logger;
import br.capitolio.engine.core.logging.LoggerFactory;
import br.capitolio.engine.render.Renderer;
import br.capitolio.engine.core.scene.Scene;
import br.capitolio.tools.cdi.Injector;

public abstract class Engine {
    private static final Logger LOGGER = LoggerFactory.getLogger(Engine.class);

    protected double frametime = 1.0 / EngineSettings.getFramerate();

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
        var lastTime = System.nanoTime();
        var unprocessedTime = 0.0;

        while (GlobalState.isRunning()) {
            Profiler.mark("Game Loop");
            // Marca o instante de inicio do loop
            final var startTime = System.nanoTime();

            // Identifica o tempo decorrido entre o último loop e este
            final var passedTime = startTime - lastTime;

            // Armazena o tempo de início do loop
            lastTime = startTime;

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
                    window.setTitle("%s | FPS: %s".formatted(EngineSettings.getWindowTitle(), frames));
                    frames = 0;
                    frameCounter = 0;
                    Profiler.setFrame(frames);
                }
            }

            if (shouldRender) {
                update();
                render();

                frames++;
                Profiler.setFrame(frames);
            }

            Profiler.release("Game Loop", 1000 / 60.0);
        }

        window.hide();
        cleanup();
    }

    private void processInput() {
        Profiler.mark("Engine.input()");
        final var strokes = InputHandler.getActivatedKeys();

        InputHandler
                .getBoundCombinations()
                .forEach(keyBinding -> {
                    if (strokes.equals(keyBinding.getCombination().getActivationCode())) {
                        final var action = keyBinding.getAction();

                        action.setScene(scene);
                        action.process();
                        action.setScene(null);
                    }
                });
        Profiler.release("Engine.input()");
    }

    private void update() {
        Profiler.mark("Engine.update()");
        scene.update();
        window.update();
        Profiler.release("Engine.update()");
    }

    private void render() {
        Profiler.mark("Engine.render()");
        scene.render();
        window.render();
        Profiler.release("Engine.render()");
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
