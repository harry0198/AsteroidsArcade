package com.harrydrummond.asteroids.controller;

import com.harrydrummond.asteroids.sprites.PlayerSprite;
import com.harrydrummond.asteroids.sprites.Sprite;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GameController {

    private final KeyController keyController;

    private float interpolation = 0f;
    private final List<Sprite> elements = new ArrayList<>();
    private final Pane pane;
    private final PlayerSprite playerSprite;

    public GameController(Pane pane) {
        this.pane = pane;
        this.playerSprite = new PlayerSprite();
        playerSprite.setBounds(new Point2D(0,0), new Point2D(500,500));
        elements.add(playerSprite);
        // Requests pane to be "clicked" so it's active.
        pane.requestFocus();

        // Delegates key controls to keycontroller
        this.keyController = new KeyController(this);
        pane.setOnKeyPressed(keyController::handlePressed);
        pane.setOnKeyReleased(keyController::handleReleased);
    }

    public void addSprite(Sprite sprite) {
        elements.add(sprite);
    }

    public PlayerSprite getPlayerSprite() {
        return playerSprite;
    }

    public void update() {
        this.keyController.handleActiveKeys();
        elements.removeIf(el -> !el.isActive());
        for (Sprite element : elements) {
            element.move();
            element.update();
        }
    }

    public void render() {
        pane.getChildren().clear();
        pane.getChildren().addAll(elements.stream().map(Sprite::getNode).collect(Collectors.toList()));
    }

    public void setInterpolation(float interpolation) {
        this.interpolation = interpolation;
    }

    public float getInterpolation() {
        return interpolation;
    }
}