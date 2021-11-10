package br.capitolio.engine.binding.opengl;

import br.capitolio.engine.EngineException;
import br.capitolio.engine.core.control.input.KeyInput;
import br.capitolio.engine.core.control.input.MouseInput;
import org.lwjgl.glfw.GLFW;

final class InputTranslator {

    public KeyInput translateKey(int keycode) {
        return switch (keycode) {
            // Printable Keys
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
            case GLFW.GLFW_KEY_W -> KeyInput.KEY_W;
            case GLFW.GLFW_KEY_X -> KeyInput.KEY_X;
            case GLFW.GLFW_KEY_Y -> KeyInput.KEY_Y;
            case GLFW.GLFW_KEY_Z -> KeyInput.KEY_Z;
            case GLFW.GLFW_KEY_APOSTROPHE -> KeyInput.KEY_APOSTROPHE;
            case GLFW.GLFW_KEY_COMMA -> KeyInput.KEY_COMMA;
            case GLFW.GLFW_KEY_MINUS -> KeyInput.KEY_MINUS;
            case GLFW.GLFW_KEY_PERIOD -> KeyInput.KEY_PERIOD;
            case GLFW.GLFW_KEY_SLASH -> KeyInput.KEY_SLASH;
            case GLFW.GLFW_KEY_0 -> KeyInput.KEY_0;
            case GLFW.GLFW_KEY_1 -> KeyInput.KEY_1;
            case GLFW.GLFW_KEY_2 -> KeyInput.KEY_2;
            case GLFW.GLFW_KEY_3 -> KeyInput.KEY_3;
            case GLFW.GLFW_KEY_4 -> KeyInput.KEY_4;
            case GLFW.GLFW_KEY_5 -> KeyInput.KEY_5;
            case GLFW.GLFW_KEY_6 -> KeyInput.KEY_6;
            case GLFW.GLFW_KEY_7 -> KeyInput.KEY_7;
            case GLFW.GLFW_KEY_8 -> KeyInput.KEY_8;
            case GLFW.GLFW_KEY_9 -> KeyInput.KEY_9;
            case GLFW.GLFW_KEY_SEMICOLON -> KeyInput.KEY_SEMICOLON;
            case GLFW.GLFW_KEY_EQUAL -> KeyInput.KEY_EQUAL;
            case GLFW.GLFW_KEY_LEFT_BRACKET -> KeyInput.KEY_LEFT_BRACKET;
            case GLFW.GLFW_KEY_BACKSLASH -> KeyInput.KEY_BACKSLASH;
            case GLFW.GLFW_KEY_RIGHT_BRACKET -> KeyInput.KEY_RIGHT_BRACKET;
            case GLFW.GLFW_KEY_GRAVE_ACCENT -> KeyInput.KEY_GRAVE_ACCENT;

            // Funtion
            case GLFW.GLFW_KEY_ESCAPE -> KeyInput.KEY_ESCAPE;
            case GLFW.GLFW_KEY_ENTER -> KeyInput.KEY_ENTER;
            case GLFW.GLFW_KEY_SPACE -> KeyInput.KEY_SPACE;
            case GLFW.GLFW_KEY_TAB -> KeyInput.KEY_TAB;
            case GLFW.GLFW_KEY_BACKSPACE -> KeyInput.KEY_BACKSPACE;
            case GLFW.GLFW_KEY_INSERT -> KeyInput.KEY_INSERT;
            case GLFW.GLFW_KEY_DELETE -> KeyInput.KEY_DELETE;
            case GLFW.GLFW_KEY_UP -> KeyInput.KEY_UP;
            case GLFW.GLFW_KEY_DOWN -> KeyInput.KEY_DOWN;
            case GLFW.GLFW_KEY_LEFT -> KeyInput.KEY_LEFT;
            case GLFW.GLFW_KEY_RIGHT -> KeyInput.KEY_RIGHT;
            case GLFW.GLFW_KEY_PAGE_UP -> KeyInput.KEY_PAGE_UP;
            case GLFW.GLFW_KEY_PAGE_DOWN -> KeyInput.KEY_PAGE_DOWN;
            case GLFW.GLFW_KEY_HOME -> KeyInput.KEY_HOME;
            case GLFW.GLFW_KEY_END -> KeyInput.KEY_END;
            case GLFW.GLFW_KEY_CAPS_LOCK -> KeyInput.KEY_CAPS_LOCK;
            case GLFW.GLFW_KEY_SCROLL_LOCK -> KeyInput.KEY_SCROLL_LOCK;
            case GLFW.GLFW_KEY_NUM_LOCK -> KeyInput.KEY_NUM_LOCK;
            case GLFW.GLFW_KEY_PRINT_SCREEN -> KeyInput.KEY_PRINT_SCREEN;
            case GLFW.GLFW_KEY_PAUSE -> KeyInput.KEY_PAUSE;
            case GLFW.GLFW_KEY_F1 -> KeyInput.KEY_F1;
            case GLFW.GLFW_KEY_F2 -> KeyInput.KEY_F2;
            case GLFW.GLFW_KEY_F3 -> KeyInput.KEY_F3;
            case GLFW.GLFW_KEY_F4 -> KeyInput.KEY_F4;
            case GLFW.GLFW_KEY_F5 -> KeyInput.KEY_F5;
            case GLFW.GLFW_KEY_F6 -> KeyInput.KEY_F6;
            case GLFW.GLFW_KEY_F7 -> KeyInput.KEY_F7;
            case GLFW.GLFW_KEY_F8 -> KeyInput.KEY_F8;
            case GLFW.GLFW_KEY_F9 -> KeyInput.KEY_F9;
            case GLFW.GLFW_KEY_F10 -> KeyInput.KEY_F10;
            case GLFW.GLFW_KEY_F11 -> KeyInput.KEY_F11;
            case GLFW.GLFW_KEY_F12 -> KeyInput.KEY_F12;

            case GLFW.GLFW_KEY_LEFT_ALT -> KeyInput.KEY_LEFT_ALT;
            case GLFW.GLFW_KEY_LEFT_SHIFT -> KeyInput.KEY_LEFT_SHIFT;
            case GLFW.GLFW_KEY_LEFT_CONTROL -> KeyInput.KEY_LEFT_CTRL;
            case GLFW.GLFW_KEY_RIGHT_ALT -> KeyInput.KEY_RIGHT_ALT;
            case GLFW.GLFW_KEY_RIGHT_CONTROL -> KeyInput.KEY_RIGHT_CTRL;
            case GLFW.GLFW_KEY_RIGHT_SHIFT -> KeyInput.KEY_RIGHT_SHIFT;

            case GLFW.GLFW_KEY_KP_0 -> KeyInput.KEY_KP_0;
            case GLFW.GLFW_KEY_KP_1 -> KeyInput.KEY_KP_1;
            case GLFW.GLFW_KEY_KP_2 -> KeyInput.KEY_KP_2;
            case GLFW.GLFW_KEY_KP_3 -> KeyInput.KEY_KP_3;
            case GLFW.GLFW_KEY_KP_4 -> KeyInput.KEY_KP_4;
            case GLFW.GLFW_KEY_KP_5 -> KeyInput.KEY_KP_5;
            case GLFW.GLFW_KEY_KP_6 -> KeyInput.KEY_KP_6;
            case GLFW.GLFW_KEY_KP_7 -> KeyInput.KEY_KP_7;
            case GLFW.GLFW_KEY_KP_8 -> KeyInput.KEY_KP_8;
            case GLFW.GLFW_KEY_KP_9 -> KeyInput.KEY_KP_9;
            case GLFW.GLFW_KEY_KP_DECIMAL -> KeyInput.KEY_KP_DECIMAL;
            case GLFW.GLFW_KEY_KP_DIVIDE -> KeyInput.KEY_KP_DIVIDE;
            case GLFW.GLFW_KEY_KP_MULTIPLY -> KeyInput.KEY_KP_MULTIPLY;
            case GLFW.GLFW_KEY_KP_SUBTRACT -> KeyInput.KEY_KP_SUBTRACT;
            case GLFW.GLFW_KEY_KP_ADD -> KeyInput.KEY_KP_ADD;
            case GLFW.GLFW_KEY_KP_ENTER -> KeyInput.KEY_KP_ENTER;
            case GLFW.GLFW_KEY_KP_EQUAL -> KeyInput.KEY_KP_EQUAL;
            case GLFW.GLFW_KEY_MENU -> KeyInput.KEY_MENU;
            case GLFW.GLFW_KEY_LEFT_SUPER -> KeyInput.KEY_LEFT_SUPER;
            case GLFW.GLFW_KEY_RIGHT_SUPER -> KeyInput.KEY_RIGHT_SUPER;

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
            case KEY_ENTER -> GLFW.GLFW_KEY_ENTER;
            case KEY_SPACE -> GLFW.GLFW_KEY_SPACE;
            case KEY_TAB -> GLFW.GLFW_KEY_TAB;
            case KEY_BACKSPACE -> GLFW.GLFW_KEY_BACKSPACE;
            case KEY_INSERT -> GLFW.GLFW_KEY_INSERT;
            case KEY_DELETE -> GLFW.GLFW_KEY_DELETE;
            case KEY_UP -> GLFW.GLFW_KEY_UP;
            case KEY_DOWN -> GLFW.GLFW_KEY_DOWN;
            case KEY_LEFT -> GLFW.GLFW_KEY_LEFT;
            case KEY_RIGHT -> GLFW.GLFW_KEY_RIGHT;
            case KEY_PAGE_UP -> GLFW.GLFW_KEY_PAGE_UP;
            case KEY_PAGE_DOWN -> GLFW.GLFW_KEY_PAGE_DOWN;
            case KEY_HOME -> GLFW.GLFW_KEY_HOME;
            case KEY_END -> GLFW.GLFW_KEY_END;
            case KEY_CAPS_LOCK -> GLFW.GLFW_KEY_CAPS_LOCK;
            case KEY_SCROLL_LOCK -> GLFW.GLFW_KEY_SCROLL_LOCK;
            case KEY_NUM_LOCK -> GLFW.GLFW_KEY_NUM_LOCK;
            case KEY_PRINT_SCREEN -> GLFW.GLFW_KEY_PRINT_SCREEN;
            case KEY_PAUSE -> GLFW.GLFW_KEY_PAUSE;
            case KEY_F1 -> GLFW.GLFW_KEY_F1;
            case KEY_F2 -> GLFW.GLFW_KEY_F2;
            case KEY_F3 -> GLFW.GLFW_KEY_F3 ;
            case KEY_F4 -> GLFW.GLFW_KEY_F4;
            case KEY_F5 -> GLFW.GLFW_KEY_F5;
            case KEY_F6 -> GLFW.GLFW_KEY_F6;
            case KEY_F7 -> GLFW.GLFW_KEY_F7;
            case KEY_F8 -> GLFW.GLFW_KEY_F8;
            case KEY_F9 -> GLFW.GLFW_KEY_F9;
            case KEY_F10 -> GLFW.GLFW_KEY_F10;
            case KEY_F11 -> GLFW.GLFW_KEY_F11;
            case KEY_F12 -> GLFW.GLFW_KEY_F12;

            case KEY_LEFT_ALT -> GLFW.GLFW_KEY_LEFT_ALT;
            case KEY_LEFT_SHIFT -> GLFW.GLFW_KEY_LEFT_SHIFT;
            case KEY_LEFT_CTRL -> GLFW.GLFW_KEY_LEFT_CONTROL;
            case KEY_RIGHT_ALT -> GLFW.GLFW_KEY_RIGHT_ALT ;
            case KEY_RIGHT_CTRL -> GLFW.GLFW_KEY_RIGHT_CONTROL;
            case KEY_RIGHT_SHIFT -> GLFW.GLFW_KEY_RIGHT_SHIFT;

            case KEY_KP_0 -> GLFW.GLFW_KEY_KP_0;
            case KEY_KP_1 -> GLFW.GLFW_KEY_KP_1;
            case KEY_KP_2 -> GLFW.GLFW_KEY_KP_2;
            case KEY_KP_3 -> GLFW.GLFW_KEY_KP_3;
            case KEY_KP_4 -> GLFW.GLFW_KEY_KP_4;
            case KEY_KP_5 -> GLFW.GLFW_KEY_KP_5;
            case KEY_KP_6 -> GLFW.GLFW_KEY_KP_6;
            case KEY_KP_7 -> GLFW.GLFW_KEY_KP_7;
            case KEY_KP_8 -> GLFW.GLFW_KEY_KP_8;
            case KEY_KP_9 -> GLFW.GLFW_KEY_KP_9;
            case KEY_KP_DECIMAL -> GLFW.GLFW_KEY_KP_DECIMAL;
            case KEY_KP_DIVIDE -> GLFW.GLFW_KEY_KP_DIVIDE;
            case KEY_KP_MULTIPLY -> GLFW.GLFW_KEY_KP_MULTIPLY;
            case KEY_KP_SUBTRACT -> GLFW.GLFW_KEY_KP_SUBTRACT;
            case KEY_KP_ADD -> GLFW.GLFW_KEY_KP_ADD;
            case KEY_KP_ENTER -> GLFW.GLFW_KEY_KP_ENTER;
            case KEY_KP_EQUAL -> GLFW.GLFW_KEY_KP_EQUAL;
            case KEY_MENU -> GLFW.GLFW_KEY_MENU;
            case KEY_LEFT_SUPER -> GLFW.GLFW_KEY_LEFT_SUPER;
            case KEY_RIGHT_SUPER -> GLFW.GLFW_KEY_RIGHT_SUPER;
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
            case KEY_F -> GLFW.GLFW_KEY_F;
            case KEY_G -> GLFW.GLFW_KEY_G;
            case KEY_H -> GLFW.GLFW_KEY_H;
            case KEY_I -> GLFW.GLFW_KEY_I;
            case KEY_J -> GLFW.GLFW_KEY_J;
            case KEY_K -> GLFW.GLFW_KEY_K;
            case KEY_L -> GLFW.GLFW_KEY_L;
            case KEY_M -> GLFW.GLFW_KEY_M;
            case KEY_N -> GLFW.GLFW_KEY_N;
            case KEY_O -> GLFW.GLFW_KEY_O;
            case KEY_P -> GLFW.GLFW_KEY_P;
            case KEY_Q -> GLFW.GLFW_KEY_Q;
            case KEY_R -> GLFW.GLFW_KEY_R;
            case KEY_S -> GLFW.GLFW_KEY_S;
            case KEY_T -> GLFW.GLFW_KEY_T;
            case KEY_U -> GLFW.GLFW_KEY_U;
            case KEY_V -> GLFW.GLFW_KEY_V;
            case KEY_W -> GLFW.GLFW_KEY_W;
            case KEY_X -> GLFW.GLFW_KEY_X;
            case KEY_Y -> GLFW.GLFW_KEY_Y;
            case KEY_Z -> GLFW.GLFW_KEY_Z;
            case KEY_APOSTROPHE -> GLFW.GLFW_KEY_APOSTROPHE;
            case KEY_COMMA -> GLFW.GLFW_KEY_COMMA;
            case KEY_MINUS -> GLFW.GLFW_KEY_MINUS;
            case KEY_PERIOD -> GLFW.GLFW_KEY_PERIOD;
            case KEY_SLASH -> GLFW.GLFW_KEY_SLASH;
            case KEY_0 -> GLFW.GLFW_KEY_0;
            case KEY_1 -> GLFW.GLFW_KEY_1;
            case KEY_2 -> GLFW.GLFW_KEY_2;
            case KEY_3 -> GLFW.GLFW_KEY_3;
            case KEY_4 -> GLFW.GLFW_KEY_4;
            case KEY_5 -> GLFW.GLFW_KEY_5;
            case KEY_6 -> GLFW.GLFW_KEY_6;
            case KEY_7 -> GLFW.GLFW_KEY_7;
            case KEY_8 -> GLFW.GLFW_KEY_8;
            case KEY_9 -> GLFW.GLFW_KEY_9;
            case KEY_SEMICOLON -> GLFW.GLFW_KEY_SEMICOLON;
            case KEY_EQUAL -> GLFW.GLFW_KEY_EQUAL;
            case KEY_LEFT_BRACKET -> GLFW.GLFW_KEY_LEFT_BRACKET;
            case KEY_BACKSLASH -> GLFW.GLFW_KEY_BACKSLASH;
            case KEY_RIGHT_BRACKET -> GLFW.GLFW_KEY_RIGHT_BRACKET;
            case KEY_GRAVE_ACCENT -> GLFW.GLFW_KEY_GRAVE_ACCENT;
            default -> -1;
        };

    }

    public int translate(MouseInput key) {
        return 0;
    }
}
