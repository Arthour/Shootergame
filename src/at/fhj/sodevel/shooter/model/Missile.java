package at.fhj.sodevel.shooter.model;

import at.fhj.sodevel.shooter.view.GameWorld;

import java.awt.*;

public class Missile extends NonControllableObjects {
    public Missile(int x, int y, GameWorld world) {
        position = new Point(x, y);
        this.world = world;
    }

    public Missile(Point position, GameWorld world) {
        this.position = position;
    }
}
