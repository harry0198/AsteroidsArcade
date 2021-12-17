package com.harrydrummond.asteroids.sprites;

public interface Fireable {

    // Bullets per second
    int getBPS();
    void setBPS(int bps);
    Bullet fire();
}