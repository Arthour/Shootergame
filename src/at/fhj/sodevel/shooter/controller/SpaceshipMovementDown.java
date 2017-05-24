package at.fhj.sodevel.shooter.controller;

import at.fhj.sodevel.shooter.model.Spaceship;

public class SpaceshipMovementDown implements Runnable {
    private Spaceship ship;
    public SpaceshipMovementDown(Spaceship ship) {
        this.ship = ship;
    }

    @Override
    public void run() {
        while (ship.getAccelD() > 0 && ship.movingD) {
            try {
                ship.move("DOWN");
                Thread.sleep(Math.round(5000 / ship.getAccelD()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ship.movingD = false;
    }

}
