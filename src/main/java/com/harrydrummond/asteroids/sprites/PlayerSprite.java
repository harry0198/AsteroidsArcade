package com.harrydrummond.asteroids.sprites;

import com.harrydrummond.asteroids.components.Collidable;
import com.harrydrummond.asteroids.components.Destroyable;
import com.harrydrummond.asteroids.components.Fireable;
import com.harrydrummond.asteroids.geometry.Point2D;

import java.util.List;
import java.util.Random;

public class PlayerSprite extends PolyBasedSprite implements Fireable, Destroyable, Collidable {

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
    public void destroy() {
        setActive(false);
    }

    @Override
    public List<Sprite> playDestroyAnimation() {
        Random random = new Random();
        Sprite animate1 = new TimeoutSprite(List.of(new Point2D(0,0),new Point2D(-10,10)), 1000);
        animate1.setSpeed(new Point2D(-0.4,0.4));
        animate1.moveTo(this.pos.clone());
        animate1.setRotation(random.nextInt(360));
        Sprite animate2 = new TimeoutSprite(List.of(new Point2D(0,0),new Point2D(-10,10)), 1200);
        animate2.setSpeed(new Point2D(1.0,1.2));
        animate2.setRotation(random.nextInt(360));
        animate2.moveTo(this.pos.clone());
        Sprite animate3 = new TimeoutSprite(List.of(new Point2D(0,0),new Point2D(-10,10)), 800);
        animate3.setSpeed(new Point2D(0.9,-0.9));
        animate3.setRotation(random.nextInt(360));
        animate3.moveTo(this.pos.clone());
        return List.of(animate1,animate2,animate3);
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