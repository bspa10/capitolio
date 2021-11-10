package br.capitolio.engine.core.control.input;

import br.capitolio.engine.EngineException;
import br.capitolio.tools.reflection.Reflections;
import org.joml.Vector2d;

import java.util.Collection;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListSet;

public abstract class InputHandler {
    private static final ConcurrentMap<String, InputCombination> mappings = new ConcurrentHashMap<>();
    private static final ConcurrentMap<String, KeyBinding> bindings = new ConcurrentHashMap<>();

    public static final Vector2d mouse = new Vector2d();
    public static final SortedSet<KeyInput> keys = new ConcurrentSkipListSet<>();
    public static final SortedSet<MouseInput> buttons = new ConcurrentSkipListSet<>();

    private InputHandler(){}

    public static int getActivatedKeys() {
        return keys.stream().map(KeyInput::getKeycode).reduce(0, Integer::sum);
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
            throw new EngineException("Mapping [%s] not found", name);

        bindings.put(name, new KeyBinding(name, mappings.get(name), Reflections.Classes.newInstance(action)));
    }

}
