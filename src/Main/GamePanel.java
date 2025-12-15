package Main;

import Tile.TileManager;
import entity.NPCs.NPC;
import entity.NPCs.NPCManager;
import entity.NPCs.NPC_Flamingo;
import object.ObjManager;
import object.superObject;
import util.FileIO;
import util.SoundSystem;

import entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //SCREEN SETTINGS - includes resolution, tile size and so on:
    //                  ===Attributes===
    final int originalTileSize = 32;        //We're making a 2D game and deciding on the size for each Tile: 32x32.
    final int scale = 3;        //As 32x32 pixel isn't much, we upscale it. That's why we create an attribute for it. Here it's: x3.

    public final int tileSize = originalTileSize * scale; //Therefor this attribute will be the one we use, as it takes into account the previous.
    // ^ 96x96 tiles.

    //We now have a size for our building blocks, now we decide on how many building blocks our game can contain.
    public int maxScreenCollum = 9; //The amount of tiles * x
    public int maxScreenRow = 9; //The amount of tiles * Y
    public final int screenWidth = tileSize * maxScreenCollum; //Screen width. 1536 Pixels.
    public final int screenHeight = tileSize * maxScreenRow; //Screen length. 1152 Pixels.

    //WORLD SETTINGS
    public final int maxWorldCol = 30;
    public final int maxWorldRow = 30;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHieght = tileSize * maxWorldRow;

    // FPS FRAMES PER SECOND:
    int FPS = 60;

    // Cutscene variables

    boolean isFading = false;
    float fadeAlpha = 0f;   // 0 = fully transparent, 1 = fully black
    float fadeSpeed = 0.02f; // Adjust for speed
    boolean fadeOut = true;  // true = fading to black, false = fading in
    Runnable fadeCallback;   // What to do when fade finishes

    public CutsceneManager cutsceneManager;
    Thread gameThread;       // This makes the game running instead of static. "A thread is a thread of execution in a program." It keeps running until the "Run" is executed. -- There is also added a method called run.

    public TileManager tileM = new TileManager(this);
    public FileIO io = new FileIO(this);
    public KeyHandler keyH = new KeyHandler(); //We need to instantiate the Handler to use it.
    public GUI gui = new GUI(this);
    public CollisionChecker colCheck = new CollisionChecker(this);
    public NPCManager npcManager = new NPCManager(this,io,gui);
    public ObjManager objManager = new ObjManager(this,io);
    public Player player = new Player(this, keyH);
    public NPC npcArray[] = new NPC[10];
    public superObject objArray[] = new superObject[10];




    //      ===== Constructor =====
    public GamePanel() {

        cutsceneManager = new CutsceneManager(this);


        this.setPreferredSize(new Dimension(screenWidth, screenHeight));        //this =  is our Class, that we then get the size with Height * Length. We also use a new command Java just imports called Dimension = (H x L).
        this.setBackground(Color.BLACK);      //Not all needed, but we get blue background. COLOR. is goated.
        this.setDoubleBuffered(true);       //It helps with rendering/faster load. Basically it draws the program in another window we can't see before getting displayed.
        this.addKeyListener(keyH);      //We make sure to add the *Specifik KeyListener to the program. Here it's keyH.
        this.setFocusable(true);        //Basically makes it so it's focused on keyInput.
    }

    public void setupGame(){

        objManager.setObject();
        //objManager.setObject();
        npcManager.setNPC();

    }

    public void startGameThread() {

        gameThread = new Thread(this); //We're here passing the "GamePanel" class to the thread. So now the thread is instantiated.
        gameThread.start();  //This will call the 'run' method.

        //soundSystem.musicBreak("Resources/soundFiles/dry-fart.wav");
    }

    @Override
    public void run() {         //Runnable adds this methode as it's implemented. And our Thread automatically calls this method when we start the gameThread.

        double drawInterval = 1000000000 / FPS;       //We use nanoseconds to limit our Framerate. This is equal to 0.01666 which means it draws 60 times per 1 second.
        double delta = 0;
        long lastTime = System.nanoTime(); //Time of the method called.
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();        //The current time of the while loop.

            delta += (currentTime - lastTime) / drawInterval;        //delta basically tells us how much time has passed.
            timer += (currentTime - lastTime);
            lastTime = currentTime;         //Now we set the current time to the last timne - acts like time, so we can use it again once the loops runs again.


            if (delta >= 1) {      //Since we divide the time passed with drawInterval then we end up updating when after a certain time. Here it is 60 FPS.
                //UPDATE: this update information.
                update();

                //DRAW: this draws our graphics and information.
                repaint(); //repaint is how you call paintComponent

                delta--;        //We subtract from delta so it works when we reset or run the loop again.
                drawCount++;
            }

            if (timer >= 1000000000) {     //Everytime the timer reaches so many nanoseconds, it counts a one second, and there we display the FPS or the drawCount. It should show the given FPS.
                // This also works to see if we update correctly.
                //It's basically an FPS counter.
                System.out.println("FPS: " + drawCount);
                drawCount = 0;      //We also have to reset them, so they don't just count upwards for eternity.
                timer = 0;
            }
        }
    }


    public void update() {

        if (cutsceneManager.isActive()) {
            cutsceneManager.update();
            return; // Stop normal game update mens cutscene kører
        }

        // Almindelig update
        player.update();

        //NPCs
        for (int i = 0; i < npcArray.length; i++) {
            if (npcArray[i] != null) {
                npcArray[i].update();
            }
        }

        gui.update();

    }


    public void paintComponent(Graphics g) { //This is a method by Java in the JFrame package, it draws graphics.
        //We have to call the superClass, in this case JFrame.
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;      //This method has more functions

        tileM.draw(g2);


        for(int i = 0;  i < objArray.length; i++){
            if(objArray[i] != null){
                objArray[i].draw(g2,this);
            }
        }

        //NPC
        for (int i = 0; i < npcArray.length; i++) {
            if (npcArray[i] != null) {
                npcArray[i].draw(g2);
            }
        }


        //It works a lot like processing... (very nice, it takes me back a whole 2 months !!!)
        player.draw(g2);

        gui.draw(g2);

        cutsceneManager.draw(g2);
        g2.dispose();
    }
}