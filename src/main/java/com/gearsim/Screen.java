package com.gearsim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Screen extends JPanel implements MouseListener {
    private ArrayList<Shape> shapes;

    public Screen(Color background) {
        this.addMouseListener(this);
        this.setBackground(background);
        this.shapes = new ArrayList<>();
    }

    public void addShape(Shape shape) {
        this.shapes.add(shape);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shape shape : shapes) {
            shape.display(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
