package com.gearsim;

import java.awt.*;

public class Shape {
    private int points;
    private int radius;
    private int[] xPoints;
    private int[] yPoints;
    private int x;
    private int y;
    private int angleOfRotation;

    public Shape(int points, int x, int y, int radius) {
        if (points < 3) {
            return;
        } else {
            this.points = points;
            this.radius = radius;
            this.xPoints = new int[this.points];
            this.yPoints = new int[this.points];
            this.x = x;
            this.y = y;
        }
        construct();
    }

    private void construct() {
        int angleDifference = 360 / this.points;
        int currentAngle = 0;
        for (int i = 0; i < this.points; i++) {
            double currentX = this.x;
            double currentY = this.y;
            currentX = this.radius * Math.cos(Math.toRadians(currentAngle + angleOfRotation)) + this.x;
            currentY = this.radius * Math.sin(Math.toRadians(currentAngle + angleOfRotation)) + this.y;
            this.xPoints[i] = (int) currentX;
            this.yPoints[i] = (int) currentY;
            currentAngle += angleDifference;
        }
    }

    public void display(Graphics g) {
        g.fillPolygon(this.xPoints, this.yPoints, this.points);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        construct();
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
        this.xPoints = new int[this.points];
        this.yPoints = new int[this.points];
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
