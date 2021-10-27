package br.capitolio.binding.opengl.device.output;

import br.capitolio.binding.opengl.GLException;
import br.capitolio.binding.opengl.device.input.GLKeyConstants;
import br.capitolio.engine.Settings;
import br.capitolio.engine.InputManager;
import br.capitolio.engine.device.output.Window;
import br.capitolio.engine.graphics.RGBA;
import org.joml.Vector2d;
import org.joml.Vector2i;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class GLWindow implements Window {
    private final long identity;
    private final Vector2i size = Settings.getWindowSize();

    private GLWindow() {
        identity = GLFW.glfwCreateWindow(size.x, size.y, Settings.getWindowTitle(), 0, 0);

        if (identity == 0)
            throw new GLException("Unable to create window");

        GLFW.glfwMakeContextCurrent(identity);
        GL.createCapabilities();
        GLFW.glfwSwapInterval(1);
        GLFW.glfwSetKeyCallback(identity, new GLFWKeyCallback() {
            public void invoke(long window, int key, int scancode, int action, int mods) {
                final var keyCode = GLKeyConstants.code(key);
                if (keyCode == null)
                    return;

                final var handler = InputManager.getKeyboardHandler(keyCode);
                handler.handle(
                        keyCode,
                        GLKeyConstants.action(action)
                );
            }
        });

        GLFW.glfwSetMouseButtonCallback(identity, new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long window, int button, int action, int mods) {
                final var keyCode = GLKeyConstants.code(button);
                if (keyCode == null)
                    return;

                final var handler = InputManager.getKeyboardHandler(keyCode);
                handler.handle(
                        keyCode,
                        GLKeyConstants.action(action)
                );
            }
        });

        GLFW.glfwSetCursorPosCallback(identity, new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double x, double y) {
                InputManager
                        .getMousePositionHandlers()
                        .forEach(handler -> handler.handle(new Vector2d(x,y)));
            }
        });

        final var mouseScroll = new Vector2d(0, 0);
        GLFW.glfwSetScrollCallback(identity, new GLFWScrollCallback() {
            @Override
            public void invoke(long window, double x, double y) {
                mouseScroll.add(new Vector2d(x, y));

                InputManager
                        .getMouseScrollHandlers()
                        .forEach(handler -> handler.handle(mouseScroll));
            }
        });
    }

    public String getTitle() {
        return Settings.getWindowTitle();
    }

    public void setTitle(String title) {
        Settings.setWindowTitle(title);
        GLFW.glfwSetWindowTitle(identity, title);
    }

    @Override
    public void clear(RGBA bgColor) {
        GL11.glClearColor(bgColor.getRed(), bgColor.getGreen(), bgColor.getBlue(), bgColor.getAlfa());
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    }

    public void show() {
        GLFW.glfwShowWindow(identity);
    }

    public void hide() {
        GLFW.glfwHideWindow(identity);
    }

    public Vector2i getSize() {
        return size;
    }

    @Override
    public void setSize(Vector2i size) {
        this.size.set(size);
        Settings.setWindowSize(size);
    }

    public void render() {
        GLFW.glfwSwapBuffers(identity);
    }

    public void destroy() {
        Callbacks.glfwFreeCallbacks(identity);
        GLFW.glfwDestroyWindow(identity);
    }

    @Override
    public void update() {
        GLFW.glfwPollEvents();
    }
}
