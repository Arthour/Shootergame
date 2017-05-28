package at.fhj.sodevel.shooter.view;

import at.fhj.sodevel.shooter.controller.ViewController;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    private ViewController viewController = new ViewController(this);

    public GameWindow() {
        super();

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);

        GraphicsDevice gD = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        if (gD.isFullScreenSupported()) {
            gD.setFullScreenWindow(this);
        }

        init();
    }

    public GameWindow(int sizeX, int sizeY) {
        super();

        this.setTitle("Shooter Game");
        this.setSize(sizeX, sizeY);

        init();
    }

    private void init() {
        this.setBackground(Color.black);
        viewController.showMainMenu();

        this.setVisible(true);
    }

    public ViewController getViewController() {
        return viewController;
    }
}
