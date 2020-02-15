package com.program.rigidbodies;

import com.program.shapes.Cube;
import org.joml.Vector3f;

public class RigidbodyCube {
    private final Cube cube;
    private final Vector3f postition;
    private final Vector3f rotation;
    private final float scale;

    public RigidbodyCube(Cube cube) {
        this.cube = cube;
        this.postition = new Vector3f(0, 0, 0);
        this.rotation = new Vector3f(0, 0, 0);
        this.scale = 1;
    }

    public RigidbodyCube(Cube rect, Vector3f postition, Vector3f rotation, float scale) {
        this.cube = rect;
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
        return rotation;
    }

    public float getScale() {
        return scale;
    }

    public Cube getMesh() {
        return this.cube;
    }
}
