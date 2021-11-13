package br.capitolio.engine.gameplay;

import br.capitolio.engine.core.input.InputHandler;
import br.capitolio.engine.core.input.action.InputCombination;
import br.capitolio.engine.core.input.constants.KeyInput;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.util.List;

public final class Camera {
    private final Transform transform = new Transform();
    private final Matrix4f viewMatrix = new Matrix4f();

    public Camera() {
        InputHandler.mapping("forward", new InputCombination(List.of(KeyInput.KEY_W), null));
        InputHandler.binding("forward", () -> movePosition(0, 0, 1.0f));

        InputHandler.mapping("backward", new InputCombination(List.of(KeyInput.KEY_X), null));
        InputHandler.mapping("left", new InputCombination(List.of(KeyInput.KEY_A), null));
        InputHandler.mapping("right", new InputCombination(List.of(KeyInput.KEY_D), null));
    }

    public void setPosition(Vector3f position) {
        transform.getPosition().set(position);
    }

    public Vector3f getPosition() {
        return transform.getPosition();
    }

    public void movePosition(float offsetX, float offsetY, float offsetZ) {
        if ( offsetZ != 0 ) {
            getPosition().x += (float)Math.sin(Math.toRadians(getRotation().y)) * -1.0f * offsetZ;
            getPosition().z += (float)Math.cos(Math.toRadians(getRotation().y)) * offsetZ;
        }
        if ( offsetX != 0) {
            getPosition().x += (float)Math.sin(Math.toRadians(getRotation().y - 90)) * -1.0f * offsetX;
            getPosition().z += (float)Math.cos(Math.toRadians(getRotation().y - 90)) * offsetX;
        }
        getPosition().y += offsetY;
    }

    public void setRotation(Vector3f rotation) {
        transform.getRotation().set(rotation);
    }
    public Vector3f getRotation() {
        return transform.getRotation();
    }

    public void moveRotation(float offsetX, float offsetY, float offsetZ) {
        getRotation().add(offsetX, offsetY, offsetZ);
    }

    public Matrix4f getViewMatrix() {
        viewMatrix.identity();

        // First do the rotation so camera rotates over its position
        viewMatrix.rotate((float)Math.toRadians(transform.getRotation().x), new Vector3f(1, 0, 0))
                .rotate((float)Math.toRadians(transform.getRotation().y), new Vector3f(0, 1, 0));

        // Then do the translation
        viewMatrix.translate(-transform.getPosition().x, -transform.getPosition().y, -transform.getPosition().z);

        return viewMatrix;
    }
}
