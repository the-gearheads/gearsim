package com.program.renderer;

import com.program.shader.Shader;
import com.program.shapes.Mesh2D;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

public class Renderer {
    private Shader shader;

    public void initShaders() throws Exception {
        shader = new Shader();
        shader.createVertexShader(Shader.loadShaderFile("./vertex.vs"));
        shader.createFragmentShader(Shader.loadShaderFile("./fragment.fs"));
        shader.linkProgram();
    }

    public void renderMesh(Mesh2D mesh) {
        shader.bindProgram();
        GL30.glBindVertexArray(mesh.getVAO());
        GL30.glEnableVertexAttribArray(0);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, mesh.getIBO());
        GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.getIndices().length, GL11.GL_UNSIGNED_INT, 0);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
        GL30.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
        shader.unbindProgram();
    }

    public void wrapUpShaders() {
        if (shader != null) {
            shader.removeProgram();
        }
    }
}
