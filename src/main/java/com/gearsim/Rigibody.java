package com.gearsim;

import com.gearsim.util.Vector;

public class Rigibody {
    private Vector position;
    private Vector velocity;
    private Vector acceleration;
    private Shape shape;

    public Rigibody(Vector position, Shape shape, double gravity) {
        this.position = position;
        this.velocity = new Vector(0, 0);
        this.acceleration = new Vector(-gravity, 0);
        this.shape = shape;
    }

    private void update() {
        this.velocity.setX(this.velocity.X() + (this.acceleration.X()));
    }

    public void applyForce(Vector force) {
    }
}
