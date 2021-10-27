package br.capitolio.engine.math;

public class Vector2f {
    public static final Vector2f zero = new Vector2f(0.0f, 0.0f);
    public static final Vector2f one = new Vector2f(1.0f, 1.0f);

    private float x, y;

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

}
