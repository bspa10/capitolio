package br.capitolio.engine.core.input;

import br.capitolio.engine.core.input.event.MousePositionEvent;
import br.capitolio.engine.event.AbstractEvent;
import br.capitolio.engine.event.EventListener;

final class MousePositionEventListener implements EventListener<MousePositionEvent> {

    @Override
    public void onEvent(AbstractEvent aEvent) {
        final var event = (MousePositionEvent) aEvent;

        InputHandler.mouse.set(event.getPosition());
    }
}
