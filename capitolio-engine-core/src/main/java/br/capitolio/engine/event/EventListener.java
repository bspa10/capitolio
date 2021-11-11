package br.capitolio.engine.event;

public interface EventListener <E extends AbstractEvent>{

    void onEvent(AbstractEvent event);

}
