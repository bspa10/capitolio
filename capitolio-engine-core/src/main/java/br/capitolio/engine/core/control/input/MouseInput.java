package br.capitolio.engine.core.control.input;

public enum MouseInput {

    LEFT_BUTTON(2001),
    MIDDLE_BUTTON(2002),
    RIGHT_BUTTON(2003);

    private final int keycode;

    MouseInput(int keycode) {
        this.keycode = keycode;
    }

    public int getKeycode() {
        return keycode;
    }
}
