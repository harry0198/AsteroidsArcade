package com.harrydrummond.asteroids.sprites;

import javafx.geometry.Point2D;
import javafx.scene.Node;

import java.util.Collection;

public abstract class Sprite {

    protected Point2D minBound, maxBound;
    protected double xPos, yPos;
    protected int rotation;
    protected double dx,dy;
    protected boolean active = true;

    public Sprite() {
        this.xPos = 0;
        this.yPos = 0;
        this.dx = 0;
        this.dy = 0;
        this.rotation = 0;
        this.minBound = new Point2D(0,0);
        this.maxBound = new Point2D(500,500);
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    /**
     * Moves sprite by dx and dy values and then calls the update method
     */
    public void move() {
        move(dx,dy);
    }

    /**
     * Moves sprite by x and y increment
     * @param x Amount to move by
     * @param y Amount to move by
     */
    public void move(double x, double y) {
        moveTo(xPos+x, yPos+y);
    }

    /**
     * Moves sprite to exact location
     * @param x X Location to move to
     * @param y Y Location to move to
     */
    public void moveTo(double x, double y) {
        if (x < minBound.getX()) {
            xPos = maxBound.getX();
        } else if (y < minBound.getY()) {
            yPos = maxBound.getY();
        } else if (x > maxBound.getX()) {
            xPos = minBound.getX();
        } else if (y > maxBound.getY()) {
            yPos = minBound.getY();
        } else {
            xPos = x;
            yPos = y;
        }
    }

    public double getxPos() {
        return xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public int getRotation() {
        return rotation;
    }

    public void rotateLeft() {
        rotation -= 5;
        update();
    }

    public void rotateRight() {
        rotation += 5;
        update();
    }

    public void setBounds(Point2D min, Point2D max) {
        this.minBound = min;
        this.maxBound = max;
    }

    public boolean isWithinBounds(double x, double y) {
        return !(x < minBound.getX()) && !(y < minBound.getY()) && !(x > maxBound.getX()) && !(y > maxBound.getY());
    }

    public abstract Node getNode();
    public abstract void update();
}