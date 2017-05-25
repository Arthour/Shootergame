package at.fhj.sodevel.shooter.controller;

import at.fhj.sodevel.shooter.view.GameWorld;

public class CollisionThread implements Runnable {
    GameWorld world;

    public CollisionThread(GameWorld world) {
        this.world = world;
    }

    public void run() {
        while (true) {
            try {

                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isColliding (Object a, Object b) {
        if (true) {
            return true;
        }
        return false;
    }
}
