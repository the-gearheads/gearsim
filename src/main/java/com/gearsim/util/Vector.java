package com.gearsim.util;

public class Vector {
    private double x;
    private double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Vector add(Vector a, Vector b) {
        return new Vector(a.X() + b.X(), a.Y() + b.Y());
    }

    public static Vector subtract(Vector a, Vector b) {
        return new Vector(a.X() - b.X(), a.Y() - b.Y());
    }

    public static Vector multiply(Vector a, Vector b) {
        return new Vector(a.X() * b.X(), a.Y() * b.Y());
    }

    public static Vector divide(Vector a, Vector b) {
        return new Vector(a.X() / b.X(), a.Y() / b.Y());
    }

    public static Vector add(Vector a, double b) {
        return new Vector(a.X() + b, a.Y() + b);
    }

    public static Vector subtract(Vector a, double b) {
        return new Vector(a.X() - b, a.Y() - b);
    }

    public static Vector multiply(Vector a, double b) {
        return new Vector(a.X() * b, a.Y() * b);
    }

    public static Vector divide(Vector a, double b) {
        return new Vector(a.X() / b, a.Y() / b);
    }

    public double X() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double Y() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
