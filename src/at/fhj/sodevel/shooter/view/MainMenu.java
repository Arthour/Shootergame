package at.fhj.sodevel.shooter.view;

import at.fhj.sodevel.shooter.controller.ViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel implements ActionListener{
    private ViewController viewController;

    public MainMenu(ViewController viewController) {
        super();

        this.viewController = viewController;

        this.setLayout(new GridLayout(3, 1));

        JButton newGame = new JButton("New Game");
        newGame.addActionListener(this);
        newGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(newGame);
        JButton settings = new JButton("Settings");
        settings.addActionListener(this);
        settings.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(settings);
        JButton exit = new JButton("Exit");
        exit.addActionListener(this);
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(exit);
    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "New Game":
                viewController.initGameWorld();
                viewController.showGameWorld();
                viewController.hideMainMenu();
                break;
            case "Settings":
                break;
            case "Exit":
                System.exit(0);
        }
    }
}