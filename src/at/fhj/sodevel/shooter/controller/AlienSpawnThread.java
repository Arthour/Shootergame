package at.fhj.sodevel.shooter.controller;

import at.fhj.sodevel.shooter.view.GameWorld;

import java.util.Random;

public class AlienSpawnThread implements Runnable {
    private GameLoop parent;
    private Random r = new Random();


    public AlienSpawnThread(GameLoop parent) {
        this.parent = parent;
    }

    @Override
    public void run() {
        while (true) {
            try {
                parent.spawnAlien(spawnY(parent.getParent().getY(), parent.getParent().getHeight(), 20));
                Thread.sleep(500 + r.nextInt(500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private int spawnY(int screenMin, int screenMax, int modifier) {
        int rand = r.nextInt(screenMax);
        if(rand >= screenMax - modifier) {
            return screenMax - modifier;
        } else if (rand <= screenMin + modifier) {
            return screenMin + modifier;
        } else {
            return rand + modifier;
        }
    }
}
