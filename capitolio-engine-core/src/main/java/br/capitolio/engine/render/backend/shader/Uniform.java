package br.capitolio.engine.render.backend.shader;

public enum Uniform {

    PROJECTION_MATRIX("projectionMatrix"),
    WORLD_MATRIX("worldMatrix");

    private final String name;
    Uniform(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
