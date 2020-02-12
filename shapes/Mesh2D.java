package com.program.shapes;

import org.lwjgl.opengl.*;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Mesh2D {
    private Vertex[] vertices;
    private int[] indices;
    private int VAO, PBO, IBO;

    public Mesh2D(Vertex[] vertices, int[] indices) {
        this.vertices = vertices;
        this.indices = indices;
        VAO = generateVAO();
        PBO = generatePBO();
        IBO = generateIBO();
    }

    private int generateVAO() {
        int vao = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vao);

        return vao;
    }

    private int generatePBO() {
        FloatBuffer positionBuffer = MemoryUtil.memAllocFloat(vertices.length * 3);
        float[] positionData = new float[this.vertices.length * 3];
        for (int i = 0; i < this.vertices.length; i++) {
            positionData[i * 3] = this.vertices[i].getPosition().x();
            positionData[i * 3 + 1] = this.vertices[i].getPosition().y();
            positionData[i * 3 + 2] = this.vertices[i].getPosition().z();
        }
        positionBuffer.put(positionData).flip();

        int pbo = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, pbo);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, positionBuffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

        return pbo;
    }

    private int generateIBO() {
        IntBuffer indicesBuffer = MemoryUtil.memAllocInt(this.indices.length);
        indicesBuffer.put(this.indices).flip();
        int ibo = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, ibo);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);

        return ibo;
    }

    public Vertex[] getVertices() {
        return this.vertices;
    }

    public int[] getIndices() {
        return this.indices;
    }

    public int getVAO() {
        return this.VAO;
    }

    public int getPBO() {
        return this.PBO;
    }

    public int getIBO() {
        return this.IBO;
    }
}
