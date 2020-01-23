package com.gearsim;

@SuppressWarnings({"InfiniteLoopStatement", "FieldCanBeLocal"})
public class Simulator implements Runnable {
    private Thread mainThread;
    private Screen mainScreen;
    private double GLOBAL_TIME_ARBITRARY;
    private double INC;
    private double FPS;

    public Simulator(Screen screen) {
        this.mainThread = new Thread(this);
        this.mainScreen = screen;
    }

    @Override
    public void run() {
        while(true) {
            this.GLOBAL_TIME_ARBITRARY += this.INC;
            double previousFrame = System.nanoTime();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            this.FPS = 1000000000.0 / (System.nanoTime() - previousFrame);
            System.out.println(this.FPS);
            previousFrame = System.nanoTime();
        }
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
