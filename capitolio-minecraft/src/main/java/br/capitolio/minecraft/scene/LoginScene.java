package br.capitolio.minecraft.scene;

import br.capitolio.engine.InputManager;
import br.capitolio.engine.device.input.KeyAction;
import br.capitolio.engine.device.input.KeyCode;
import br.capitolio.engine.device.input.KeyboardHandler;
import br.capitolio.engine.GameState;
import br.capitolio.engine.graphics.Mesh;
import br.capitolio.engine.graphics.RGBA;
import br.capitolio.engine.graphics.Vertex;
import br.capitolio.engine.math.Vector3f;
import br.capitolio.engine.scene.GameObject;
import br.capitolio.engine.scene.Scene;

public class LoginScene extends Scene {

    @Override
    public RGBA getBgColor() {
        return RGBA.BLACK;
    }

    @Override
    protected void doInitialize() {
        build();
        keybind();
    }

    private void build() {
        final var block = new GameObject();
        block.setMesh(Mesh.newMesh(
                new Vertex[] {
                        new Vertex(new Vector3f(-0.5f,  0.5f, 0.0f), RGBA.GREEN),
                        new Vertex(new Vector3f(-0.5f, -0.5f, 0.0f), RGBA.GREEN),
                        new Vertex(new Vector3f(0.5f, -0.5f, 0.0f), RGBA.GREEN),
                        new Vertex(new Vector3f(0.5f,  0.5f, 0.0f), RGBA.GREEN)
                },
                new int[] {0, 1, 2, 0, 3, 2}
        ));

        addGameObject(block);

    }

    private void keybind() {
        InputManager.bind(KeyCode.ESCAPE, (KeyboardHandler) (key, action) -> {
            if (! action.equals(KeyAction.RELEASE))
                return;

            GameState.setShouldStop();
        });

    }

    @Override
    protected void doUpdate() {

    }
}
