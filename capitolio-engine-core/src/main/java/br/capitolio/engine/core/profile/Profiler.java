package br.capitolio.engine.core.profile;

import br.capitolio.engine.EngineSettings;
import br.capitolio.engine.core.logging.Logger;
import br.capitolio.engine.core.logging.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public abstract class Profiler {
    private static final Logger LOGGER = LoggerFactory.getLogger(Profiler.class);
    private static final ConcurrentMap<String, Long> marks = new ConcurrentHashMap<>();

    private Profiler(){}

    private static long frame = 0;
    public static void setFrame(long frame) {
        Profiler.frame = frame;
    }
    public static long getFrame() {
        return Profiler.frame;
    }

    public static void mark(String name) {
        if (!EngineSettings.isProfile())
            return;
        marks.put(name, System.nanoTime());
    }
    public static void release(String name) {
        if (!EngineSettings.isProfile())
            return;

        final var delta = doRelease(name) / 1000000.0;
        LOGGER.debug("[Frame %s][%s] %s ms", frame, name , delta);
    }

    public static void release(String name, double limit){
        if (!EngineSettings.isProfile())
            return;

        final var delta = doRelease(name) / 1000000.0;

        if (delta > limit)
            LOGGER.warn("[Frame %s][%s] %s ms", frame, name , delta);
        else
            LOGGER.debug("[Frame %s][%s] %s ms", frame, name , delta);
    }

    private static float doRelease(String name) {
        final var delta = System.nanoTime() - marks.get(name);
        marks.remove(name);
        return delta;
    }
}
