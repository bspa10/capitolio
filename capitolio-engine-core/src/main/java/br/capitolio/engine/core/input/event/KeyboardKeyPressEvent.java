package br.capitolio.engine.core.input.event;

import br.capitolio.engine.core.input.constants.InputAction;
import br.capitolio.engine.core.input.constants.KeyInput;

public final class KeyboardKeyPressEvent extends KeyboardEvent {

    public KeyboardKeyPressEvent(KeyInput input) {
        super(InputAction.PRESS, input);
    }
}
