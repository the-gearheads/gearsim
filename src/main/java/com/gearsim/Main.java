package com.gearsim;

import javax.swing.*;
import java.awt.*;

public class Main {
    private static int width = 800;
    private static int height = 600;

    public static void main(String[] args) {
        JFrame mainWindow = new JFrame("Gearsim");
        mainWindow.setSize(800, 600);
        mainWindow.setVisible(true);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Screen screen = new Screen(Color.black);
        mainWindow.add(screen);

        Simulator simulator = new Simulator(screen);
        simulator.start();
    }
}
