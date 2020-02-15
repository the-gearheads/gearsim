package com.program.engine;

import com.program.renderer.Renderer;
import com.program.rigidbodies.RigidbodyRect;
import com.program.shapes.Rect;

public class Engine implements Runnable{
    private final Thread mainThread = new Thread(this, "MAIN_THREAD");
    private final Window window = new Window("Window", 800, 600);
    private final Renderer renderer = new Renderer();

    private RigidbodyRect object;

    @Override
    public void run() {
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (!window.isClosing()) {
            update();
            render();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        end();
        window.close();
        stop();
    }

    public void init() throws Exception {
        window.intialize();
        window.setBackgroundClearColor(1.0f, 1.0f, 0.0f, 1.0f);
        renderer.initShaders();
        renderer.initUniforms();
        renderer.initProjection(this.window);
    }

    private void update() {
        window.update();
    }

    private void render() {
        window.swapBuffers();
    }

    public void start() {
        mainThread.start();
    }

    public void end() {
        renderer.wrapUp();
    }

    public void stop() {
        mainThread.stop();
    }
}
