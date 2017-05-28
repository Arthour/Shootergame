package at.fhj.sodevel.shooter.controller;

import at.fhj.sodevel.shooter.model.Alien;
import at.fhj.sodevel.shooter.model.Bullet;
import at.fhj.sodevel.shooter.model.Missile;
import at.fhj.sodevel.shooter.view.GameWorld;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class GameLoop implements KeyListener, Runnable {
    private final Set<Integer> pressed = new HashSet<>();
    private GameWorld world;
    private ViewController viewController;
    private boolean spawningMissile = false;
    private boolean spawningBullet = false;

    private int missileCount = 3;

    public GameLoop(GameWorld world, ViewController viewController) {
        this.world = world;
        this.viewController = viewController;
        new Thread(this).start();
        new Thread(new AlienSpawnThread(this)).start();
    }

    public boolean isSpawningBullet() {
        return spawningBullet;
    }

    public void spawnBullet() {
        world.bullets.add(new Bullet(world.getShip().getX() + 20, world.getShip().getY()));
    }

    public void spawnMissile() {
        if (missileCount > 0) {
            world.missiles.add(new Missile(world.getShip().getX() + 20, world.getShip().getY()));
            missileCount--;
        }
    }

    public void spawnAlien(int y) {
        world.aliens.add(new Alien(world.getWidth() + 10, y));
    }

    public GameWorld getWorld() {
        return world;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        pressed.add(e.getKeyCode());
        for (int key : pressed) {
            switch (key) {
                case KeyEvent.VK_RIGHT:
                    world.getShip().isAcceleratingR = true;
                    break;
                case KeyEvent.VK_LEFT:
                    world.getShip().isAcceleratingL = true;
                    break;
                case KeyEvent.VK_UP:
                    world.getShip().isAcceleratingU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    world.getShip().isAcceleratingD = true;
                    break;
                case KeyEvent.VK_SPACE:
                    if (!spawningBullet) {
                        new Thread(new BulletSpawnThread(this)).start();
                        spawningBullet = true;
                    }
                    break;
                case KeyEvent.VK_SHIFT:
                    if (!spawningMissile) {
                        spawnMissile();
                        spawningMissile = true;
                    }
                    break;
                case KeyEvent.VK_ESCAPE:
                    viewController.showIngameMenu();
                    //TODO: pause, open menu
                    //Semaphore
                    //wait(), notify(), notifyAll()
                    break;
                case KeyEvent.VK_Z:
                    int up = getWorld().getShip().getAccelU();
                    int down = getWorld().getShip().getAccelD();
                    int left = getWorld().getShip().getAccelL();
                    int right = getWorld().getShip().getAccelR();

                    System.out.printf("UP: %d (%b), DOWN: %d (%b), LEFT: %d (%b), RIGHT: %d (%b)\n", up, getWorld().getShip().isAcceleratingU, down, getWorld().getShip().isAcceleratingD, left, getWorld().getShip().isAcceleratingL, right, getWorld().getShip().isAcceleratingR);
            }
        }
    }

    @Override
    public synchronized void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                world.getShip().isAcceleratingU = false;
                break;
            case KeyEvent.VK_DOWN:
                world.getShip().isAcceleratingD = false;
                break;
            case KeyEvent.VK_LEFT:
                world.getShip().isAcceleratingL = false;
                break;
            case KeyEvent.VK_RIGHT:
                world.getShip().isAcceleratingR = false;
                break;
            case KeyEvent.VK_SPACE:
                spawningBullet = false;
            case KeyEvent.VK_SHIFT:
                spawningMissile = false;
        }
        pressed.remove(e.getKeyCode());
    }

    @Override
    public void run() {
        while (true) {
            try {
                world.revalidate();
                world.repaint();
                Thread.sleep(16, 666);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
