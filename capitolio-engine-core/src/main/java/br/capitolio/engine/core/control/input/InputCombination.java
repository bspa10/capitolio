package br.capitolio.engine.core.control.input;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public final class InputCombination {

    private final int activationCode;
    private final List<KeyInput> keys = new ArrayList<>();
    private final List<MouseInput> buttons = new ArrayList<>();

    public InputCombination(List<KeyInput> keys, List<MouseInput> buttons) {
        if (keys != null && !keys.isEmpty()) {
            this.keys.addAll(keys);
        }

        if (buttons != null && !buttons.isEmpty()) {
            this.buttons.addAll(buttons);
        }

        activationCode =
                this.keys.stream().map(KeyInput::getKeycode).reduce(0, Integer::sum)
                        + this.buttons.stream().map(MouseInput::getKeycode).reduce(0, Integer::sum);
    }

    public int getActivationCode() {
        return activationCode;
    }
}
