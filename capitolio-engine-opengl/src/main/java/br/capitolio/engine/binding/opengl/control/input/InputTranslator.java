package br.capitolio.engine.binding.opengl.control.input;

import br.capitolio.engine.EngineException;
import br.capitolio.engine.core.control.input.KeyInput;
import br.capitolio.engine.core.control.input.MouseInput;
import org.lwjgl.glfw.GLFW;

public class InputTranslator {

    public KeyInput translateKey(int keycode) {
        return switch (keycode) {
            // Printable
            case GLFW.GLFW_KEY_A -> KeyInput.KEY_A;
            case GLFW.GLFW_KEY_B -> KeyInput.KEY_B;
            case GLFW.GLFW_KEY_C -> KeyInput.KEY_C;
            case GLFW.GLFW_KEY_D -> KeyInput.KEY_D;
            case GLFW.GLFW_KEY_E -> KeyInput.KEY_E;
            case GLFW.GLFW_KEY_F -> KeyInput.KEY_F;
            case GLFW.GLFW_KEY_G -> KeyInput.KEY_G;
            case GLFW.GLFW_KEY_H -> KeyInput.KEY_H;
            case GLFW.GLFW_KEY_I -> KeyInput.KEY_I;
            case GLFW.GLFW_KEY_J -> KeyInput.KEY_J;
            case GLFW.GLFW_KEY_K -> KeyInput.KEY_K;
            case GLFW.GLFW_KEY_L -> KeyInput.KEY_L;
            case GLFW.GLFW_KEY_M -> KeyInput.KEY_M;
            case GLFW.GLFW_KEY_N -> KeyInput.KEY_N;
            case GLFW.GLFW_KEY_O -> KeyInput.KEY_O;
            case GLFW.GLFW_KEY_P -> KeyInput.KEY_P;
            case GLFW.GLFW_KEY_Q -> KeyInput.KEY_Q;
            case GLFW.GLFW_KEY_R -> KeyInput.KEY_R;
            case GLFW.GLFW_KEY_S -> KeyInput.KEY_S;
            case GLFW.GLFW_KEY_T -> KeyInput.KEY_T;
            case GLFW.GLFW_KEY_U -> KeyInput.KEY_U;
            case GLFW.GLFW_KEY_V -> KeyInput.KEY_V;
            case GLFW.GLFW_KEY_X -> KeyInput.KEY_X;
            case GLFW.GLFW_KEY_Y -> KeyInput.KEY_Y;
            case GLFW.GLFW_KEY_Z -> KeyInput.KEY_Z;

            // Funtion
            case GLFW.GLFW_KEY_ESCAPE -> KeyInput.KEY_ESCAPE;
            case GLFW.GLFW_KEY_LEFT_ALT -> KeyInput.KEY_LEFT_ALT;
            case GLFW.GLFW_KEY_RIGHT_ALT -> KeyInput.KEY_RIGHT_ALT;
            case GLFW.GLFW_KEY_LEFT_CONTROL -> KeyInput.KEY_LEFT_CTRL;
            case GLFW.GLFW_KEY_RIGHT_CONTROL -> KeyInput.KEY_RIGHT_CTRL;
            case GLFW.GLFW_KEY_LEFT_SHIFT -> KeyInput.KEY_LEFT_SHIFT;
            case GLFW.GLFW_KEY_RIGHT_SHIFT -> KeyInput.KEY_RIGHT_SHIFT;

            case GLFW.GLFW_KEY_UP -> KeyInput.KEY_UP;
            case GLFW.GLFW_KEY_DOWN -> KeyInput.KEY_DOWN;
            case GLFW.GLFW_KEY_LEFT -> KeyInput.KEY_LEFT;
            case GLFW.GLFW_KEY_RIGHT -> KeyInput.KEY_RIGHT;

            case GLFW.GLFW_KEY_KP_8 -> KeyInput.KEY_KP_UP;
            case GLFW.GLFW_KEY_KP_2 -> KeyInput.KEY_KP_DOWN;
            case GLFW.GLFW_KEY_KP_4 -> KeyInput.KEY_KP_LEFT;
            case GLFW.GLFW_KEY_KP_6 -> KeyInput.KEY_KP_RIGHT;

            default -> throw new EngineException("Unknown keycode [%s]".formatted(keycode));
        };
    }


    public MouseInput translateButton(int keycode) {
        return switch (keycode) {
            case GLFW.GLFW_MOUSE_BUTTON_LEFT -> MouseInput.LEFT_BUTTON;
            case GLFW.GLFW_MOUSE_BUTTON_RIGHT -> MouseInput.RIGHT_BUTTON;
            case GLFW.GLFW_MOUSE_BUTTON_MIDDLE -> MouseInput.MIDDLE_BUTTON;
            default -> throw new EngineException("Unknown keycode [%s]".formatted(keycode));
        };
    }

    public int translate(KeyInput key) {
        var keycode = -1;

        keycode = keyFunction(key);
        if (keycode != -1)
            return keycode;

        return keyPrintable(key);
    }

    private int keyFunction(KeyInput key) {
        return switch (key) {
            case KEY_ESCAPE -> GLFW.GLFW_KEY_ESCAPE;
            case KEY_LEFT_ALT -> GLFW.GLFW_KEY_LEFT_ALT;
            case KEY_RIGHT_ALT -> GLFW.GLFW_KEY_RIGHT_ALT;
            case KEY_LEFT_CTRL -> GLFW.GLFW_KEY_LEFT_CONTROL;
            case KEY_RIGHT_CTRL -> GLFW.GLFW_KEY_RIGHT_CONTROL;
            case KEY_UP -> GLFW.GLFW_KEY_UP;
            case KEY_DOWN -> GLFW.GLFW_KEY_DOWN;
            case KEY_LEFT -> GLFW.GLFW_KEY_LEFT;
            case KEY_RIGHT -> GLFW.GLFW_KEY_RIGHT;
            default -> -1;
        };
    }

    private int keyPrintable(KeyInput key) {
        return switch (key) {
            case KEY_A -> GLFW.GLFW_KEY_A;
            case KEY_B -> GLFW.GLFW_KEY_B;
            case KEY_C -> GLFW.GLFW_KEY_C;
            case KEY_D -> GLFW.GLFW_KEY_D;
            case KEY_E -> GLFW.GLFW_KEY_E;
            default -> -1;
        };

    }

    public int translate(MouseInput key) {
        return 0;
    }
}
