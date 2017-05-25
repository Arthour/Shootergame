package at.fhj.sodevel.shooter.model;

import java.awt.*;

public class Alien extends NonControllableObjects {
    public Alien(int x, int y) {
        position = new Point(x, y);
        bounds = new Rectangle(x, y-11,24,13);
    }

    public Alien(Point position) {
        this.position = position;
    }
}
