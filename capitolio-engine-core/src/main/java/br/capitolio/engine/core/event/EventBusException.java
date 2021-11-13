package br.capitolio.engine.core.event;

import br.capitolio.engine.EngineException;

public class EventBusException extends EngineException {

    public EventBusException(String message) {
        super(message);
    }
}
