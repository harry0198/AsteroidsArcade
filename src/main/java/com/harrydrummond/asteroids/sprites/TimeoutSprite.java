package com.harrydrummond.asteroids.sprites;


import com.harrydrummond.asteroids.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.List;

public class TimeoutSprite extends PolyBasedSprite {

    private final long timestamp;
    private final long timeoutMs; // 1 sec

    public TimeoutSprite(List<Point2D> points, int timeoutMs) {
        super(points);
        this.timeoutMs = timeoutMs;
        this.timestamp = System.currentTimeMillis();
        shape.setStroke(Color.WHITE);
        shape.setStrokeWidth(2);
    }

    public TimeoutSprite(List<Point2D> points) {
        this(points, 1000);
    }

    @Override
    public void update() {
        if (timestamp + timeoutMs < System.currentTimeMillis()) {
            setActive(false);
            return;
        }
        super.update();
    }
}