package com.gearsim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Screen extends JPanel implements MouseListener {
    public Circle circle;

    public Screen(Color background) {
        this.addMouseListener(this);
        this.setBackground(background);
        this.circle = new Circle(45, 200, 200, 100);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        circle.display(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        circle.setPoints(circle.getPoints() + 1);
        repaint();
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
