package Main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Game");

        // Create the Canvas-based GamePanel
        GamePanel gamePanel = new GamePanel();

        window.add(gamePanel);
        window.pack();  // Important: pack AFTER adding the panel
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();

        // Start game loop
        gamePanel.startGameThread();
    }
}
