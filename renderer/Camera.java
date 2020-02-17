package com.program.renderer;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.system.CallbackI;

public class Camera {
    private Vector3f position;
    private Vector3f rotation;

    public Camera() {
        position = new Vector3f(0, 0, 0);
        rotation = new Vector3f(0, 0, 0);
    }

    public Camera(Vector3f position, Vector3f rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    public void position(float x, float y, float z) {
        this.position.x = x;
        this.position.y = y;
        this.position.z = z;
    }

    public void rotation(float xdeg, float ydeg, float zdeg) {
        this.rotation.x = xdeg;
        this.rotation.y = ydeg;
        this.rotation.z = zdeg;
    }

    public void translate(float x, float y, float z) {
        if (x != 0) {
            this.position.x += (float) Math.sin(Math.toRadians(this.position.y - 90)) * -x;
            this.position.z += (float) Math.cos(Math.toRadians(this.position.y - 90)) * x;
        }
        if (z != 0) {
            this.position.x += (float) Math.sin(Math.toRadians(this.position.y)) * -z;
            this.position.z += (float) Math.cos(Math.toRadians(this.position.y)) * z;
        }
        this.position.y += y;
    }

    public void rotate(float xdeg, float ydeg, float zdeg) {
        this.rotation.x += xdeg;
        this.rotation.y += ydeg;
        this.rotation.z += zdeg;
    }

    public Vector3f getPosition() {
        return this.position;
    }

    public Vector3f getRotation() {
        return this.rotation;
    }
}
