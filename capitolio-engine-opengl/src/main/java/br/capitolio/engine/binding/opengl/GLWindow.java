package br.capitolio.engine.binding.opengl;

import br.capitolio.engine.EngineSettings;
import br.capitolio.engine.core.control.input.InputHandler;
import br.capitolio.engine.core.control.output.Window;
import br.capitolio.engine.core.logging.Logger;
import br.capitolio.engine.core.logging.LoggerFactory;
import org.joml.Matrix4f;
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
            LOGGER.debug("Resized (%s, %s)", width, height);
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
        GL11.glEnable(GL11.GL_STENCIL_TEST);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glCullFace(GL11.GL_BACK);

        GLFW.glfwSetKeyCallback(window,(window, key, scancode, action, mods) -> {
            final var input = inputTranslator.translateKey(key);
            if (action == GLFW.GLFW_RELEASE) {
                LOGGER.trace("Key [%s] released", input);
                InputHandler.keys.remove(input);
            }

            if (action == GLFW.GLFW_PRESS) {
                LOGGER.trace("Key [%s] pressed", input);
                InputHandler.keys.add(input);
            }
        });

        GLFW.glfwSetMouseButtonCallback(window, (window, button, action, mods) -> {
            final var input = inputTranslator.translateButton(button);
            if (action == GLFW.GLFW_RELEASE) {
                LOGGER.trace("Button [%s] released", input);
                InputHandler.buttons.remove(input);
            }

            if (action == GLFW.GLFW_PRESS) {
                LOGGER.trace("Button [%s] pressed", input);
                InputHandler.buttons.add(input);
            }
        });

        GLFW.glfwSetCursorPosCallback(window, (window, xpos, ypos) -> InputHandler.mouse.set(xpos, ypos));
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
    public void render() {
        GLFW.glfwSwapBuffers(window);
        GLFW.glfwSwapBuffers(window);
        GLFW.glfwSwapBuffers(window);
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

    private boolean isKeyActivated(int keycode) {
        if (keycode == -1)
            return false;

        return GLFW.glfwGetKey(window, keycode) == GLFW.GLFW_PRESS;
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
    public Matrix4f updateProjection() {
        return projection.setPerspective(FOV, getAspectRatio(), Z_NEAR, Z_FAR);
    }

    @Override
    public Matrix4f updateProjection(Matrix4f matrix, int width, int height) {
        return matrix.setPerspective(FOV, (float) width / height, Z_NEAR, Z_FAR);
    }
}
