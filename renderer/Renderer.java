package com.program.renderer;

import com.program.engine.Window;
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
    private Matrix4f projection;

    public void initProjection(Window window) throws Exception {
        float aspectRatio = (float) window.getWidth() / window.getHeight();
        projection = new Matrix4f().perspective(FOV, aspectRatio, Z_NEAR, Z_FAR);
        shader.createUniform("projMat");
    }

    public void initShaders() throws Exception {
        shader = new Shader();
        shader.createVertexShader(Shader.loadShaderFile("/home/richards/FinalProject/src/com/program/shader/vertex.vs"));
        shader.createFragmentShader(Shader.loadShaderFile("/home/richards/FinalProject/src/com/program/shader/fragment.fs"));
        shader.linkProgram();
    }

    public void renderMesh(Mesh2D mesh) {
        shader.bindProgram();
        renderProjection();
        GL30.glBindVertexArray(mesh.getVAO());
        GL30.glEnableVertexAttribArray(0);
        GL30.glEnableVertexAttribArray(1);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, mesh.getIBO());
        GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.getIndices().length, GL11.GL_UNSIGNED_INT, 0);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
        GL30.glDisableVertexAttribArray(0);
        GL30.glDisableVertexAttribArray(1);
        GL30.glBindVertexArray(0);
        shader.unbindProgram();
    }

    public void renderProjection() {
        shader.setUniform("projMat", projection);
    }

    public void wrapUpShaders() {
        if (shader != null) {
            shader.removeProgram();
        }
    }
}
