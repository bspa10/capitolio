package br.capitolio.engine.event;

import br.capitolio.engine.EngineException;

public class EventBusException extends EngineException {

    public EventBusException(String message) {
        super(message);
    }
}
