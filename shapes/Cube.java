package com.program.shapes;

import com.program.mesh.Color;
import com.program.mesh.Mesh;
import com.program.mesh.Tri;
import com.program.mesh.Vertex;

public class Cube extends Mesh {
    private Vertex[] vertices = new Vertex[4];
    private Tri[] indices = new Tri[2];
    private Color[] color = new Color[4];

    public Cube() {
        construct();
    }

    private void construct() {
        vertices = new Vertex[]{
                new Vertex(-0.5f, 0.5f, 0.5f),
                new Vertex(-0.5f, -0.5f, 0.5f),
                new Vertex(0.5f, -0.5f, 0.5f),
                new Vertex(0.5f, 0.5f, 0.5f),
                new Vertex(-0.5f, 0.5f, -0.5f),
                new Vertex(0.5f, 0.5f, -0.5f),
                new Vertex(-0.5f, -0.5f, -0.5f),
                new Vertex(0.5f, -0.5f, -0.5f)

        };
        indices = new Tri[] {
                new Tri(0, 1, 3),
                new Tri(3, 1, 2),
                new Tri(4, 0, 3),
                new Tri(5, 4, 3),
                new Tri(3, 2, 7),
                new Tri(5, 3, 7),
                new Tri(6, 1, 0),
                new Tri(6, 0, 4),
                new Tri(2, 1, 6),
                new Tri(2, 6, 7),
                new Tri(7, 6, 4),
                new Tri(7, 4, 5)
        };
        color = new Color[] {
                new Color(0, 0, 0, 255),
                new Color(255, 0, 0, 255),
                new Color(0, 255, 0, 255),
                new Color(255, 255, 0, 255),
                new Color(0, 0, 255, 255),
                new Color(255, 0, 255, 255),
                new Color(0, 255, 255, 255),
                new Color(255, 255, 255, 255)
        };
    }

    public void update() {
        this.setVertices(this.vertices);
        this.setIndices(this.indices);
        this.setColor(this.color);
        this.constructBuffers();
    }
}
