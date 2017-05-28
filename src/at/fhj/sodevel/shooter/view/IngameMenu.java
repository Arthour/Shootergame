package at.fhj.sodevel.shooter.view;

import at.fhj.sodevel.shooter.controller.ViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IngameMenu extends JPanel implements ActionListener {
    private ViewController viewController;

    public IngameMenu(ViewController viewController) {
        super();

        this.viewController = viewController;

        this.setLayout(new GridLayout(4, 1));

        JButton newGame = new JButton("Restart");
        newGame.addActionListener(this);
        newGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(newGame);
        JButton mainMenu = new JButton("Exit to main menu");
        mainMenu.addActionListener(this);
        mainMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(mainMenu);
        JButton exit = new JButton("Exit to desktop");
        exit.addActionListener(this);
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(exit);
        JButton resume = new JButton("Resume game");
        resume.addActionListener(this);
        resume.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(resume);
    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Restart":
                viewController.initGameWorld();
                viewController.showGameWorld();
                viewController.hideIngameMenu();
                break;
            case "Exit to main menu":
                viewController.showMainMenu();
                viewController.hideIngameMenu();
                break;
            case "Exit to desktop":
                System.exit(0);
                break;
            case "Resume game":
                viewController.showGameWorld();
                viewController.hideIngameMenu();
        }
    }
}