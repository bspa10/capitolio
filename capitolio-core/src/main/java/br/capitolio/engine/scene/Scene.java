package br.capitolio.engine.scene;

import br.capitolio.engine.graphics.RGBA;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene {
    private final List<GameObject> objects = new ArrayList<>();

    public abstract RGBA getBgColor();

    public final void initialize() {
        doInitialize();
    }

    protected abstract void doInitialize();

    protected final void addGameObject(GameObject object) {
        objects.add(object);
    }

    public final List<GameObject> getObjects() {
        return objects;
    }

    public final void update() {
        objects.forEach(GameObject::update);
        doUpdate();
    }

    /**
     * Single frame scene step.
     */
    protected abstract void doUpdate();


    public final void destroy() {
        objects.forEach(GameObject::destroy);
    }
}
