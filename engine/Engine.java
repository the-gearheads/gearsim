package com.program.engine;

import com.program.renderer.Camera;
import com.program.renderer.Renderer;
import com.program.rigidbodies.Rigidbody;
import com.program.shapes.Cube;
import org.joml.Vector3f;
import org.lwjgl.system.CallbackI;

public class Engine implements Runnable{
    private final Thread mainThread = new Thread(this, "MAIN_THREAD");
    private final Window window = new Window("Window", 800, 600);
    private final Renderer renderer = new Renderer(window);

    private Camera camera;
    private Rigidbody[] objects = new Rigidbody[100];
    private Rigidbody object;
    int dir = 1;

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
        window.setBackgroundClearColor(0.0f, 0.1f, 0.0f, 1.0f);

        renderer.initShaders();
        renderer.initUniforms();

        camera = new Camera();

        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 10; i++) {
                Rigidbody object = new Rigidbody(
                        new Cube(),
                        new Vector3f((i * 2) - 10, 0, -(10 + (j * 2))),
                        new Vector3f(0, 0, 0),
                        1
                );
                objects[i + (j * 10)] = object;
            }
        }
        camera.translate(0, -20, 5);
        camera.rotate(60, 0, 0);
    }

    private void update() {
        window.update();
        objects[54].rotate(2, 3, 4);
        if (objects[92].getPostition().y > 0.5f) {
            dir = -1;
        }
        else if (objects[92].getPostition().y < -0.5f) {
            dir = 1;
        }
        objects[92].translate(0, dir * 0.09f, 0);
    }

    private void render() {
        renderer.render(objects, camera);
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
