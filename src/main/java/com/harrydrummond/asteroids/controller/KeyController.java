package com.harrydrummond.asteroids.controller;

import com.harrydrummond.asteroids.sprites.Bullet;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class KeyController {

    private final GameController gameController;
    private final List<KeyCode> keyCodes = Collections.synchronizedList(new ArrayList<>());

    public KeyController(final GameController gameController) {
        this.gameController = gameController;
    }

    public synchronized void handlePressed(KeyEvent keyEvent) {
        KeyCode keyCode = keyEvent.getCode();
        // This can be fired multiple times before it is released
        if (!keyCodes.contains(keyCode)) {
            keyCodes.add(keyCode);
        }
    }

    public synchronized void handleReleased(KeyEvent keyEvent) {
        KeyCode keyCode = keyEvent.getCode();
        // This can be fired multiple times before it is released
        keyCodes.remove(keyCode);
    }

    public synchronized void handleActiveKeys() {
        for (KeyCode keyCode : keyCodes) {
            switch (keyCode) {
                case UP:
                    gameController.getPlayerSprite().thrust(gameController.getInterpolation());
                    break;
                case LEFT:
                    gameController.getPlayerSprite().rotateLeft();
                    break;
                case RIGHT:
                    gameController.getPlayerSprite().rotateRight();
                    break;
                case SPACE:
                    Bullet bullet = gameController.getPlayerSprite().fire();
                    if (bullet != null) {
                        gameController.addSprite(bullet);
                    }
                    break;
                default:
            }
        }
    }
}