package br.capitolio.engine.core.input.event;

import br.capitolio.engine.core.input.constants.InputAction;
import br.capitolio.engine.core.input.constants.KeyInput;

public final class KeyboardKeyReleaseEvent extends KeyboardEvent{

    public KeyboardKeyReleaseEvent(KeyInput input) {
        super(InputAction.RELEASE, input);
    }
}
