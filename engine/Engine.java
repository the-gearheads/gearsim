package com.program.engine;

import com.program.renderer.Renderer;
import com.program.math.Vector3f;
import com.program.shapes.Mesh2D;
import com.program.shapes.Vertex;

public class Engine implements Runnable{
    private final Thread mainThread = new Thread(this, "MAIN_THREAD");
    private final Window window = new Window("Window", 800, 600);
    private final Renderer renderer = new Renderer();
    private Mesh2D mesh;

    @Override
    public void run() {
        init();
        while (!window.isClosing()) {
            update();
            render();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        window.close();
        stop();
    }

    public void init() {
        window.intialize();
        window.setBackgroundClearColor(1.0f, 1.0f, 0.0f, 1.0f);

        mesh = new Mesh2D(new Vertex[] {
                new Vertex(new Vector3f(-0.5f, 0.5f, 0.0f)),
                new Vertex(new Vector3f(0.5f, 0.5f, 0.0f)),
                new Vertex(new Vector3f(0.5f, -0.5f, 0.0f)),
                new Vertex(new Vector3f(-0.5f, -0.5f, 0.0f))
        }, new int[] {
            0, 1, 2,
            0, 3, 2
        });
    }

    private void update() {
        window.update();
    }

    private void render() {
        renderer.renderMesh2D(mesh);
        window.swapBuffers();
    }

    public void start() {
        mainThread.start();
    }

    public void stop() {
        mainThread.stop();
    }
}
