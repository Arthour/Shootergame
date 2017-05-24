package at.fhj.sodevel.shooter.model;

import at.fhj.sodevel.shooter.view.GameWorld;

import java.awt.*;

public class Bullet extends NonControllableObjects {
    public Bullet(int x, int y) {
        position = new Point(x, y);
    }

    public Bullet(Point position) {
        this.position = position;
    }
}
