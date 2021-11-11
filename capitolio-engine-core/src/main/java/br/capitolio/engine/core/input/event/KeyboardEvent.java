package br.capitolio.engine.core.input.event;

import br.capitolio.engine.core.input.constants.InputAction;
import br.capitolio.engine.core.input.constants.KeyInput;
import lombok.Getter;

import java.util.StringJoiner;

@Getter
public abstract class KeyboardEvent extends AbstractKeyEvent {
    private final KeyInput input;

    public KeyboardEvent(InputAction action, KeyInput input) {
        super(action);
        this.input = input;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", KeyboardEvent.class.getCanonicalName() + "[", "]")
                .add(getAction().name())
                .add(input.name())
                .toString();
    }
}
