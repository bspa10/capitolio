package br.capitolio.engine.core.control.input;

public enum KeyInput {

    KEY_A(1001),
    KEY_B(1002),
    KEY_C(1003),
    KEY_D(1004),
    KEY_E(1005),
    KEY_F(1006),
    KEY_G(1007),
    KEY_H(1008),
    KEY_I(1009),
    KEY_J(1010),
    KEY_K(1011),
    KEY_L(1012),
    KEY_M(1013),
    KEY_N(1014),
    KEY_O(1015),
    KEY_P(1016),
    KEY_Q(1017),
    KEY_R(1018),
    KEY_S(1019),
    KEY_T(1020),
    KEY_U(1021),
    KEY_V(1022),
    KEY_X(1023),
    KEY_Y(1024),
    KEY_Z(1025),

    KEY_UP(1026),
    KEY_DOWN(1027),
    KEY_LEFT(1028),
    KEY_RIGHT(1029),

    KEY_ESCAPE(1030),
    KEY_SPACE(1031),

    KEY_LEFT_ALT(1032),
    KEY_RIGHT_ALT(1033),

    KEY_LEFT_CTRL(1034),
    KEY_RIGHT_CTRL(1035),

    KEY_LEFT_SHIFT(1036),
    KEY_RIGHT_SHIFT(1037),

    KEY_KP_UP(1038),
    KEY_KP_DOWN(1039),
    KEY_KP_LEFT(1040),
    KEY_KP_RIGHT(1041);

    private final int keycode;

    KeyInput(int keycode) {
        this.keycode = keycode;
    }

    public int getKeycode() {
        return keycode;
    }
}
