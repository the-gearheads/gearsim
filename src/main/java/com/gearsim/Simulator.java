package com.gearsim;

public class Simulator implements Runnable {
    @Override
    public void run() {
        while(true) {
            System.out.println("this is simulator");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
