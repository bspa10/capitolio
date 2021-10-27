package br.capitolio.engine;

import br.capitolio.engine.scene.Scene;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public abstract class SceneManager {
    private static final AtomicReference<Class<Scene>> current = new AtomicReference<>();
    private static final AtomicReference<Class<Scene>> previews = new AtomicReference<>();

    private SceneManager(){}

    public static void setCurrent(Class<? extends Scene> klass) {
        previews.getAndSet(current.get());
        current.getAndSet((Class<Scene>) klass);
    }

    public static Optional<Class<Scene>> getCurrent() {
        return Optional.ofNullable(current.get());
    }

    public static Optional<Class<Scene>> getPreviews() {
        return Optional.ofNullable(previews.get());
    }
}
