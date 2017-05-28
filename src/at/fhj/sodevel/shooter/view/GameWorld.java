package at.fhj.sodevel.shooter.view;

import at.fhj.sodevel.shooter.controller.*;
import at.fhj.sodevel.shooter.model.Alien;
import at.fhj.sodevel.shooter.model.Bullet;
import at.fhj.sodevel.shooter.model.Missile;
import at.fhj.sodevel.shooter.model.Spaceship;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class GameWorld extends JPanel {
    private Spaceship ship;

    public List<Bullet> bullets = Collections.synchronizedList(new LinkedList<Bullet>());
    public List<Missile> missiles = Collections.synchronizedList(new LinkedList<Missile>());
    public List<Alien> aliens = Collections.synchronizedList(new LinkedList<Alien>());

    public GameWorld(GameWindow window) {
        super();

        this.setSize(window.getSize());
        this.setLayout(new FlowLayout());
        this.setFocusable(true);

        ship = new Spaceship(window.getWidth() / 2, window.getHeight() / 2, this);
        new Thread(ship).start();

        new Thread(new BulletThread(this)).start();
        new Thread(new MissileThread(this)).start();
        new Thread(new AlienThread(this)).start();

        new Thread(new CollisionThread(this)).start();

        this.addKeyListener(new GameLoop(this, window.getViewController()));
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
        //g2.drawRect((int)ship.bounds.getX(), (int)ship.bounds.getY(), (int)ship.bounds.getWidth(), (int)ship.bounds.getHeight());

        synchronized(bullets) {
            Iterator<Bullet> i = bullets.iterator();
            while (i.hasNext()) {
                Bullet b = i.next();
                g2.drawString("-", b.getX(), b.getY());
                //g2.drawRect((int)b.bounds.getX(), (int)b.bounds.getY(), (int)b.bounds.getWidth(), (int)b.bounds.getHeight());
            }
        }
        synchronized(missiles) {
            Iterator<Missile> i = missiles.iterator();
            while (i.hasNext()) {
                Missile m = i.next();
                g2.drawString("->", m.getX(), m.getY());
                //g2.drawRect((int)m.bounds.getX(), (int)m.bounds.getY(), (int)m.bounds.getWidth(), (int)m.bounds.getHeight());
            }
        }
        synchronized(aliens) {
            Iterator<Alien> i = aliens.iterator();
            while (i.hasNext()) {
                Alien a = i.next();
                g2.drawString("<=:(", a.getX(), a.getY());
                //g2.drawRect((int)a.bounds.getX(), (int)a.bounds.getY(), (int)a.bounds.getWidth(), (int)a.bounds.getHeight());
            }
        }
    }
}
