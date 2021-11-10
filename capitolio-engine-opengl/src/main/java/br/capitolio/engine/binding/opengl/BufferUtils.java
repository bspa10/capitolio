package br.capitolio.engine.binding.opengl;

import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public abstract class BufferUtils {
    private BufferUtils() {}

    public static FloatBuffer store(float[] data) {
        return MemoryUtil
                .memAllocFloat(data.length)
                .put(data)
                .flip();
    }

    public static IntBuffer store(int[] data) {
        return MemoryUtil
                .memAllocInt(data.length)
                .put(data)
                .flip();
    }

}
