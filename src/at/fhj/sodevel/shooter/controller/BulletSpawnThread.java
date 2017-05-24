package at.fhj.sodevel.shooter.controller;

public class BulletSpawnThread implements Runnable {
    private GameLoop parent;

    public BulletSpawnThread(GameLoop parent) {
        this.parent = parent;
    }

    @Override
    public void run() {
        while (parent.isSpawningBullet()) {
            try {
                if (parent.isSpawningBullet()) {
                    parent.spawnBullet();
                }
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
