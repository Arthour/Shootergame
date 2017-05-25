package at.fhj.sodevel.shooter.controller;

import at.fhj.sodevel.shooter.model.Alien;
import at.fhj.sodevel.shooter.model.SpaceObject;
import at.fhj.sodevel.shooter.view.GameWorld;

public class CollisionThread implements Runnable {
    GameWorld world;

    public CollisionThread(GameWorld world) {
        this.world = world;
    }

    public void run() {
        while (true) {
            try {
                while (world.aliensToCheckCollision.hasNext()) {
                    Alien a = world.aliensToCheckCollision.next();
                    if (isColliding(world.getShip(), a)) {
                        //TODO: damage player
                    }
                    //TODO: check collision for bullets/missiles and aliens
                }
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isColliding (SpaceObject a, SpaceObject b) {
        double aX1 = a.bounds.getX();
        double aX2 = a.bounds.getX() + a.bounds.getWidth();
        double aY1 = a.bounds.getY();
        double aY2 = a.bounds.getY() + a.bounds.getHeight();

        double bX1 = b.bounds.getX();
        double bX2 = b.bounds.getX() + b.bounds.getWidth();
        double bY1 = b.bounds.getY();
        double bY2 = b.bounds.getY() + b.bounds.getHeight();

        if (aX1 < bX2 && aX2 > bX1 && aY1 < bY2 && aY2 > bY1) {
            return true;
        }
        return false;
    }
}
