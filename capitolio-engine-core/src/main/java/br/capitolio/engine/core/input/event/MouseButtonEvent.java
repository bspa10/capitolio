package br.capitolio.engine.core.input.event;

import br.capitolio.engine.core.input.constants.InputAction;
import br.capitolio.engine.core.input.constants.MouseInput;
import lombok.Getter;

@Getter
public final class MouseButtonEvent extends AbstractKeyEvent {
    private final MouseInput input;

    public MouseButtonEvent(InputAction action, MouseInput input) {
        super(action);
        this.input = input;
    }
}
