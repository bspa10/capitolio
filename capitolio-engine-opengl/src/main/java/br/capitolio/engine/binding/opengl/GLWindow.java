package br.capitolio.engine.binding.opengl;

import br.capitolio.engine.EngineSettings;
import br.capitolio.engine.core.Window;
import br.capitolio.engine.core.input.constants.InputAction;
import br.capitolio.engine.core.input.event.*;
import br.capitolio.engine.core.logging.Logger;
import br.capitolio.engine.core.logging.LoggerFactory;
import br.capitolio.engine.event.EventBus;
import org.joml.Matrix4f;
import org.joml.Vector2d;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;

public final class GLWindow extends Window {
    private static final Logger LOGGER = LoggerFactory.getLogger(GLWindow.class);

    private final InputTranslator inputTranslator = new InputTranslator();

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
            windowSize.set(width, height);
            resize = true;
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
//        GL11.glEnable(GL11.GL_STENCIL_TEST);
//        GL11.glEnable(GL11.GL_CULL_FACE);
//        GL11.glCullFace(GL11.GL_BACK);

        GLFW.glfwSetKeyCallback(window,(window, key, scancode, action, mods) -> {
            if (action == GLFW.GLFW_REPEAT)
                return;

            final var ik = inputTranslator.translateKey(key);
            if (action == GLFW.GLFW_PRESS)
                EventBus.post(new KeyboardKeyPressEvent(ik));

            if (action == GLFW.GLFW_RELEASE)
                EventBus.post(new KeyboardKeyReleaseEvent(ik));

        });

        GLFW.glfwSetMouseButtonCallback(window, (window, button, action, mods) -> {
            if (action == GLFW.GLFW_REPEAT)
                return;

            final var ik = inputTranslator.translateButton(button);
            final var ia = action == GLFW.GLFW_PRESS ? InputAction.PRESS : InputAction.RELEASE;

            EventBus.post(new MouseButtonEvent(ia, ik));
        });

        GLFW.glfwSetCursorPosCallback(window, (window, xpos, ypos) -> {
            final var position = new Vector2d(xpos, ypos);

            EventBus.post(new MousePositionEvent(position));
        });


    }

    @Override
    public void hide() {
        LOGGER.trace("Hiding window");
        GLFW.glfwHideWindow(window);
    }

    @Override
    public void setViewPort(int bottom, int left) {
        GL11.glViewport(0, 0, EngineSettings.getWindowSize().x, EngineSettings.getWindowSize().y);
    }

    @Override
    protected void doRender() {
        GL11.glFlush();
        GLFW.glfwSwapBuffers(window);
    }

    @Override
    public void update() {
        GLFW.glfwPollEvents();
    }

    @Override
    public void doCleanup() {
        GLFW.glfwDestroyWindow(window);
    }

    @Override
    public void setBgColor(float red, float green, float blue, float alfa) {
        GL11.glClearColor(red, green, blue, alfa);
    }

    @Override
    public void setTitle(String title) {
        GLFW.glfwSetWindowTitle(window, title);
    }

    @Override
    public Matrix4f getProjectionMatrix() {
        return projection
                .identity()
                .perspective(
                        EngineSettings.getFieldOfView(),
                        getAspectRatio(),
                        EngineSettings.getzNear(),
                        EngineSettings.getzFar()
                );
    }
}

