package com.harrydrummond.asteroids.geometry;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Point2DTest {

    @Test
    public void testClone() {
        Point2D point2D = new Point2D(4,5);
        Point2D pointClone = point2D.clone();

        assertNotEquals(point2D, pointClone);
    }

}