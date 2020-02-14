package com.program.shapes;

import com.program.math.Vector3f;

public class Rect extends Mesh2D {
    private float x, y, z, width, height;
    private Vertex[] vertices = new Vertex[4];
    private int[] indices = new int[6];

    public Rect(float x, float y, float z, float width, float height) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.width = width;
        this.height = height;
    }

    public void construct() {
        vertices = new Vertex[]{
                new Vertex(x, y, z),
                new Vertex(x + width, y, z),
                new Vertex(x + width, y + height, z),
                new Vertex(x, y + height, z)
        };
        indices = new int[] {
                0, 1, 2,
                0, 2, 3
        };
    }

    public void updateMesh() {
        this.setVertices(this.vertices);
        this.setIndices(this.indices);
    }
}
