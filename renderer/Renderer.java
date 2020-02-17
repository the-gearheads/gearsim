package com.program.renderer;

import com.program.engine.Window;
import com.program.mesh.Color;
import com.program.rigidbodies.Rigidbody;
import com.program.shader.Shader;
import com.program.mesh.Mesh;
import lighting.Sunlight;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

public class Renderer {
    private Shader shader;

    private final float FOV = (float)Math.toRadians(60.0f);
    private final float Z_NEAR = 0.01f;
    private final float Z_FAR = 1000.f;

    private final Window window;
    private final Transformations transformations;

    public Renderer(Window window) {
        this.window = window;
        this.transformations = new Transformations();
    }

    public void initUniforms() throws Exception {
        shader.createUniform("projMat");
        shader.createUniform("objMat");
    }

    public void initShaders() throws Exception {
        shader = new Shader();
        shader.createVertexShader(Shader.loadShaderFile("/home/richards/FinalProject/src/com/program/shader/vertex.vs"));
        shader.createFragmentShader(Shader.loadShaderFile("/home/richards/FinalProject/src/com/program/shader/fragment.fs"));
        shader.linkProgram();
    }

    public void render(Rigidbody[] rigidbodies, Camera camera) {
        shader.bindProgram();

        Matrix4f projMatrix = transformations.getProjMatrix(
                this.FOV,
                this.Z_NEAR,
                this.Z_FAR,
                this.window.getWidth(),
                this.window.getHeight()
        );
        shader.setUniform("projMat", projMatrix);

        Matrix4f camMatrix = transformations.getCamMatrix(camera);

        for (Rigidbody rigidbody : rigidbodies) {
            Matrix4f objMatrix = transformations.getObjectMatrix(rigidbody, camMatrix);
            shader.setUniform("objMat", objMatrix);

            rigidbody.getMesh().update();
            bind(rigidbody.getMesh());
        }

        shader.unbindProgram();
    }

    private void bind(Mesh mesh) {
        GL30.glBindVertexArray(mesh.getVAO());

        GL30.glEnableVertexAttribArray(0);
        GL30.glEnableVertexAttribArray(1);

        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, mesh.getIBO());
        GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.getIndices().length * 3, GL11.GL_UNSIGNED_INT, 0);
    }

    private void unbind() {
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);

        GL30.glDisableVertexAttribArray(0);
        GL30.glDisableVertexAttribArray(1);

        GL30.glBindVertexArray(0);
    }

    public void wrapUp() {
        if (shader != null) {
            shader.removeProgram();
        }
        unbind();
    }
}
