package br.capitolio.minecraft.scene;

import br.capitolio.engine.core.graphics.Mesh;
import br.capitolio.engine.core.graphics.ObjectLoader;
import br.capitolio.engine.core.graphics.RGBA;
import br.capitolio.engine.core.graphics.Vertex;
import br.capitolio.engine.core.scene.GameObject;
import br.capitolio.engine.core.scene.Scene;
import org.lwjgl.glfw.GLFW;

public class WorldScene extends Scene {
    private int direction = 0;
    private float colour = 0.0f;
    private Mesh mesh;

    @Override
    public void doInit() {
        mesh = ObjectLoader.load(
                new Vertex[]{
                        new Vertex(-0.5f, 0.5f, 0f, RGBA.RED),
                        new Vertex(-0.5f, -0.5f, 0f, RGBA.GREEN),
                        new Vertex(0.5f, -0.5f, 0f, RGBA.BLUE),
                        new Vertex(0.5f, -0.5f, 0f, RGBA.BLUE),
                        new Vertex(0.5f, 0.5f, 0f, RGBA.GREEN),
                        new Vertex(-0.5f, 0.5f, 0f, RGBA.RED)
                },
                new int[] {0, 1, 3, 3, 1, 2}
        );

        final var cube = new GameObject();
        cube.setMesh(mesh);
        addObject(cube);
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

        window.setBgColor(colour, 0, colour, 0);
        render.render(this);
    }

    @Override
    protected void doCleanup() {
        mesh.cleanup();
    }
}
