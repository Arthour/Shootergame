package at.fhj.sodevel.shooter.model;

import java.awt.*;

public class Alien extends NonControllableObjects {
    public Alien(int x, int y) {
        position = new Point(x, y);
    }

    public Alien(Point position) {
        this.position = position;
    }
}
