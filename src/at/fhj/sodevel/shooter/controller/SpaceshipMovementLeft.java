package at.fhj.sodevel.shooter.controller;

import at.fhj.sodevel.shooter.model.Spaceship;

public class SpaceshipMovementLeft implements Runnable {
    private Spaceship ship;
    public SpaceshipMovementLeft(Spaceship ship) {
        this.ship = ship;
    }

    @Override
    public void run() {
        while (ship.getAccelL() > 0 && ship.movingL) {
            try {
                ship.move("LEFT");
                Thread.sleep(Math.round(5000 / (ship.getAccelL() + 1)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ship.movingL = false;
    }

}
