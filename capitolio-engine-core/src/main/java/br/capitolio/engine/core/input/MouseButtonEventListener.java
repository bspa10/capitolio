package br.capitolio.engine.core.input;

import br.capitolio.engine.core.input.constants.InputAction;
import br.capitolio.engine.core.input.event.MouseButtonEvent;
import br.capitolio.engine.core.logging.Logger;
import br.capitolio.engine.core.logging.LoggerFactory;
import br.capitolio.engine.core.event.AbstractEvent;
import br.capitolio.engine.core.event.EventListener;

final class MouseButtonEventListener implements EventListener<MouseButtonEvent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(MouseButtonEventListener.class);

    @Override
    public void onEvent(AbstractEvent aEvent) {
        final var event = (MouseButtonEvent) aEvent;

        if (event.getAction() == InputAction.PRESS) {
            LOGGER.trace("%s %s", event.getAction(), event.getInput());
            InputHandler.buttons.add(event.getInput());
        }

        if (event.getAction() == InputAction.RELEASE) {
            LOGGER.trace("%s %s", event.getAction(), event.getInput());
            InputHandler.buttons.remove(event.getInput());
        }
    }
}
