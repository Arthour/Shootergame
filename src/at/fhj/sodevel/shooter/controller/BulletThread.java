package at.fhj.sodevel.shooter.controller;

import at.fhj.sodevel.shooter.model.Bullet;
import at.fhj.sodevel.shooter.view.GameWorld;

import java.util.*;

public class BulletThread implements Runnable {
    private GameWorld world;

    public BulletThread(GameWorld world) {
        this.world = world;
    }

    @Override
    public void run() {
        for (;;) {
            try {
                world.bullets.addAll(world.bulletsToAdd);
                world.bulletsToAdd.clear();
                Iterator<Bullet> iter = world.bullets.iterator();
                ArrayList<Bullet> temp = new ArrayList<>();
                while (iter.hasNext()) {
                    Bullet b = iter.next();

                    if (b.getX() < world.getParent().getWidth() + 10) {
                        b.moveForward();
                        temp.add(b);
                    } else {
                        iter.remove();
                    }
                }
                world.bulletsToDraw = temp.iterator();
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}