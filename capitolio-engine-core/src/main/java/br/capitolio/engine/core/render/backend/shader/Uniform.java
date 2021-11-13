package br.capitolio.engine.core.render.backend.shader;

public enum Uniform {

    FRUSTUM("frustum"),
    WORLD_MATRIX("worldMatrix"),
    TEXTURE("f_texture");

    private final String name;
    Uniform(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
