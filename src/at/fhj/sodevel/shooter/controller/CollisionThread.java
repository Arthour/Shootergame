package at.fhj.sodevel.shooter.controller;

import at.fhj.sodevel.shooter.model.Alien;
import at.fhj.sodevel.shooter.model.Bullet;
import at.fhj.sodevel.shooter.model.Missile;
import at.fhj.sodevel.shooter.model.SpaceObject;
import at.fhj.sodevel.shooter.view.GameWorld;

import java.util.Iterator;
import java.util.Random;

public class CollisionThread implements Runnable {
    GameWorld world;
    Random r = new Random();

    public CollisionThread(GameWorld world) {
        this.world = world;
    }

    public void run() {
        while (true) {
            try {
                synchronized(world.aliens) {
                    Iterator<Alien> iA= world.aliens.iterator();
                    while (iA.hasNext()) {
                        Alien a = iA.next();

                        if (isColliding(world.getShip(), a)) {
                            world.getShip().decreaseHealth(5);
                            iA.remove();
                        }
                        synchronized (world.missiles) {
                            Iterator<Missile> iM = world.missiles.iterator();
                            while (iM.hasNext()) {
                                Missile m = iM.next();

                                if (isColliding(m, a)) {
                                    iM.remove();
                                    a.decreaseHealth(3);
                                    if (a.getHealth() <= 0) {
                                        iA.remove();
                                    }
                                }

                            }
                        }
                        synchronized (world.bullets) {
                            Iterator<Bullet> iB = world.bullets.iterator();
                            while (iB.hasNext()) {
                                Bullet b = iB.next();

                                if (isColliding(b, a)) {
                                    iB.remove();
                                    a.decreaseHealth(r.nextInt(1) + 1);
                                    if (a.getHealth() <= 0) {
                                        try {
                                            iA.remove();
                                        } catch (IllegalStateException e) {
                                            System.out.println(e);
                                        }
                                    }
                                }

                            }
                        }
                    }
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
