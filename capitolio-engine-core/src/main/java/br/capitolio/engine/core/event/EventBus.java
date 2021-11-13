package br.capitolio.engine.core.event;

import br.capitolio.engine.core.logging.Logger;
import br.capitolio.engine.core.logging.LoggerFactory;
import br.capitolio.tools.reflection.Reflections;

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

    public static <E extends AbstractEvent> void post(E event) {
        synchronized (EventBus.class) {
            final var listener = findListener(event);

            if (listener != null)
                listener.onEvent(event);
        }
    }

    private static <E extends AbstractEvent> EventListener<? extends AbstractEvent> findListener(E event) {
        final var eventTypeName = event.getClass().getTypeName();
        if (listeners.containsKey(eventTypeName))
            for (var listener : listeners.get(eventTypeName))
                return listener;

        else
            for (var parent : Reflections.Classes.getParents(event)) {
                final var parentTypeName = parent.getTypeName();
                if (!listeners.containsKey(parentTypeName))
                    continue;

                for (var listener : listeners.get(parentTypeName))
                    return listener;
            }

        return null;
    }
}
