package br.capitolio.binding.opengl.device.input;

import br.capitolio.engine.device.input.KeyAction;
import br.capitolio.engine.device.input.KeyCode;
import org.lwjgl.glfw.GLFW;

public abstract class GLKeyConstants {
    private GLKeyConstants(){}

    public static int action(KeyAction action) {
        return switch (action) {
            case PRESS -> GLFW.GLFW_PRESS;
            case RELEASE -> GLFW.GLFW_RELEASE;
            case REPEAT -> GLFW.GLFW_REPEAT;
        };
    }

    public static KeyAction action(int action) {
        return switch (action) {
            case GLFW.GLFW_PRESS -> KeyAction.PRESS;
            case GLFW.GLFW_RELEASE -> KeyAction.RELEASE;
            case GLFW.GLFW_REPEAT -> KeyAction.REPEAT;
            default -> null;
        };
    }

    public static int code(KeyCode code) {
        return switch (code) {
            case ENTER -> GLFW.GLFW_KEY_ENTER;
            case ESCAPE -> GLFW.GLFW_KEY_ESCAPE;
            case MOUSE_BUTTON_LEFT -> GLFW.GLFW_MOUSE_BUTTON_LEFT;
            case MOUSE_BUTTON_RIGHT -> GLFW.GLFW_MOUSE_BUTTON_RIGHT;
        };
    }

    public static KeyCode code(int code) {
        return switch (code) {
            case GLFW.GLFW_KEY_ENTER -> KeyCode.ENTER;
            case GLFW.GLFW_KEY_ESCAPE -> KeyCode.ESCAPE;
            case GLFW.GLFW_MOUSE_BUTTON_LEFT -> KeyCode.MOUSE_BUTTON_LEFT;
            case GLFW.GLFW_MOUSE_BUTTON_RIGHT -> KeyCode.MOUSE_BUTTON_RIGHT;
            default -> null;
        };
    }

}
