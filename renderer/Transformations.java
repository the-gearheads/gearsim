package com.program.renderer;

import org.joml.Matrix4f;
import org.joml.Vector2d;
import org.joml.Vector3f;

public class Transformations {
    private Matrix4f projMat;
    private Matrix4f worldMat;

    public Transformations() {
        projMat = new Matrix4f();
        worldMat = new Matrix4f();
    }

    public Matrix4f getProjMatrix(float fov, float zNear, float zFar, float width, float height) {
        float aspectRatio = width / height;
        projMat.identity();
        projMat.perspective(fov, aspectRatio, zNear, zFar);
        return projMat;
    }

    public Matrix4f getWorldMatrix(Vector3f offset, Vector3f rotate, float scale) {
        worldMat.identity().translate(offset).
                rotateX((float)Math.toRadians(rotate.x)).
                rotateY((float)Math.toRadians(rotate.y)).
                rotateZ((float)Math.toRadians(rotate.z)).
                scale(scale);
        return worldMat;
    }
}
