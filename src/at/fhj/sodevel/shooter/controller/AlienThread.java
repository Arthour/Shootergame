package at.fhj.sodevel.shooter.controller;

import at.fhj.sodevel.shooter.model.Alien;
import at.fhj.sodevel.shooter.view.GameWorld;

import java.util.Iterator;

public class AlienThread implements Runnable {
    private GameWorld world;

    public AlienThread(GameWorld world) {
        this.world = world;
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized(world.aliens) {
                    Iterator<Alien> i = world.aliens.iterator();
                    while (i.hasNext()) {
                        Alien a = i.next();

                        if (a.getX() > world.getParent().getX() - 30) {
                            a.moveBackward();
                        } else {
                            i.remove();
                        }
                    }
                }
                Thread.sleep(12);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}