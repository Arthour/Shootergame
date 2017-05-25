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
    private int maxHor = 800, maxVer = 1000;
    private GameWorld world;

    public Spaceship(int x, int y, GameWorld world) {
        position = new Point(x, y);
        this.world = world;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (isAcceleratingU) {
                    if(accelU < maxVer / 10) {
                        accelU = maxVer / 10;
                    } else if(accelU * 1.015F > maxVer) {
                        accelU = maxVer;
                    } else if(accelU >= maxVer / 10 && accelU <= maxVer) {
                        accelU = Math.round(accelU * 1.015F);
                    }
                    if (!movingU) {
                        new Thread(new SpaceshipMovementUp(this)).start();
                        movingU = true;
                    }
                } else {
                    if (accelU < 25 && accelU > 0) {
                        accelU = 0;
                    } else if (accelU >= 25) {
                        accelU = Math.round(accelU / 1.1F - 5);
                    }
                }

                if (isAcceleratingD) {
                    if(accelD < maxVer / 10) {
                        accelD = maxVer / 10;
                    } else if(accelD * 1.015F > maxVer) {
                        accelD = maxVer;
                    } else if(accelD >= maxVer / 10 && accelD <= maxVer) {
                        accelD = Math.round(accelD * 1.015F);
                    }
                    if (!movingD) {
                        new Thread(new SpaceshipMovementDown(this)).start();
                        movingD = true;
                    }
                } else {
                    if (accelD < 25 && accelD > 0) {
                        accelD = 0;
                    } else if (accelD >= 25) {
                        accelD = Math.round(accelD / 1.1F - 2);
                    }
                }

                if (isAcceleratingL) {
                    if(accelL < maxVer / 10) {
                        accelL = maxVer / 10;
                    } else if(accelL * 1.015F > maxHor) {
                        accelL = maxHor;
                    } else if(accelL >= maxVer / 10 && accelL <= maxHor) {
                        accelL = Math.round(accelL * 1.015F);
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
                    if(accelR < maxVer / 10) {
                        accelR = maxVer / 10;
                    } else if(accelR * 1.015F > maxHor) {
                        accelR = maxHor;
                    } else if(accelR >= maxVer / 10 && accelR <= maxHor) {
                        accelR = Math.round(accelR * 1.015F);
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

    public float getAccelU() {
        return accelU;
    }

    public float getAccelD() {
        return accelD;
    }

    public float getAccelL() {
        return accelL;
    }

    public float getAccelR() {
        return accelR;
    }

    public void move(String direction) {
        switch (direction) {
            case "UP":
                if (world.getY() + 20 < getY()) {
                    modY(-1);
                }
                break;
            case "DOWN":
                if (world.getHeight() - 10 > getY()) {
                    modY(1);
                }
                break;
            case "RIGHT":
                if (world.getWidth() - 30 > getX()) {
                    modX(1);
                }
                break;
            case "LEFT":
                if (world.getX() + 10 < getX()) {
                    modX(-1);
                }
        }


    }
}
