package at.fhj.sodevel.shooter.model;

import at.fhj.sodevel.shooter.view.GameWorld;

import java.awt.*;

public class SpaceObject {
    protected Point position;
    protected GameWorld world;

    public Point getPosition() {
        return position;
    }

    public int getX() {
        return (int)position.getX();
    }

    public int getY() {
        return (int)position.getY();
    }

    public void modX(int x) {
        position = new Point(getX() + x, getY());
    }

    public void modY(int y) {
        position = new Point(getX(), getY() + y);
    }

}
