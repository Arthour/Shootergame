package at.fhj.sodevel.shooter.model;

import java.awt.*;

public class Missile extends NonControllableObjects {
    public Missile(int x, int y) {
        position = new Point(x, y);
    }

    public Missile(Point position) {
        this.position = position;
    }
}
