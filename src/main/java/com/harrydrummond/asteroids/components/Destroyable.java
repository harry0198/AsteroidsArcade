package com.harrydrummond.asteroids.components;

import com.harrydrummond.asteroids.sprites.Sprite;

import java.util.List;

public interface Destroyable {

    void destroy();
    List<Sprite> playDestroyAnimation();
}