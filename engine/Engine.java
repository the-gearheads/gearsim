package com.program.engine;

import com.program.renderer.Renderer;
import com.program.rigidbodies.RigidbodyCube;
import com.program.shapes.Cube;

public class Engine implements Runnable{
    private final Thread mainThread = new Thread(this, "MAIN_THREAD");
    private final Window window = new Window("Window", 800, 600);
    private final Renderer renderer = new Renderer();

    private RigidbodyCube object;

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
        window.setBackgroundClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        renderer.initShaders();
        renderer.initUniforms();
        renderer.initProjection(this.window);
        object = new RigidbodyCube(new Cube());
        object.translate(0, 0, -4);
    }

    private void update() {
        object.rotate(
                object.getRotation().x + 1,
                object.getRotation().y + 1,
                object.getRotation().z + 1);
        window.update();
    }

    private void render() {
        renderer.renderRidgidbody(object);
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
