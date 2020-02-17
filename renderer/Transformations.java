package com.program.renderer;

import com.program.rigidbodies.Rigidbody;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Transformations {
    private Matrix4f camMat;
    private Matrix4f projMat;
    private Matrix4f objectMat;

    public Transformations() {
        camMat = new Matrix4f();
        projMat = new Matrix4f();
        objectMat = new Matrix4f();
    }

    public Matrix4f getCamMatrix(Camera camera) {
        Vector3f cameraPosition = camera.getPosition();
        Vector3f cameraRotation = camera.getRotation();
        camMat.identity();
        camMat.rotate(
                (float) Math.toRadians(cameraRotation.x),
                new Vector3f(1, 0 ,0)
        ).rotate(
                (float) Math.toRadians(cameraRotation.y),
                new Vector3f(0, 1, 0)
        );
        camMat.translate(cameraPosition.x, cameraPosition.y, cameraPosition.z);
        return camMat;
    }

    public Matrix4f getProjMatrix(float fov, float zNear, float zFar, float width, float height) {
        float aspectRatio = width / height;
        projMat.identity();
        projMat.perspective(fov, aspectRatio, zNear, zFar);
        return projMat;
    }

    public Matrix4f getObjectMatrix(Rigidbody object, Matrix4f view) {
        Vector3f objectPositon = object.getPostition();
        Vector3f objectRotation = object.getRotation();
        float scale = object.getScale();
        objectMat.identity().translate(objectPositon).
                rotateX((float)Math.toRadians(objectRotation.x)).
                rotateY((float)Math.toRadians(objectRotation.y)).
                rotateZ((float)Math.toRadians(objectRotation.z)).
                scale(scale);
        Matrix4f _view = new Matrix4f(view);
        return _view.mul(objectMat);
    }
}
