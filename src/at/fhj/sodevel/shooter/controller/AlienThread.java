package at.fhj.sodevel.shooter.controller;

import at.fhj.sodevel.shooter.model.Alien;
import at.fhj.sodevel.shooter.model.Bullet;
import at.fhj.sodevel.shooter.view.GameWorld;

import java.util.ArrayList;
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
                world.aliens.addAll(world.aliensToAdd);
                world.aliensToAdd.clear();
                Iterator<Alien> iter = world.aliens.iterator();
                ArrayList<Alien> temp = new ArrayList<>();
                while (iter.hasNext()) {
                    Alien a = iter.next();

                    if (a.getX() > world.getParent().getX() - 10) {
                        a.moveBackward();
                        temp.add(a);
                    } else {
                        iter.remove();
                    }
                }
                world.aliensToDraw = temp.iterator();
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}