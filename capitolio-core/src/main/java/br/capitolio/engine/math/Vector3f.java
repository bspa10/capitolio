package br.capitolio.engine.math;

public class Vector3f {
    public static final Vector3f zero = new Vector3f(0.0f, 0.0f,0.0f);
    public static final Vector3f one = new Vector3f(1.0f, 1.0f,1.0f);

    private float x, y, z;

    public Vector3f (float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
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

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }
}
