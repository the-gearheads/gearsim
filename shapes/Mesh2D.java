package com.program.shapes;

import org.lwjgl.opengl.*;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Mesh2D {
    private Vertex[] vertices;
    private int[] indices;
    private Color[] colors;
    private int VAO, VBO, IBO, COLOR;

    public Mesh2D(Vertex[] vertices, int[] indices, Color[] colors) {
        this.vertices = vertices;
        this.indices = indices;
        this.colors = colors;
        VAO = generateVAO();
        VBO = generateVBO();
        IBO = generateIBO();
        COLOR = generateColorVBO();
    }

    private int generateVAO() {
        int vao = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vao);

        return vao;
    }

    private int generateVBO() {
        FloatBuffer positionBuffer = MemoryUtil.memAllocFloat(vertices.length * 3);
        float[] positionData = new float[this.vertices.length * 3];
        for (int i = 0; i < this.vertices.length; i++) {
            positionData[i * 3] = this.vertices[i].getPosition().x();
            positionData[i * 3 + 1] = this.vertices[i].getPosition().y();
            positionData[i * 3 + 2] = this.vertices[i].getPosition().z();
        }
        positionBuffer.put(positionData).flip();

        int vbo = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, positionBuffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

        return vbo;
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

    private int generateColorVBO() {
        FloatBuffer colorsBuffer = MemoryUtil.memAllocFloat(this.colors.length * 4);
        float[] colorRaw = new float[this.colors.length * 4];
        for (int i = 0; i < this.colors.length; i++) {
                colorRaw[i * 4] = this.colors[i].getR();
                colorRaw[i * 4 + 1] = this.colors[i].getG();
                colorRaw[i * 4 + 2] = this.colors[i].getB();
                colorRaw[i * 4 + 3] = this.colors[i].getA();
        }
        colorsBuffer.put(colorRaw).flip();

        int color = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, color);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, colorsBuffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(1, 4, GL11.GL_FLOAT, false, 0,0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

        return color;
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

    public int getVBO() {
        return this.VBO;
    }

    public int getIBO() {
        return this.IBO;
    }
}
