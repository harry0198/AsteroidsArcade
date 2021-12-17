package com.harrydrummond.asteroids.sprites;

import com.harrydrummond.asteroids.geometry.Point2D;
import javafx.scene.paint.Color;

public class Bullet extends PolyBasedSprite {

    public Bullet(Point2D pos) {
        super(new Point2D(0,0), new Point2D(1,0));
        this.pos = pos;
        shape.setStroke(Color.WHITE);
        shape.setStrokeWidth(2);
    }

    @Override
    public void moveTo(Point2D point) {
        if (!isWithinBounds(point)) {
            setActive(false);
            return;
        }
        super.moveTo(point);
    }
}