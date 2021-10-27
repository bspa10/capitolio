package br.capitolio.engine;

import br.capitolio.engine.render.ShaderType;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public abstract class RenderManager {
    private static final ConcurrentMap<ShaderType, String> buffer = new ConcurrentHashMap<>();

    private RenderManager(){}

    public static void setShader(ShaderType type, String script) {
        buffer.put(type, script);
    }

    public static String getShader(ShaderType type) {
        return buffer.getOrDefault(type, null);
    }

}
