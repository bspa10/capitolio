package br.capitolio.minecraft;

import br.capitolio.engine.GameLogic;
import org.lwjgl.glfw.GLFW;

public class Game extends GameLogic {
    private int direction = 0;
    private float colour = 0.0f;

    @Override
    public void init() {
        render.init();
    }

    @Override
    public void input() {
        if (window.isKeyPressed(GLFW.GLFW_KEY_UP))
            direction += 1;

        else if (window.isKeyPressed(GLFW.GLFW_KEY_DOWN))
            direction -= 1;

         else
            direction = 0;
    }

    @Override
    public void update() {
        colour += direction * 0.001f;
        if (colour > 1)
            colour = 1;

        if (colour < 0)
            colour = 0;
    }

    @Override
    public void render() {
        if (window.isResize())
            window.setViewPort(0, 0);

        window.setBgColor(0, 0, colour, 0);
        render.clear();
    }

    @Override
    public void cleanup() {
        render.cleanup();
    }
}
