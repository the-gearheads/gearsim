package com.gearsim;

import com.gearsim.util.Vector;

public class Rigidbody {
    private Vector position;
    private Vector velocity;
    private Vector acceleration;
    private Shape shape;

    public Rigidbody(Vector position, Shape shape, double gravity) {
        this.position = position;
        this.velocity = new Vector(0, 0);
        this.acceleration = new Vector(-gravity, 0.01);
        this.shape = shape;
    }

    private void updateShape() {
        this.shape.setPosition(this.position.X(), this.position.Y());
    }

    public void update(double time) {
        this.velocity.setX(this.velocity.X() + (this.acceleration.X() * time));
        this.position.setX(this.position.X() + (this.velocity.X() * time));
        System.out.println(this.position.X());
        updateShape();
    }

    public void applyForce(Vector force) {

    }

    public Shape getShape() {
        return shape;
    }
}
