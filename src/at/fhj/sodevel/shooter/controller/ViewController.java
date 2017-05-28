package at.fhj.sodevel.shooter.controller;

import at.fhj.sodevel.shooter.view.GameWindow;
import at.fhj.sodevel.shooter.view.GameWorld;
import at.fhj.sodevel.shooter.view.IngameMenu;
import at.fhj.sodevel.shooter.view.MainMenu;

import javax.swing.*;
import java.awt.*;

public class ViewController {
    GameWindow window;

    MainMenu mainMenu;
    IngameMenu ingameMenu;
    GameWorld world;

    public ViewController (GameWindow window) {
        this.window = window;

        mainMenu = new MainMenu(this);
        window.add(mainMenu).setVisible(false);
        ingameMenu = new IngameMenu(this);
        window.add(ingameMenu).setVisible(false);
    }

    public void showMainMenu() {
        window.setLayout(new GridBagLayout());
        mainMenu.setVisible(true);
    }

    public void hideMainMenu() {
        mainMenu.setVisible(false);
    }

    public void showIngameMenu() {
        window.setLayout(new GridBagLayout());
        ingameMenu.setVisible(true);
    }

    public void hideIngameMenu() {
        ingameMenu.setVisible(false);
    }

    public void initGameWorld() {
        if (world != null) {
            window.remove(world);
        }
        world = new GameWorld(window);
        window.add(world);
    }

    public void showGameWorld() {
        window.setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS));
        world.setVisible(true);
    }

    public void hideGameWorld() {
        world.setVisible(false);
    }

}
