package com.harrydrummond.asteroids.controller;

import com.harrydrummond.asteroids.geometry.Point2D;
import com.harrydrummond.asteroids.sprites.*;
import javafx.application.Platform;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class GameController {

    private final KeyController keyController;

    private float interpolation = 0f;
    private final List<Sprite> elements = Collections.synchronizedList(new ArrayList<>());
    private final Pane pane;
    private final PlayerSprite playerSprite;

    public GameController(Pane pane) {
        this.pane = pane;
        this.playerSprite = new PlayerSprite();
        //playerSprite.setBounds(new BoundingBox(0,0,500,500));
        elements.add(playerSprite);

        Asteroid asteroid = new Asteroid();
        asteroid.setSpeed(new Point2D(3, 2));

        elements.add(asteroid);
        // Requests pane to be "clicked" so it's active.
        pane.requestFocus();

        // Delegates key controls to keycontroller
        this.keyController = new KeyController(this);
        pane.setOnKeyPressed(keyController::handlePressed);
        pane.setOnKeyReleased(keyController::handleReleased);
    }

    public synchronized void addSprite(Sprite sprite) {
        elements.add(sprite);
    }

    public PlayerSprite getPlayerSprite() {
        return playerSprite;
    }

    public synchronized void update() {
        this.keyController.handleActiveKeys();
        synchronized (elements) {
            elements.removeIf(el -> !el.isActive());
            for (Sprite element : elements) {
                element.move();
                element.update();

                for (Sprite sprite : elements) {
                    boolean intersects = sprite.intersects(element);
                    if (!intersects) continue;
                }
            }
        }
    }

    public void render() {
        pane.getChildren().clear();
        synchronized (elements) {
            for (Sprite element : elements) {
                element.draw(pane);
            }
        }
    }

    public void setInterpolation(float interpolation) {
        this.interpolation = interpolation;
    }

    public float getInterpolation() {
        return interpolation;
    }
}