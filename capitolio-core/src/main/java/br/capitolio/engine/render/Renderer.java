package br.capitolio.engine.render;

import br.capitolio.engine.scene.Scene;

public interface Renderer {

    void render(Scene scene);

    void create();
    void destroy();
}
