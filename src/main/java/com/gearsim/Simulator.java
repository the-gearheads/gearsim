package com.gearsim;

import com.gearsim.util.Vector;
import sun.awt.image.PixelConverter;

import java.util.ArrayList;

public class Simulator implements Runnable {
    private double GLOBAL_TIME_ARBITRARY;
    private double INC;
    private double FPS;

    private Thread mainThread;
    private Screen mainScreen;
    private ArrayList<Rigidbody> rigidbodies;

    public Simulator(Screen screen) {
        this.INC += 0.01;

        this.mainThread = new Thread(this);
        this.mainScreen = screen;
        rigidbodies = new ArrayList<>();
        rigidbodies.add(new Rigidbody(new Vector(300, 300), new Shape(4, 100, 100, 100), 10));
    }

    @Override
    public void run() {
        while(true) {
            double previousFrame = System.nanoTime();

            updateRigidbodies();
            updateScreen();

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            previousFrame = System.nanoTime();
            this.GLOBAL_TIME_ARBITRARY += this.INC;
        }
    }

    private void updateRigidbodies() {
        for (Rigidbody rigidbody : rigidbodies) {
            rigidbody.update(GLOBAL_TIME_ARBITRARY);
        }
    }

    private void updateScreen() {
        for (Rigidbody rigidbody : rigidbodies) {
            this.mainScreen.addShape(rigidbody.getShape());
        }
        mainScreen.repaint();
    }

    private double FPS(double timestamp) {
        return 1000000000.0 / (System.nanoTime() - timestamp);
    }

    public void start() {
        this.mainThread.start();
    }

    public void stop() {
        this.mainThread.stop(); //Deprecated
    }

    public boolean isRunning() {
        return this.mainThread.isAlive();
    }
}
