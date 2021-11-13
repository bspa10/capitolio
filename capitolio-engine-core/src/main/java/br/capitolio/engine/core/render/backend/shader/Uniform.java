package br.capitolio.engine.core.render.backend.shader;

public enum Uniform {

    FRUSTUM("frustum"),
    MODEL_VIEW_MATRIX("modelViewMatrix"),
    TEXTURE("f_texture");

    private final String name;
    Uniform(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
