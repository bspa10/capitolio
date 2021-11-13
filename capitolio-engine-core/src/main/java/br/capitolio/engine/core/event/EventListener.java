package br.capitolio.engine.core.event;

public interface EventListener <E extends AbstractEvent>{

    void onEvent(AbstractEvent event);

}
