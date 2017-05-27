package at.fhj.sodevel.shooter.model;

import java.awt.*;

public class Bullet extends NonControllableObjects {
    public Bullet(int x, int y) {
        position = new Point(x, y);
        bounds = new Rectangle(x, y-4,6,0);
    }
}
