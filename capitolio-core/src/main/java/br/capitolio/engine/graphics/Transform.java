package br.capitolio.engine.graphics;

import org.joml.Vector3f;

public final class Transform {

    private final Vector3f position = new Vector3f();
    private final Vector3f rotation = new Vector3f();
    private final Vector3f scale = new Vector3f(1.0f, 1.0f, 1.0f);

    public Vector3f getPosition() {
        return position;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public Vector3f getScale() {
        return scale;
    }
}
