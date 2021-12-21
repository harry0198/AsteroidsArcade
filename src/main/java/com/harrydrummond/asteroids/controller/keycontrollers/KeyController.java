package com.harrydrummond.asteroids.controller.keycontrollers;

import com.harrydrummond.asteroids.controller.GameController;
import javafx.scene.input.KeyEvent;

public abstract class KeyController {

    private final GameController gameController;

    public KeyController(final GameController gameController) {
        this.gameController = gameController;
    }

    public GameController getGameController() {
        return gameController;
    }

    public abstract void handlePressed(KeyEvent keyEvent);
    public abstract void handleReleased(KeyEvent keyEvent);
    public abstract void handleActiveKeys();
}