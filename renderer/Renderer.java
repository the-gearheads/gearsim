package com.program.renderer;

import com.program.engine.Window;
import com.program.rigidbodies.RigidbodyRect;
import com.program.shader.Shader;
import com.program.shapes.Mesh2D;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

public class Renderer {
    private Shader shader;

    private final float FOV = (float)Math.toRadians(60.0f);
    private final float Z_NEAR = 0.01f;
    private final float Z_FAR = 1000.f;

    private final Transformations transformations;
    private Matrix4f projection;
    private Matrix4f world;

    public Renderer() {
        transformations = new Transformations();
    }

    public void initUniforms() throws Exception {
        shader.createUniform("projMat");
        shader.createUniform("worldMat");
    }

    public void initProjection(Window window) throws Exception {
        projection = transformations.getProjMatrix(
                this.FOV,
                this.Z_NEAR,
                this.Z_FAR,
                window.getWidth(),
                window.getHeight()
        );
        shader.setUniform("projMat", projection);
    }

    public void initShaders() throws Exception {
        shader = new Shader();
        shader.createVertexShader(Shader.loadShaderFile("/home/richards/FinalProject/src/com/program/shader/vertex.vs"));
        shader.createFragmentShader(Shader.loadShaderFile("/home/richards/FinalProject/src/com/program/shader/fragment.fs"));
        shader.linkProgram();
    }

    public void renderRidgidbody(RigidbodyRect rect) {
        shader.bindProgram();
        setProjection();

        Matrix4f worldMatrix = transformations.getWorldMatrix(
                rect.getPostition(),
                rect.getRotation(),
                rect.getScale()
        );
        shader.setUniform("worldMat", world);
        bind(rect.getRect());
    }

    private void bind(Mesh2D mesh) {
        GL30.glBindVertexArray(mesh.getVAO());

        GL30.glEnableVertexAttribArray(0);
        GL30.glEnableVertexAttribArray(1);

        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, mesh.getIBO());
        GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.getIndices().length, GL11.GL_UNSIGNED_INT, 0);
    }

    private void unbind() {
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);

        GL30.glDisableVertexAttribArray(0);
        GL30.glDisableVertexAttribArray(1);

        GL30.glBindVertexArray(0);
    }

    private void setProjection() {
        shader.setUniform("projMat", projection);
    }

    private void setWorld() {
        shader.setUniform("worldMat", world);
    }

    public void wrapUp() {
        if (shader != null) {
            shader.removeProgram();
        }
        unbind();
    }
}
