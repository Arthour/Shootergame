package at.fhj.sodevel.shooter.controller;

public class BulletSpawnThread implements Runnable {
    private GameLoop parent;
    private BulletThread bt;


    public BulletSpawnThread(GameLoop parent, BulletThread bt) {
        this.parent = parent;
        this.bt = bt;
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
