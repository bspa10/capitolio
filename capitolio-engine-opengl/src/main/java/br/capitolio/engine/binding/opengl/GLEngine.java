package br.capitolio.engine.binding.opengl;

import br.capitolio.engine.Engine;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;

public final class GLEngine extends Engine {
    private GLFWErrorCallback errorCallback;

    private GLEngine(){}

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
