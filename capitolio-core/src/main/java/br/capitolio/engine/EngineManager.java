package br.capitolio.engine;

import br.capitolio.engine.renderer.RenderManager;
import br.capitolio.framework.cdi.Injector;

public abstract class EngineManager {
    public static final long NANOSECOND= 1000000000L;
    public static final long FRAMERATE = 1000L;

    protected static int fps;
    protected static float frametime = 1.0f / FRAMERATE;
    protected boolean running;

    private final RenderManager render = Injector.inject(RenderManager.class);
    private final WindowManager window = Injector.inject(WindowManager.class);
    private final GameLogic gameLogic = Injector.inject(GameLogic.class);
    protected abstract void doStart();

    public final void start() {
        window.init();
        render.setWindow(window);

        gameLogic.setWindow(window);
        gameLogic.setRenderer(render);
        gameLogic.init();

        doStart();

        if (running)
            return;

        run();
    }

    public final void stop() {
        if (!running)
            return;

        running = false;
    }

    private void run() {
        running = true;
        var frames = 0;
        var frameCounter = 0L;
        var lastTime = System.nanoTime();
        var unprocessedTime = 0.0;

        while (running) {
            var render = false;
            var startTime = System.nanoTime();
            var passedTime = startTime - lastTime;
            lastTime = startTime;

            unprocessedTime += passedTime / (double) NANOSECOND;
            frameCounter += passedTime;

            input();

            while (unprocessedTime  > frametime) {
                render = true;
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

            if (render) {
                update();
                render();
                frames++;
            }
        }

        cleanup();
    }

    public void input() {
        gameLogic.input();
    }

    public void update() {
        gameLogic.update();
        window.update();
    }

    public void render() {
        gameLogic.render();
        window.render();
    }

    protected abstract void doCleanup();
    public final void cleanup() {
        doCleanup();
        window.cleanup();
        gameLogic.cleanup();
    }

}
