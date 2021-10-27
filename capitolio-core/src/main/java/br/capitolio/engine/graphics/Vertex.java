package br.capitolio.engine.graphics;

import br.capitolio.engine.math.Vector3f;

public final class Vertex {
    private final Vector3f position;
    private final RGBA color;


    public Vertex(Vector3f position, RGBA color) {
        this.position = position;
        this.color = color;
    }

    public Vector3f getPosition() {
        return position;
    }

    public RGBA getColor() {
        return color;
    }
}
