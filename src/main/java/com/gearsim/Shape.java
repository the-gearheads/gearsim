package com.gearsim;

import com.gearsim.util.Vector;

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
            this.points = points;
            this.radius = radius;
            this.position = new Vector(x, y);
        }
        construct();
    }

    private void construct() {
        int angleDifference = 360 / this.points;
        int currentAngle = 0;
        for (int i = 0; i < this.points; i++) {
            Vector currentPosition = new Vector(0, 0);
            currentPosition.setX(this.radius * Math.cos(Math.toRadians(currentAngle + angleOfRotation)) + this.position.getX());
            currentPosition.setY(this.radius * Math.sin(Math.toRadians(currentAngle + angleOfRotation)) + this.position.getX());
            this.poly.addPoint((int) currentPosition.getX(), (int) currentPosition.getY());
            currentAngle += angleDifference;
        }
    }

    public void display(Graphics g) {
        g.fillPolygon(this.poly);
    }

    public double getX() {
        return this.position.getX();
    }

    public double getY() {
        return this.position.getY();
    }

    public void setPosition(int x, int y) {
        this.position.setX(x);
        this.position.setY(y);
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
