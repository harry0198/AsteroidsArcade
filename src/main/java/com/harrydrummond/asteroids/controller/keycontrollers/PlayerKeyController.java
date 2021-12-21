package com.harrydrummond.asteroids.controller.keycontrollers;

import com.harrydrummond.asteroids.controller.GameController;
import com.harrydrummond.asteroids.controller.GameInfo;
import com.harrydrummond.asteroids.geometry.Point2D;
import com.harrydrummond.asteroids.sprites.Bullet;
import com.harrydrummond.asteroids.sprites.PlayerSprite;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.*;

public class PlayerKeyController extends KeyController {

    private final List<KeyCode> keyCodes = Collections.synchronizedList(new ArrayList<>());
    private boolean playerIsInHyperSpace = false;

    public PlayerKeyController(final GameController gameController) {
        super(gameController);
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
        GameController gameController = getGameController();
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
                case ENTER:
                    if (playerIsInHyperSpace) break;
                    playerIsInHyperSpace = true;
                    Random random = new Random();
                    PlayerSprite playerSprite = gameController.getPlayerSprite();
                    playerSprite.setVisible(false);
                    gameController.getPlayerSprite().moveTo(new Point2D(random.nextInt(GameInfo.WINDOW_WIDTH), random.nextInt(GameInfo.WINDOW_HEIGHT)));
                    //TODO delegate?
                    TimerTask task = new TimerTask() {
                        @Override
                        public void run() {
                            playerIsInHyperSpace = false;
                            playerSprite.setVisible(true);
                        }
                    };
                    Timer timer = new Timer();
                    timer.schedule(task, 500);
                default:
            }
        }
    }
}