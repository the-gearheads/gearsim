package com.program.shapes;

public class Rect extends Mesh2D {
    private float x, y, z, width, height;
    private Vertex[] vertices = new Vertex[4];
    private int[] indices = new int[6];
    private Color[] color = new Color[4];

    public Rect(float x, float y, float z, float width, float height) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.width = width;
        this.height = height;
        construct();
    }

    private void construct() {
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
        color = new Color[] {
                new Color(1.0f, 0.0f, 0.0f, 1.0f),
                new Color(1.0f, 0.0f, 1.0f, 1.0f),
                new Color(0.0f, 1.0f, 0.0f, 1.0f),
                new Color(1.0f, 0.0f, 1.0f, 1.0f),
        };

        this.setVertices(this.vertices);
        this.setIndices(this.indices);
        this.setColor(this.color);
        this.constructBuffers();
    }
}
