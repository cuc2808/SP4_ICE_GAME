package Main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //SCREEN SETTINGS - includes resolution, tile size and so on:
    //                  ===Attributes===
    final int originalTileSize = 32;        //We're making a 2D game and deciding on the size for each Tile: 32x32.
    final int scale = 2;        //As 32x32 pixel isn't much, we upscale it. That's why we create an attribute for it. Here it's: x3.

    final int tileSize = originalTileSize * scale; //Therefor this attribute will be the one we use, as it takes into account the previous.
    // ^ 96x96 tiles.

    //We now have a size for our building blocks, now we decide on how many building blocks our game can contain.
    final int maxScreenCollum = 14; //The amount of tiles * x
    final int maxScreenRow = 14; //The amount of tiles * Y
    final int screenWidth = tileSize * maxScreenCollum; //Screen width. 1536 Pixels.
    final int screenLength = tileSize * maxScreenRow; //Screen length. 1152 Pixels.

    Thread gameThread; // This makes the game running instead of static. "A thread is a thread of execution in a program." It keeps running until the "Run" is executed. -- There is also added a method called run.
    //This Thread is also a

    //      ===== Constructor =====
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenLength));        //this =  is our Class, that we then get the size with Height * Length. We also use a new command Java just imports called Dimension = (H x L).
        this.setBackground(Color.LIGHT_GRAY);      //Not all needed, but we get blue background. COLOR. is goated.
        this.setDoubleBuffered(true);       //It helps with rendering/faster load. Basically it draws the program in another window we can't see before getting displayed.
    }

    public void startGameThread() {

        gameThread = new Thread(this); //We're here passing the "GamePanel" class to the thread. So now the thread is instantiated.
        gameThread.start();  //This will call the 'run' method.
    }

    @Override
    public void run() {         //Runnable adds this methode as it's implemented. And our Thread automatically calls this method when we start the gameThread.

        while (gameThread != null) {


            //UPDATE: this update information.
            update();

            //DRAW: this draws our graphics and information.
            repaint(); //repaint is how you call paintComponent

        }
    }

    public void update() { //

    }

    public void paintComponent(Graphics g) { //This is a method by Java in the JFrame package, it draws graphics.
        //We have to call the superClass, in this case JFrame.
        super.paintComponent(g);

    }
}
