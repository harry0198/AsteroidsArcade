package com.harrydrummond.asteroids.sprites;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Bullet extends LineBasedSprite {

    private boolean active = true;

    public Bullet(double x, double y) {
        super();
        this.xPos = x;
        this.yPos = y;
        update();
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public void moveTo(double x, double y) {
        if (!isWithinBounds(x,y)) {
            setActive(false);
            return;
        }
        super.moveTo(x, y);
    }

    @Override
    public void update() {
        shape = new Line(xPos,yPos,xPos,yPos);
        shape.setStroke(Color.WHITE);
        shape.setStrokeWidth(2);
        shape.setRotate(rotation);
    }
}