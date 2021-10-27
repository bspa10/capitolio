package br.capitolio.engine.device.output;

import br.capitolio.engine.graphics.RGBA;

/**
 * Displayable window of the game.
 */
public interface Window {

    static Window create() {
        return null;
    }

    long getIdentity();

    String getTitle();
    void setTitle(String title);

    void clear(RGBA bgColor);
    void show();
    void hide();

    /**
     * size[0] = width
     * <br>
     * size[1] = height
     */
    int[] getSize();
    void setSize(int width, int height);

    void render();
    void destroy();

    void update();
}
