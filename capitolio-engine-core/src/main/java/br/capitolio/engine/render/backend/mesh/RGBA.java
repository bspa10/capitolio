package br.capitolio.engine.render.backend.mesh;

public class RGBA {
    public static RGBA RED = new RGBA(1.0f, 0.0f, 0.0f);
    public static RGBA GREEN = new RGBA(0.0f, 1.0f, 0.0f);
    public static RGBA BLUE = new RGBA(0.0f, 0.0f, 1.0f);
    public static RGBA BLACK = new RGBA(0.0f, 0.0f, 0.0f);

    private final float red;
    private final float green;
    private final float blue;
    private final float alfa;

    public RGBA(float red, float green, float blue, float alfa) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alfa = alfa;
    }

    public RGBA(float red, float green, float blue) {
        this(red, green, blue, 1.0f);
    }

    public float getRed() {
        return red;
    }

    public float getGreen() {
        return green;
    }

    public float getBlue() {
        return blue;
    }

    public float getAlfa() {
        return alfa;
    }
}
