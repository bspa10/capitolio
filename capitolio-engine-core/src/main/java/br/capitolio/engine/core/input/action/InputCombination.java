package br.capitolio.engine.core.input.action;

import br.capitolio.engine.core.input.constants.KeyInput;
import br.capitolio.engine.core.input.constants.MouseInput;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Getter
public final class InputCombination {

    private final String activationCode;
    private final List<KeyInput> keys = new ArrayList<>();
    private final List<MouseInput> buttons = new ArrayList<>();

    public InputCombination(List<KeyInput> keys, List<MouseInput> buttons) {
        if (keys != null && !keys.isEmpty()) {
            this.keys.addAll(keys);
        }

        if (buttons != null && !buttons.isEmpty()) {
            this.buttons.addAll(buttons);
        }

        activationCode = this.keys.stream().map(k -> String.valueOf(k.getKeycode())).collect(Collectors.joining("."))
                + this.buttons.stream().map(k -> String.valueOf(k.getKeycode())).collect(Collectors.joining("."));
    }

    public String getActivationCode() {
        return activationCode;
    }
}
