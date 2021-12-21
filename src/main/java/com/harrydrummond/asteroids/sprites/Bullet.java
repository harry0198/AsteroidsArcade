package com.harrydrummond.asteroids.sprites;

import com.harrydrummond.asteroids.components.Collidable;
import com.harrydrummond.asteroids.components.Destroyable;
import com.harrydrummond.asteroids.components.Projectile;
import com.harrydrummond.asteroids.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.List;

public class Bullet extends PolyBasedSprite implements Projectile, Collidable, Destroyable {

    public Bullet(Point2D pos) {
        super(new Point2D(0,0), new Point2D(1,0));
        this.pos = pos;
        shape.setStroke(Color.WHITE);
        shape.setStrokeWidth(2);
    }

    @Override
    public void moveTo(Point2D point) {
        if (!isWithinBounds(point)) {
            setActive(false);
            return;
        }
        super.moveTo(point);
    }

    @Override
    public void destroy() {
        setActive(false);
    }

    @Override
    public List<Sprite> playDestroyAnimation() {
        return List.of();
    }
}