package com.harrydrummond.asteroids.sprites;

import javafx.geometry.Point2D;

public class PlayerSprite extends LineBasedSprite implements Fireable {

    public PlayerSprite() {
        super();
        rotation = 0;
        update();
    }



    /**
     * Increments thrust power to movement
     * @param interpolation Interpolation factor to keep renders smooth.
     */
    public void thrust(float interpolation) {
        // todo cap speed?
        dx += Math.sin(Math.toRadians(getRotation())) * 0.4 * interpolation;
        dy += -Math.cos(Math.toRadians(getRotation())) * 0.4 * interpolation;
    }

    @Override
    public Bullet fire() {
        Bullet bullet = new Bullet(xPos, yPos);
        bullet.setBounds(minBound, maxBound);
        bullet.rotation = rotation;
        double tmpx = 0;
        double tmpy = 0;
        tmpx += Math.sin(Math.toRadians(getRotation())) * 3;
        tmpy += -Math.cos(Math.toRadians(getRotation())) * 3;
        bullet.dx = tmpx;
        bullet.dy = tmpy;
        return bullet;
    }

    @Override
    public void update() {
//        polygon = createPolygon(
//                xPos+10,yPos+10,
//                xPos,5+yPos,
//                5+xPos,5+yPos,
//                5+xPos, yPos
//        );
        shape = createPolygon(
                xPos,yPos-10,
                xPos-5,yPos+5,
                xPos,yPos,
                xPos+5,yPos+5
        );
        shape.setRotate(rotation);
    }
}