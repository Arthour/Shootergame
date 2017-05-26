package at.fhj.sodevel.shooter.controller;

import at.fhj.sodevel.shooter.model.Missile;
import at.fhj.sodevel.shooter.view.GameWorld;

import java.util.Iterator;

public class MissileThread implements Runnable {
    private GameWorld world;

    public MissileThread(GameWorld world) {
        this.world = world;
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized(world.missiles) {
                    Iterator<Missile> i = world.missiles.iterator();
                    while (i.hasNext()) {
                        Missile m = i.next();

                        if (m.getX() < world.getParent().getWidth() + 10) {
                            m.moveForward();
                        } else {
                            i.remove();
                        }
                    }
                }
                Thread.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}