package br.capitolio.binding.opengl.control.output;

import br.capitolio.binding.opengl.GLException;
import br.capitolio.engine.EngineSettings;
import br.capitolio.control.output.Window;
import org.joml.Matrix4f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class GLWindow extends Window {
    private static final Logger LOGGER = LoggerFactory.getLogger(GLWindow.class);

    private GLWindow(){}

    @Override
    protected void doInit() {
        final var callback = GLFWErrorCallback.createPrint(System.err);
        GLFW.glfwSetErrorCallback(callback);

        if ( !GLFW.glfwInit() )
            throw new GLException("Unable to initialize GLFW");

        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 2);
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE);
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_FORWARD_COMPAT, GLFW.GLFW_TRUE);

        var maximized = false;
        final var videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        final var windowSize = EngineSettings.getWindowSize();
        if (windowSize.x == 0 || windowSize.y == 0) {
            windowSize.setComponent(0, videoMode.width());
            windowSize.setComponent(1, videoMode.height());
            GLFW.glfwWindowHint(GLFW.GLFW_MAXIMIZED, GLFW.GLFW_TRUE);
            maximized = true;
        }

        window = GLFW.glfwCreateWindow(windowSize.x, windowSize.y, EngineSettings.getWindowTitle(), MemoryUtil.NULL, MemoryUtil.NULL);
        if (window == MemoryUtil.NULL)
            throw new GLException("Unable to create window");

        GLFW.glfwSetFramebufferSizeCallback(window, (window, width, height) -> {
            LOGGER.debug("<Window> Resized ({}, {})", width, height);
            windowSize.set(width, height);
            resize = true;
        });

        GLFW.glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
           if (key == GLFW.GLFW_KEY_ESCAPE && action == GLFW.GLFW_RELEASE)
               GLFW.glfwSetWindowShouldClose(window, true);
        });

        if (maximized)
            GLFW.glfwMaximizeWindow(window);
        else {
            GLFW.glfwSetWindowPos(
                    window,
                    (videoMode.width() - windowSize.x) / 2,
                    (videoMode.height() - windowSize.y) / 2
            );
        }

        GLFW.glfwMakeContextCurrent(window);

        GL.createCapabilities();
        GLFW.glfwShowWindow(window);
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_STENCIL_TEST);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glCullFace(GL11.GL_BACK);
    }

    @Override
    public void setViewPort(int bottom, int left) {
        GL11.glViewport(0, 0, EngineSettings.getWindowSize().x, EngineSettings.getWindowSize().y);
    }

    public void render() {
        GLFW.glfwSwapBuffers(window);
    }

    public void update() {
        GLFW.glfwPollEvents();
    }

    public void doCleanup() {
        GLFW.glfwDestroyWindow(window);
    }

    public boolean isKeyPressed(int keycode) {
        return GLFW.glfwGetKey(window, keycode) == GLFW.GLFW_PRESS;
    }

    @Override
    public void setBgColor(float red, float green, float blue, float alfa) {
        GL11.glClearColor(red, green, blue, alfa);
    }

    public boolean shouldClose() {
        return GLFW.glfwWindowShouldClose(window);
    }

    public void setTitle(String title) {
        GLFW.glfwSetWindowTitle(window, title);
    }

    public Matrix4f updateProjection() {
        return projection.setPerspective(FOV, getAspectRatio(), Z_NEAR, Z_FAR);
    }

    public Matrix4f updateProjection(Matrix4f matrix, int width, int height) {
        return matrix.setPerspective(FOV, (float) width / height, Z_NEAR, Z_FAR);
    }
}

