package at.fhj.sodevel.shooter.controller;

import at.fhj.sodevel.shooter.model.Spaceship;

public class SpaceshipMovementUp implements Runnable {
    private Spaceship ship;
    public SpaceshipMovementUp(Spaceship ship) {
        this.ship = ship;
    }

    @Override
    public void run() {
        while (ship.getAccelU() > 0 && ship.movingU) {
            try {
                ship.move("UP");
                Thread.sleep(Math.round(5000 / (ship.getAccelU() + 1)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ship.movingU = false;
    }

}
