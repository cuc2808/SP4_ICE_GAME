package Main;

import util.SoundSystem;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


        // We start using JFrame and set up some parameters:
        JFrame window = new JFrame();       //New instance of our window using JFrame.
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      //Makes sure we can close window when closing tab.
        window.setResizable(false);         //Makes it so the user cannot resize the window - this can cause complications.
        window.setTitle("Game");         //sets the title of the opened window (very simple)

        GamePanel gamePanel = new GamePanel();      //New instance of the GamePanel in main.

        window.add(gamePanel);      //We then add it to the window. This will work as Settings for the window. fx. size.

        window.pack();      //This allows us to see the window in action.

        window.setLocationRelativeTo(null);      //Make it so the window/our game will appear in the middle.
        window.setVisible(true);        //Makes it so we can see the window.


        gamePanel.startGameThread();



    }
}