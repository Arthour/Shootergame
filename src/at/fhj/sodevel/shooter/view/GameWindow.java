package at.fhj.sodevel.shooter.view;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    private MainMenu menu;
    private GameWorld world;

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
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.black);

        menu = new MainMenu(this);
        this.add(menu).setVisible(true);

        this.setVisible(true);
    }

    public void startGame() {
        world = new GameWorld(this);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.add(world);
    }

}
