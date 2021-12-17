package com.harrydrummond.asteroids.sprites;


import com.harrydrummond.asteroids.geometry.Point2D;
import javafx.scene.paint.Color;

public class Asteroid extends PolyBasedSprite {

    public Asteroid() {
        super(
                new Point2D(-2,4),
                new Point2D(1,4),
                new Point2D(1,3),
                new Point2D(3,2),
                new Point2D(2,1),
                new Point2D(2,-1),
                new Point2D(1,-2),
                new Point2D(-1,-3),
                new Point2D(-3,-2),
                new Point2D(-4,1)
        );
        shape.setFill(null);
        shape.setStrokeWidth(2);
        shape.setStroke(Color.WHITE);
        setScale(10);
    }
}