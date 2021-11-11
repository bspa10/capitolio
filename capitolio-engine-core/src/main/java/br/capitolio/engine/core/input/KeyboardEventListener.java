package br.capitolio.engine.core.input;

import br.capitolio.engine.core.input.constants.InputAction;
import br.capitolio.engine.core.input.event.KeyboardEvent;
import br.capitolio.engine.core.logging.Logger;
import br.capitolio.engine.core.logging.LoggerFactory;
import br.capitolio.engine.event.AbstractEvent;
import br.capitolio.engine.event.EventListener;

final class KeyboardEventListener implements EventListener<KeyboardEvent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(KeyboardEventListener.class);

    @Override
    public void onEvent(AbstractEvent aEvent) {
        final var event = (KeyboardEvent) aEvent;

        if (event.getAction() == InputAction.PRESS) {
            LOGGER.trace("%s %s", event.getAction(), event.getInput());
            InputHandler.keys.add(event.getInput());
        }

        if (event.getAction() == InputAction.RELEASE) {
            LOGGER.trace("%s %s", event.getAction(), event.getInput());
            InputHandler.keys.remove(event.getInput());
        }
    }
}
