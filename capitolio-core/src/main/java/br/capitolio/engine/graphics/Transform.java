package br.capitolio.engine.graphics;

import br.capitolio.engine.math.Vector3f;

public final class Transform {

    private final Vector3f position = Vector3f.zero;
    private final Vector3f rotation = Vector3f.zero;
    private final Vector3f scale = Vector3f.one;

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
