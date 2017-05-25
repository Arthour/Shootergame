package at.fhj.sodevel.shooter.model;

import java.awt.*;

public class Bullet extends NonControllableObjects {
    public Bullet(int x, int y) {
        position = new Point(x, y);
        bounds = new Rectangle(x, y-11,24,13);
    }

    public Bullet(Point position) {
        this.position = position;
    }
}
