package com.harrydrummond.asteroids.sprites;

import com.harrydrummond.asteroids.geometry.Point2D;
import com.harrydrummond.asteroids.geometry.Polygon;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.List;

public abstract class PolyBasedSprite extends Sprite {

    protected final Polygon shape;
    protected double scale = 1;

    public PolyBasedSprite(Point2D... points) {
        this(Arrays.asList(points));
    }

    public PolyBasedSprite(List<Point2D> points) {
        super(points);
        shape = createPolygon();
    }

    public void setScale(double scale) {
        this.scale = scale;
        for (Point2D point : points) {
            point.multiply(scale, scale);
        }
    }

    public double getScale() {
        return this.scale;
    }


    @Override
    public Node getNode() {
        return shape;
    }

    protected Polygon createPolygon() {
        Polygon polygon = new Polygon(points);
        polygon.setFill(Color.WHITE);
        return polygon;
    }

    @Override
    public void draw(Pane pane) {
        if (isVisible()) pane.getChildren().add(getNode());
    }

    @Override
    public void update() {
        // Updates position
        shape.alterPointPositions(getAbsolutePoints());
        shape.setRotate(rotation);
    }
}