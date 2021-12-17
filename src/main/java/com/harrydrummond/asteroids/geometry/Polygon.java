package com.harrydrummond.asteroids.geometry;

import java.util.ArrayList;
import java.util.List;

public class Polygon extends javafx.scene.shape.Polygon {

    public Polygon(Point2D... points) {
        this(List.of(points));
    }

    public Polygon(List<Point2D> points) {
        super(0);

        // Annoyingly javafx polygon has an inconsistency between constructor points being initialised
        // vs adding points. (Constructor requires array, adding points requires list).
        alterPointPositions(points);
    }

    public void alterPointPositions(List<Point2D> points) {
        getPoints().clear();
        getPoints().addAll(convertPointsToDoubleList(points));
    }

    private static List<Double> convertPointsToDoubleList(final List<Point2D> points) {
        List<Double> dArr = new ArrayList<>(points.size()*2);
        for (Point2D point : points) {
            dArr.add(point.getX());
            dArr.add(point.getY());
        }
        return dArr;
    }
}