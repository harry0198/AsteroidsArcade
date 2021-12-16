package com.harrydrummond.asteroids.game;


public abstract class GameLoop {

    protected GameStatus gameStatus;
    protected int fps;

    private Thread gameThread;

    public GameLoop() {
        gameStatus = GameStatus.STOPPED;
    }

    public void run() {
        gameStatus = GameStatus.RUNNING;
        gameThread = new Thread(this::processGameLoop);
        gameThread.start();
    }

    public void stop() {
        gameStatus = GameStatus.STOPPED;
    }

    public boolean isGameRunning() {
        return gameStatus == GameStatus.RUNNING;
    }

    protected abstract void processGameLoop();
}