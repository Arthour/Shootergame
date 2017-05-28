package at.fhj.sodevel.shooter.controller;

import at.fhj.sodevel.shooter.model.Spaceship;

public class SpaceshipMovementRight implements Runnable {
    private Spaceship ship;
    public SpaceshipMovementRight(Spaceship ship) {
        this.ship = ship;
    }

    @Override
    public void run() {
        while (ship.getAccelR() > 0 && ship.movingR) {
            try {
                ship.move("RIGHT");
                Thread.sleep(Math.round(5000 / (ship.getAccelR() + 1)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ship.movingR = false;
    }

}
