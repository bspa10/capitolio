package br.capitolio.binding.opengl.utils;

import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;

public abstract class BufferUtils {
    private BufferUtils() {}

    public static FloatBuffer store(float[] data) {
        return MemoryUtil
                .memAllocFloat(data.length)
                .put(data)
                .flip();
    }

}
