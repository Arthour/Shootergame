package at.fhj.sodevel.shooter.model;

import java.awt.*;

public class Alien extends NonControllableObjects {
    private int health = 5;

    public Alien(int x, int y) {
        position = new Point(x, y);
        bounds = new Rectangle(x, y-11,24,13);
    }

    public void decreaseHealth(int amount) {
        health -= amount;
    }

    public int getHealth() {
        return health;
    }
}
