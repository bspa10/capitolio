package br.capitolio.engine.graphics;

import lombok.Getter;
import org.joml.Vector3f;

@Getter
public class Vertex {

    private Vector3f position;
    private RGBA color;

    public Vertex(float x, float y, float z, RGBA color) {
        position = new Vector3f(x, y, z);
        this.color = color;
    }

    public Vertex(Vector3f position, RGBA color) {
        this.position = position;
        this.color = color;
    }
}
