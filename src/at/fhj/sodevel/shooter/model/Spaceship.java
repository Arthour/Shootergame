package at.fhj.sodevel.shooter.model;

import at.fhj.sodevel.shooter.controller.SpaceshipMovementDown;
import at.fhj.sodevel.shooter.controller.SpaceshipMovementLeft;
import at.fhj.sodevel.shooter.controller.SpaceshipMovementRight;
import at.fhj.sodevel.shooter.controller.SpaceshipMovementUp;
import at.fhj.sodevel.shooter.view.GameWorld;

import java.awt.*;

public class Spaceship extends SpaceObject implements Runnable {
    // Acceleration
    private int  accelU = 0, accelD = 0, accelL = 0, accelR = 0;
    public boolean isAcceleratingU = false, isAcceleratingD = false, isAcceleratingL = false, isAcceleratingR = false;
    public boolean movingU = false, movingD = false, movingL = false, movingR = false;
    private int maxHor = 900, maxVer = 1000;
    private int modificator = 3;

    //Health
    private int health = 100;

    //General
    private GameWorld world;


    public Spaceship(int x, int y, GameWorld world) {
        position = new Point(x, y);
        this.world = world;
        bounds = new Rectangle(x, y-10,22,11);
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (isAcceleratingU) {
                    if(accelU < maxVer / modificator) {
                        accelU = maxVer / modificator;
                    } else if(accelU * 1.015F + 1 >= maxVer) {
                        accelU = maxVer;
                    } else if(accelU <= maxVer) {
                        accelU = Math.round(accelU * 1.015F) + 1;
                    }
                    if (!movingU) {
                        new Thread(new SpaceshipMovementUp(this)).start();
                        movingU = true;
                    }
                } else {
                    if (accelU < 25 && accelU > 0) {
                        accelU = 0;
                    } else if (accelU >= 25) {
                        accelU = Math.round(accelU / 1.05F - 5);
                    }
                }

                if (isAcceleratingD) {
                    if(accelD < maxVer / modificator) {
                        accelD = maxVer / modificator;
                    } else if(accelD * 1.015F + 1 >= maxVer) {
                        accelD = maxVer;
                    } else if(accelD <= maxVer) {
                        accelD = Math.round(accelD * 1.015F) + 1;
                    }
                    if (!movingD) {
                        new Thread(new SpaceshipMovementDown(this)).start();
                        movingD = true;
                    }
                } else {
                    if (accelD < 25 && accelD > 0) {
                        accelD = 0;
                    } else if (accelD >= 25) {
                        accelD = Math.round(accelD / 1.05F - 5);
                    }
                }

                if (isAcceleratingL) {
                    if(accelL < maxVer / modificator) {
                        accelL = maxVer / modificator;
                    } else if(accelL * 1.015F + 2 >= maxHor) {
                        accelL = maxHor;
                    } else if(accelL <= maxHor) {
                        accelL = Math.round(accelL * 1.015F) + 2;
                    }
                    if (!movingL) {
                        new Thread(new SpaceshipMovementLeft(this)).start();
                        movingL = true;
                    }
                } else {
                    if (accelL < 25 && accelL > 0) {
                        accelL = 0;
                    } else if (accelL >= 25) {
                        accelL = Math.round(accelL / 1.05F - 2);
                    }
                }

                if (isAcceleratingR) {
                    if(accelR < maxVer / modificator) {
                        accelR = maxVer / modificator;
                    } else if(accelR * 1.015F + 2 >= maxHor) {
                        accelR = maxHor;
                    } else if(accelR <= maxHor) {
                        accelR = Math.round(accelR * 1.015F) + 2;
                    }
                    if (!movingR) {
                        new Thread(new SpaceshipMovementRight(this)).start();
                        movingR = true;
                    }
                } else {
                    if (accelR < 25 && accelR > 0) {
                        accelR = 0;
                    } else if (accelR >= 25) {
                        accelR = Math.round(accelR / 1.05F - 2);
                    }
                }


                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getAccelU() {
        return accelU;
    }

    public int getAccelD() {
        return accelD;
    }

    public int getAccelL() {
        return accelL;
    }

    public int getAccelR() {
        return accelR;
    }

    public void move(String direction) {
        switch (direction) {
            case "UP":
                if (world.getY() + 10 < bounds.getY()) {
                    modY(-1);
                }
                break;
            case "DOWN":
                if (world.getHeight() - 10 > bounds.getY() + bounds.getHeight()) {
                    modY(1);
                }
                break;
            case "RIGHT":
                if (world.getWidth() - 10 > bounds.getX() + bounds.getWidth()) {
                    modX(1);
                }
                break;
            case "LEFT":
                if (world.getX() + 10 < bounds.getX()) {
                    modX(-1);
                }
        }
        bounds.setLocation(getX(), getY() - 10);
    }

    public int getHealth() {
        return health;
    }

    public void increaseHealth(int amount) {
        if (health + amount <= 100) {
            health += amount;
        } else if (health + amount > 100) {
            health = 100;
        }
    }

    public void decreaseHealth(int amount) {
        health -= amount;
        if (health <= 0) {
            //TODO: Gameover
        }
    }
}
