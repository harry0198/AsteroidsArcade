package com.harrydrummond.asteroids.sprites;

import com.harrydrummond.asteroids.geometry.Point2D;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.List;


public abstract class Sprite {

    protected final List<Point2D> points;
    protected Bounds bounds;
    protected Point2D pos;
    protected int rotation;
    // Velocity vector
    protected Point2D dPos;
    protected boolean active = true;

    public Sprite(List<Point2D> points) {
        this.points = points;
        this.pos = new Point2D(0,0);
        this.dPos = new Point2D(0,0);
        this.rotation = 0;
        bounds = new BoundingBox(0,0,500,500);
    }

    /**
     * Gets the points of the sprite relative to x,y = 0,0
     * @return Points of sprite relative to x,y = 0,0
     */
    public List<Point2D> getRelativePoints() {
        return this.points;
    }

    /**
     * Gets the absolute position of the points respective to sprite X Position and Y Position
     * @return List of absolute sprite position points
     */
    public List<Point2D> getAbsolutePoints() {
        List<Point2D> points = new ArrayList<>(this.points.size());
        for (Point2D point : this.points) {
            points.add(new Point2D(point.getX() + pos.getX(), point.getY() + pos.getY()));
        }
        return points;
    }

    public Area getArea() {
        java.awt.Polygon polygon = new java.awt.Polygon(
                getAbsolutePoints().stream().mapToInt(x -> (int) x.getX()).toArray(),
                getAbsolutePoints().stream().mapToInt(x -> (int) x.getY()).toArray(),
                points.size());
        return new Area(polygon);
    }

    public boolean intersects(Sprite sprite) {

        if (!intersectsBoundingBox(sprite)) return false;
        Area area = getArea();
        Area compareArea = sprite.getArea();
        area.intersect(compareArea);
        return !compareArea.isEmpty();

    }

    /**
     * Checks if sprite intersects bounding box.
     * Less computationally expensive as intersects but less accurate as uses bounding box of sprite
     * @param sprite Sprite to check
     * @return If sprite intersects this sprites bounding box
     */
    private boolean intersectsBoundingBox(Sprite sprite) {
        List<Point2D> points = getAbsolutePoints();
        List<Point2D> rPoints = sprite.getAbsolutePoints();
        Point2D lowerBound = Point2D.minOf(points);
        Point2D upperBound = Point2D.maxOf(points);
        Point2D rLowerBound = Point2D.minOf(rPoints);
        Point2D rUpperBound = Point2D.maxOf(rPoints);
        if (rUpperBound.getX() < lowerBound.getX()) return false;
        if (rUpperBound.getY() < lowerBound.getY()) return false;

        if (rLowerBound.getX() > upperBound.getX()) return false;
        if (rLowerBound.getY() > upperBound.getY()) return false;

        return true;

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
        move(new Point2D(dPos.getX(), dPos.getY()));
    }


    public void move(Point2D point) {
        point.add(pos);
        moveTo(point);
    }


    public void moveTo(Point2D point) {

        if (point.getX() < bounds.getMinX()) {
            pos.add(bounds.getMaxX(),0);
        } else if (point.getY() < bounds.getMinY()) {
            pos.add(0,bounds.getMaxY());
        } else if (point.getX() > bounds.getMaxX()) {
            pos.subtract(bounds.getMaxX(),0);
        } else if (point.getY() > bounds.getMaxY()) {
            pos.subtract(0,bounds.getMaxY());
        } else {
            pos = point;
        }
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

    public void setSpeed(Point2D dPos) {
        this.dPos = dPos;
    }

    public void setBounds(Bounds bounds) {
        this.bounds = bounds;
    }

    public boolean isWithinBounds(Point2D point) {
        return bounds.contains(point.getX(), point.getY());
    }

    public abstract void draw(Pane pane);
    public abstract Node getNode();
    public abstract void update();
}