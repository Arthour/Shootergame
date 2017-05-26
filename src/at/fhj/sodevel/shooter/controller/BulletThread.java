package at.fhj.sodevel.shooter.controller;

import at.fhj.sodevel.shooter.model.Bullet;
import at.fhj.sodevel.shooter.view.GameWorld;

import java.util.Iterator;

public class BulletThread implements Runnable {
    private GameWorld world;

    public BulletThread(GameWorld world) {
        this.world = world;
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized(world.bullets) {
                    Iterator<Bullet> i = world.bullets.iterator();
                    while (i.hasNext()) {
                        Bullet b = i.next();

                        if (b.getX() < world.getParent().getWidth() + 10) {
                            b.moveForward();
                        } else {
                            i.remove();
                        }
                    }
                }
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}