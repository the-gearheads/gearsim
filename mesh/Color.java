package com.program.mesh;

import org.joml.Vector4f;

public class Color {
    private int r, g, b, a;

    public Color(int r, int g, int b, int a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public float getR() {
        return (float) r / 255;
    }

    public float getG() {
        return (float) g / 255;
    }

    public float getB() {
        return (float) b / 255;
    }

    public float getA() {
        return (float) a / 255;
    }

    public Vector4f toVector() {
        return new Vector4f(getR(), getG(), getB(), getA());
    }
}
