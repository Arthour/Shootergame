package at.fhj.sodevel.shooter.controller;

import at.fhj.sodevel.shooter.model.Missile;
import at.fhj.sodevel.shooter.view.GameWorld;

import java.util.ArrayList;
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
                world.missiles.addAll(world.missilesToAdd);
                world.missilesToAdd.clear();
                Iterator<Missile> iter = world.missiles.iterator();
                ArrayList<Missile> temp = new ArrayList<>();
                while (iter.hasNext()) {
                    Missile m = iter.next();

                    if (m.getX() < world.getParent().getWidth() + 10) {
                        m.moveForward();
                        temp.add(m);
                    } else {
                        iter.remove();
                    }
                }
                world.missilesToDraw = temp.iterator();
                Thread.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}