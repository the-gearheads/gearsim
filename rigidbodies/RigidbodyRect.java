package com.program.rigidbodies;

import com.program.shapes.Rect;
import org.joml.Vector3f;

public class RigidbodyRect {
    private final Rect rect;
    private final Vector3f postition;
    private final Vector3f rotation;
    private final float scale;

    public RigidbodyRect(Rect rect) {
        this.rect = rect;
        this.postition = new Vector3f(0, 0, 0);
        this.rotation = new Vector3f(0, 0, 0);
        this.scale = 1;
    }

    public RigidbodyRect(Rect rect, Vector3f postition, Vector3f rotation, float scale) {
        this.rect = rect;
        this.postition = postition;
        this.rotation = rotation;
        this.scale = 1;
    }

    public void translate(float x, float y, float z) {
        this.postition.x = x;
        this.postition.y = y;
        this.postition.z = z;
    }

    public void rotate(float xdeg, float ydeg, float zdeg) {
        this.rotation.x = xdeg;
        this.rotation.y = ydeg;
        this.rotation.z = zdeg;
    }

    public Vector3f getPostition() {
        return postition;
    }

    public Vector3f getRotation() {
        return postition;
    }

    public float getScale() {
        return scale;
    }

    public Rect getRect() {
        return this.rect;
    }
}
