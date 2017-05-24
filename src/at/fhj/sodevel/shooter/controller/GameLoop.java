package at.fhj.sodevel.shooter.controller;

import at.fhj.sodevel.shooter.model.Bullet;
import at.fhj.sodevel.shooter.model.Missile;
import at.fhj.sodevel.shooter.view.GameWorld;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class GameLoop implements KeyListener, Runnable {
    private final Set<Integer> pressed = new HashSet<>();
    private GameWorld parent;
    private boolean spawningMissile = false;
    private boolean spawningBullet = false;

    private int missileCount = 3;


    public GameLoop(GameWorld parent) {
        this.parent = parent;
        new Thread(this).start();
    }

    public boolean isSpawningBullet() {
        return spawningBullet;
    }

    public void spawnBullet() {
        parent.bulletsToAdd.add(new Bullet(parent.getShip().getX() + 20, parent.getShip().getY(), parent));
    }

    public void spawnMissile() {
        if (missileCount > 0) {
            parent.missiles.add(new Missile(parent.getShip().getX() + 20, parent.getShip().getY(), parent));
            missileCount--;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        pressed.add(e.getKeyCode());
        for (int key : pressed) {
            switch (key) {
                case KeyEvent.VK_RIGHT:
                    parent.getShip().isAcceleratingR = true;
                    break;
                case KeyEvent.VK_LEFT:
                    parent.getShip().isAcceleratingL = true;
                    break;
                case KeyEvent.VK_UP:
                    parent.getShip().isAcceleratingU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    parent.getShip().isAcceleratingD = true;
                    break;
                case KeyEvent.VK_SPACE:
                    if (!spawningBullet) {
                        new Thread(new BulletSpawnThread(this, parent.bt)).start();
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
                    //TODO: pause, open menu
                    break;
            }
        }
    }

    @Override
    public synchronized void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                parent.getShip().isAcceleratingU = false;
                break;
            case KeyEvent.VK_DOWN:
                parent.getShip().isAcceleratingD = false;
                break;
            case KeyEvent.VK_LEFT:
                parent.getShip().isAcceleratingL = false;
                break;
            case KeyEvent.VK_RIGHT:
                parent.getShip().isAcceleratingR = false;
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
                parent.revalidate();
                parent.repaint();
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
