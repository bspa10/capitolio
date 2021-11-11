package br.capitolio.engine.core.input.action;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public final class KeyBinding {

    private final String name;
    private final InputCombination combination;
    private final AbstractAction action;

}
