package br.capitolio.engine.core.input.event;

import br.capitolio.engine.core.input.constants.InputAction;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
abstract class AbstractKeyEvent extends AbstractInputEvent {

    private final InputAction action;
}
