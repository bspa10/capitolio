package br.capitolio.engine.event;

import lombok.Getter;

@Getter
public abstract class AbstractEvent {

    protected final long timestamp = System.nanoTime();

}
