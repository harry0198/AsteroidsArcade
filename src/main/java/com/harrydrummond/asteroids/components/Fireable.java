package com.harrydrummond.asteroids.components;

import com.harrydrummond.asteroids.sprites.Bullet;

public interface Fireable {

    // Bullets per second
    int getBPS();
    void setBPS(int bps);
    Bullet fire();
}