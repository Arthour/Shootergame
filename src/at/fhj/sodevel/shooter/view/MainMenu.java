package at.fhj.sodevel.shooter.view;

import at.fhj.sodevel.shooter.ShooterGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel implements ActionListener{
    private GameWindow parent;

    public MainMenu(GameWindow parent) {
        super();

        this.parent = parent;

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
                parent.startGame();
                this.setVisible(false);
                break;
            case "Settings":
                break;
            case "Exit":
                System.exit(0);
        }
    }
}