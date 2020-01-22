package com.gearsim.util;

public class Vector {
    private double x;
    private double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Vector add(Vector a, Vector b) {
        return new Vector(a.getX() + b.getX(), a.getY() + b.getY());
    }

    public static Vector subtract(Vector a, Vector b) {
        return new Vector(a.getX() - b.getX(), a.getY() - b.getY());
    }

    public static Vector multiply(Vector a, Vector b) {
        return new Vector(a.getX() * b.getX(), a.getY() * b.getY());
    }

    public static Vector divide(Vector a, Vector b) {
        return new Vector(a.getX() / b.getX(), a.getY() / b.getY());
    }

    public static Vector add(Vector a, double b) {
        return new Vector(a.getX() + b, a.getY() + b);
    }

    public static Vector subtract(Vector a, double b) {
        return new Vector(a.getX() - b, a.getY() - b);
    }

    public static Vector multiply(Vector a, double b) {
        return new Vector(a.getX() * b, a.getY() * b);
    }

    public static Vector divide(Vector a, double b) {
        return new Vector(a.getX() / b, a.getY() / b);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
