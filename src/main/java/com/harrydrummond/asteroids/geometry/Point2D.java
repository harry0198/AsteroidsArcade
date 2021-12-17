package com.harrydrummond.asteroids.geometry;

import java.util.List;

public class Point2D implements Cloneable {

    private double x,y;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void add(double x, double y) {
        this.x += x;
        this.y += y;
    }

    public void add(Point2D point2D) {
        add(point2D.getX(), point2D.getY());
    }

    public void subtract(double x, double y) {
        this.x -= x;
        this.y -= y;
    }

    public void subtract(Point2D point2D) {
        subtract(point2D.getX(), point2D.getY());
    }

    public void multiply(double x, double y) {
        this.x *= x;
        this.y *= y;
    }

    public void multiply(Point2D point2D) {
        multiply(point2D.getX(), point2D.getY());
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public static Point2D minOf(List<Point2D> point2Ds) {
        double minX = Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;
        for (Point2D point2D : point2Ds) {
            if (point2D.getX() < minX) {
                minX = point2D.getX();
            }
            if (point2D.getY() < minY) {
                minY = point2D.getY();
            }
        }

        return new Point2D(minX, minY);
    }

    public static Point2D maxOf(List<Point2D> point2Ds) {
        double maxX = Double.MIN_VALUE;
        double maxY = Double.MIN_VALUE;
        for (Point2D point2D : point2Ds) {
            if (point2D.getX() > maxX) {
                maxX = point2D.getX();
            }
            if (point2D.getY() > maxY) {
                maxY = point2D.getY();
            }
        }

        return new Point2D(maxX, maxY);
    }

    @Override
    public Point2D clone() {
        try {
            Point2D clone = (Point2D) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}