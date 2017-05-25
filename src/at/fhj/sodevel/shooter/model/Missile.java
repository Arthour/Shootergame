package at.fhj.sodevel.shooter.model;

import java.awt.*;

public class Missile extends NonControllableObjects {
    public Missile(int x, int y) {
        position = new Point(x, y);
        bounds = new Rectangle(x, y-11,24,13);
    }

    public Missile(Point position) {
        this.position = position;
    }
}
