package br.capitolio.binding.opengl;

import br.capitolio.engine.EngineManager;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;

public class GLEngineManager extends EngineManager {
    private GLFWErrorCallback errorCallback;

    @Override
    protected void doStart() {
        errorCallback = GLFWErrorCallback.createPrint(System.err);
        GLFW.glfwSetErrorCallback(errorCallback);
    }

    @Override
    protected void doCleanup() {
        errorCallback.free();
    }
}
