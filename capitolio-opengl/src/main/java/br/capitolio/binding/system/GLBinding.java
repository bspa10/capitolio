package br.capitolio.binding.system;

import br.capitolio.engine.EngineException;
import br.capitolio.engine.system.Binding;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

public class GLBinding implements Binding {

    private GLBinding(){}

    @Override
    public void initialize() {
        if ( ! GLFW.glfwInit() )
            throw new EngineException("Unable to initialize GLFW");

        final var callback = GLFWErrorCallback.createPrint(System.err);
        GLFW.glfwSetErrorCallback(callback);

        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE);

    }

    @Override
    public void destroy() {
        GLFW.glfwTerminate();
        GL.destroy();
    }
}
