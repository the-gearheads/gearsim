package com.gearsim;

import com.gearsim.util.Vector;
import com.sun.org.apache.xerces.internal.impl.xs.models.XSCMRepeatingLeaf;

import java.awt.*;

public class Shape {
    private Polygon poly;
    private int points;
    private int radius;
    private Vector position;
    private int angleOfRotation;

    public Shape(int points, double x, double y, int radius) {
        if (points < 3) {
            return;
        } else {
            this.poly = new Polygon();
            this.points = points;
            this.radius = radius;
            this.position = new Vector(x, y);
        }
        construct();
    }

    private void construct() {
        clearPolygon();
        int angleDifference = 360 / this.points;
        int currentAngle = 0;
        for (int i = 0; i < this.points; i++) {
            Vector currentPosition = new Vector(this.position.X(), this.position.Y());
            currentPosition.setX(this.radius * Math.cos(Math.toRadians(currentAngle + angleOfRotation)) + this.position.X());
            currentPosition.setY(this.radius * Math.sin(Math.toRadians(currentAngle + angleOfRotation)) + this.position.Y());
            this.poly.addPoint((int) currentPosition.X(), (int) currentPosition.Y());
            currentAngle += angleDifference;
        }
    }

    private void clearPolygon() {
        this.poly.reset();
    }

    public void display(Graphics g) {
        g.fillPolygon(this.poly);
    }

    public double getX() {
        return this.position.X();
    }

    public double getY() {
        return this.position.Y();
    }

    public void setPosition(double x, double y) {
        this.position.setX(x);
        this.position.setY(y);
        construct();
    }

    public void setPosition(Vector position) {
        this.position.setX(position.X());
        this.position.setY(position.Y());
        construct();
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
        construct();
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setAngleOfRotation(int angle) {
        this.angleOfRotation = angle;
        construct();
    }

    public int getAngleOfRotation() {
        return this.angleOfRotation;
    }
}
