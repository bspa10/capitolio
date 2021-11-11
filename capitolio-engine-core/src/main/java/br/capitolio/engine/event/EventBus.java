package br.capitolio.engine.event;

import br.capitolio.engine.core.logging.Logger;
import br.capitolio.engine.core.logging.LoggerFactory;
import br.capitolio.tools.reflection.Reflections;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public abstract class EventBus {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventBus.class);
    private static final ConcurrentMap<String, List<EventListener<? extends AbstractEvent>>> listeners = new ConcurrentHashMap<>();

    private EventBus(){}

    public static void subscribe(Class<? extends EventListener<? extends AbstractEvent>> klass) {
        LOGGER.debug("subscribing [%s]", klass.getCanonicalName());
        final var types = Reflections.Classes.getGenericTypes(klass, EventListener.class);
        if (types.length == 0)
            throw new EventBusException("Listener class must implement [%s]".formatted(EventListener.class.getCanonicalName()));

        final var typeName = types[0].getTypeName();
        if (!listeners.containsKey(typeName))
            listeners.put(typeName, new ArrayList<>());

        listeners.get(typeName).add(Reflections.Classes.newInstance(klass));
    }

    /**
     * <p>
     * Envia o evento para o listener mais específico possível.
     * </p>
     * <p>
     *     Caso não se encontra o listener específico do evento, então
     *     é navegada na hierarquia da própria classe (extends) em busca
     *     de alguma classe que possa processar o event. Não sendo possível
     *     processar o evento, o mesmo é descartado em silêncio.
     * </p>
     */
    @SneakyThrows
    public static <E extends AbstractEvent> void post(E event) {
        if (listeners.containsKey(event.getClass().getTypeName()))
            for (var listener : listeners.get(event.getClass().getTypeName()))
                listener.onEvent(event);

        else {
            for (var parent : Reflections.Classes.getParents(event)) {
                if (!listeners.containsKey(parent.getTypeName()))
                    continue;

                for (var listener : listeners.get(parent.getTypeName()))
                    listener.onEvent(event);

                break;
            }
        }
    }
}
