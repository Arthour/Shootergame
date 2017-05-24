package at.fhj.sodevel.shooter.view;

import at.fhj.sodevel.shooter.controller.BulletThread;
import at.fhj.sodevel.shooter.controller.GameLoop;
import at.fhj.sodevel.shooter.controller.MissileThread;
import at.fhj.sodevel.shooter.model.Bullet;
import at.fhj.sodevel.shooter.model.Missile;
import at.fhj.sodevel.shooter.model.Spaceship;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class GameWorld extends JPanel {
    public Spaceship ship;
    public ArrayList<Bullet> bullets = new ArrayList<>();
    public ArrayList<Bullet> bulletsToAdd = new ArrayList<>();
    public Iterator<Bullet> bulletsToDraw;
    public ArrayList<Missile> missiles = new ArrayList<>();
    public ArrayList<Missile> missilesToAdd = new ArrayList<>();
    public Iterator<Missile> missilesToDraw;

    public BulletThread bt;


    public GameWorld(GameWindow parent) {
        super();

        this.setSize(parent.getSize());
        this.setLayout(new FlowLayout());
        this.setFocusable(true);

        ship = new Spaceship(parent.getWidth() / 2, parent.getHeight() / 2);
        new Thread(ship).start();

        this.setVisible(true);

        new Thread(bt = new BulletThread(this)).start();
        new Thread(new MissileThread(this)).start();

        this.addKeyListener(new GameLoop(this));
    }

    public Spaceship getShip() {
        return ship;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        final Graphics2D g2 = (Graphics2D)g;
        setFocusable(true);

        g2.drawString("(=>", ship.getX(), ship.getY());
        while (bulletsToDraw.hasNext()) {
            Bullet b = bulletsToDraw.next();
            g2.drawString("-", b.getX(), b.getY());
        }
        while (missilesToDraw.hasNext()) {
            Missile m = missilesToDraw.next();
            g2.drawString("->", m.getX(), m.getY());
        }
    }
}
