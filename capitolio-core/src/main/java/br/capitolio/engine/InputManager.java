package br.capitolio.engine;

import br.capitolio.engine.device.input.*;

import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

public abstract class InputManager {
    private static final ConcurrentMap<KeyCode, KeyboardHandler> keyboard = new ConcurrentHashMap<>();
    private static final ConcurrentMap<KeyCode, MouseKeyHandler> mouse = new ConcurrentHashMap<>();
    private static final Queue<MousePositionHandler> position = new ConcurrentLinkedQueue<>();
    private static final Queue<MouseScrollHandler> scroll = new ConcurrentLinkedQueue<>();

    private InputManager() {}

    public static void reset() {
        keyboard.clear();
        position.clear();
        scroll.clear();
    }

    public static void bind(KeyCode key, KeyboardHandler handler) {
        keyboard.put(key, handler);
    }

    public static KeyboardHandler getKeyboardHandler(KeyCode key) {
        return keyboard.getOrDefault(key, (code, action) -> {});
    }

    public static void bind(KeyCode key, MouseKeyHandler handler) {
        mouse.put(key, handler);
    }

    public static MouseKeyHandler getMouseKeyHandler(KeyCode key) {
        return mouse.getOrDefault(key, (code, action) -> {});
    }

    public static void bind(MousePositionHandler handler) {
        position.add(handler);
    }

    public static Queue<MousePositionHandler> getMousePositionHandlers() {
        return position;
    }

    public static void bind(MouseScrollHandler handler) {
        scroll.add(handler);
    }

    public static Queue<MouseScrollHandler> getMouseScrollHandlers() {
        return scroll;
    }
}
