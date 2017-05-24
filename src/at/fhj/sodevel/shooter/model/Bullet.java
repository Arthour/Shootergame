package at.fhj.sodevel.shooter.model;

import at.fhj.sodevel.shooter.view.GameWorld;

import java.awt.*;

public class Bullet extends NonControllableObjects {
    public Bullet(int x, int y, GameWorld world) {
        position = new Point(x, y);
        this.world = world;
    }

    public Bullet(Point position, GameWorld world) {
        this.position = position;
        this.world = world;
    }
}
