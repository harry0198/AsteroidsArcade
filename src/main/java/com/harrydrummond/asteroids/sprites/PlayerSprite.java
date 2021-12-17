package com.harrydrummond.asteroids.sprites;

import com.harrydrummond.asteroids.geometry.Point2D;

public class PlayerSprite extends PolyBasedSprite implements Fireable {

    // bullets per second
    private int bps = 4;
    private long lastFiredTimeStamp = 0;

    public PlayerSprite() {
        super(
                new Point2D(0,-10),
                new Point2D(-5,5),
                new Point2D(0,0),
                new Point2D(5,5)
        );
    }


    /**
     * Increments thrust power to movement
     * @param interpolation Interpolation factor to keep renders smooth.
     */
    public void thrust(float interpolation) {
        // todo cap speed?
        dPos.add(
                Math.sin(Math.toRadians(getRotation())) * 0.4 * interpolation,
                -Math.cos(Math.toRadians(getRotation())) * 0.4 * interpolation
        );
    }

    @Override
    public Bullet fire() {

        if (this.lastFiredTimeStamp + (1000/getBPS()) > System.currentTimeMillis()) return null;

        this.lastFiredTimeStamp = System.currentTimeMillis();

        Bullet bullet = new Bullet(pos);
        bullet.setBounds(bounds);
        bullet.rotation = rotation;
        bullet.dPos.add(
                Math.sin(Math.toRadians(getRotation())) * 3,
                -Math.cos(Math.toRadians(getRotation())) * 3
        );
        return bullet;
    }

    @Override
    public int getBPS() {
        return bps;
    }

    @Override
    public void setBPS(int bps) {
        this.bps = bps;
    }
}