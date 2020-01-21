package com.gearsim;

import javax.swing.*;
import java.awt.*;

public class Main {
    private static int width = 800;
    private static int height = 600;

    public static void main(String[] args) {
        JFrame mainWindow = new JFrame("OK");
        Screen screen = new Screen(Color.black);
        mainWindow.setSize(500, 500);
        mainWindow.add(screen);
        mainWindow.setVisible(true);
    }
}
