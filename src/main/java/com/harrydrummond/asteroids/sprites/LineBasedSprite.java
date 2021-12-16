package com.harrydrummond.asteroids.sprites;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public abstract class LineBasedSprite extends Sprite {

    protected Shape shape;

    public LineBasedSprite() {
        super();
        shape = createPolygon(xPos,yPos,
                xPos,5+yPos,
                5+xPos,5+yPos,
                5+xPos,yPos);
    }

    @Override
    public Node getNode() {
        return shape;
    }

    protected Polygon createPolygon(double... points) {
        Polygon polygon = new Polygon(points);
        polygon.setFill(Color.WHITE);
        return polygon;
    }

    @Override
    public void update() {
        // Default polygon of square
        //TODO maybe update points instead of making entire new ting
        shape = createPolygon(xPos,yPos,
                xPos,5+yPos,
                5+xPos,5+yPos,
                5+xPos,yPos);
        shape.setRotate(rotation);
    }
}