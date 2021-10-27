package br.capitolio.engine.math;

public class Vector2d {
    public static final Vector2d zero = new Vector2d(0.0, 0.0);
    public static final Vector2d one = new Vector2d(1.0, 1.0);

    private double x, y;

    public Vector2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void add(Vector2d other) {
        x += other.x;
        y += other.y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

}
