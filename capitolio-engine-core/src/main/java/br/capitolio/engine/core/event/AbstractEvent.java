package br.capitolio.engine.core.event;

import lombok.Getter;

@Getter
public abstract class AbstractEvent {

    protected final long timestamp = System.nanoTime();

}
