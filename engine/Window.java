package com.program.engine;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowCloseCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class Window {
    private String title;
    private int width, height;
    private long internalWindow;
    private float[] bgRGBA = new float[4];

    public Window(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
    }

    public void intialize() {
        if (!GLFW.glfwInit()) {
            return;
        }
        this.internalWindow = GLFW.glfwCreateWindow(this.width, this.height, this.title, 0,0);
        if (this.internalWindow == 0) {
            return;
        }
        GLFWVidMode vMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        GLFW.glfwShowWindow(this.internalWindow);
        GLFW.glfwSwapInterval(1);
        GLFW.glfwMakeContextCurrent(this.internalWindow);
        GL.createCapabilities();
    }

    public void update() {
        clear();
        GLFW.glfwPollEvents();
    }

    public void swapBuffers() {
        GLFW.glfwSwapBuffers(this.internalWindow);
    }

    private void clear() {
        GL11.glClearColor(
                this.bgRGBA[0],
                this.bgRGBA[1],
                this.bgRGBA[2],
                this.bgRGBA[3]
        );
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
    }

    public void setBackgroundClearColor(float r, float g, float b, float a) {
        this.bgRGBA[0] = r;
        this.bgRGBA[1] = g;
        this.bgRGBA[2] = b;
        this.bgRGBA[3] = a;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public boolean isClosing() {
        return GLFW.glfwWindowShouldClose(this.internalWindow);
    }

    public void close() {
        GLFW.glfwDestroyWindow(this.internalWindow);
    }
}
