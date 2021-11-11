package br.capitolio.engine.core.input;

import br.capitolio.engine.EngineException;
import br.capitolio.engine.core.input.action.AbstractAction;
import br.capitolio.engine.core.input.action.InputCombination;
import br.capitolio.engine.core.input.action.KeyBinding;
import br.capitolio.engine.core.input.constants.KeyInput;
import br.capitolio.engine.core.input.constants.MouseInput;
import br.capitolio.engine.event.EventBus;
import br.capitolio.tools.reflection.Reflections;
import org.joml.Vector2d;

import java.util.Collection;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;

public abstract class InputHandler {
    private static final ConcurrentMap<String, InputCombination> mappings = new ConcurrentHashMap<>();
    private static final ConcurrentMap<String, KeyBinding> bindings = new ConcurrentHashMap<>();

    static final Vector2d mouse = new Vector2d();
    static final SortedSet<KeyInput> keys = new ConcurrentSkipListSet<>();
    static final SortedSet<MouseInput> buttons = new ConcurrentSkipListSet<>();

    private InputHandler(){}

    static {
        EventBus.subscribe(KeyboardEventListener.class);
        EventBus.subscribe(MouseButtonEventListener.class);
        EventBus.subscribe(MousePositionEventListener.class);
    }

    public static String getActivatedKeys() {
        return keys.stream().map(k -> String.valueOf(k.getKeycode())).collect(Collectors.joining("."));
    }

    /**
     * Realiza o mapeamento de uma combinação de teclas a uma ação nomeada.
     */
    public static void mapping(String name, InputCombination combination) {
        mappings.put(name, combination);
    }

    public static Collection<KeyBinding> getBoundCombinations(){
        return bindings.values();
    }

    /**
     * Vincula uma ação nomeada a um código cliente
     */
    public static void binding(String name, Class<? extends AbstractAction> action) {
        if (!mappings.containsKey(name))
            throw new EngineException("Mapping [%s] not found".formatted(name));

        bindings.put(name, new KeyBinding(name, mappings.get(name), Reflections.Classes.newInstance(action)));
    }

}
