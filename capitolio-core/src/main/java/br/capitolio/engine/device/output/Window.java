package br.capitolio.engine.device.output;

import br.capitolio.engine.graphics.RGBA;
import org.joml.Vector2i;

/**
 * Displayable window of the game.
 */
public interface Window {

    String getTitle();
    void setTitle(String title);

    void clear(RGBA bgColor);
    void show();
    void hide();

    Vector2i getSize();
    void setSize(Vector2i size);

    void render();
    void destroy();

    void update();
}
