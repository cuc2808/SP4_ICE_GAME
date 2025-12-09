package Main;

import Tile.TileManager;
import entity.NPC;
import util.FileIO;
import util.SoundSystem;
import entity.Player;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class GamePanel extends Canvas implements Runnable {

    // ===== SCREEN SETTINGS =====
    final int originalTileSize = 32;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;

    public int maxScreenCollum = 9;
    public int maxScreenRow = 9;
    public final int screenWidth = tileSize * maxScreenCollum;
    public final int screenHeight = tileSize * maxScreenRow;

    // ===== WORLD SETTINGS =====
    public final int maxWorldCol = 30;
    public final int maxWorldRow = 30;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    int FPS = 60;

    public CollisionChecker colCheck = new CollisionChecker(this);
    TileManager tileM = new TileManager(this);
    FileIO io = new FileIO(this);
    SoundSystem soundSystem = new SoundSystem(io);
    KeyHandler keyH = new KeyHandler();

    public GUI gui = new GUI(this);
    Thread gameThread;

    public Player player = new Player(this, keyH);
    public NPC npc = new NPC(this);

    // ===== Constructor =====
    public GamePanel() {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(Color.BLACK);
        addKeyListener(keyH);
        setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
        soundSystem.playTrack("Resources/musicFiles/mainTheme.wav");
    }

    @Override
    public void run() {

        createBufferStrategy(2);   // DOUBLE BUFFER STRATEGY
        BufferStrategy bs = getBufferStrategy();

        double drawInterval = 1000000000.0 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            long currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                render(bs);
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        player.update();
        npc.update();
    }

    // ===== NEW RENDER METHOD (no screen tearing!) =====
    private void render(BufferStrategy bs) {

        do {
            do {
                Graphics2D g2 = (Graphics2D) bs.getDrawGraphics();

                // CLEAR SCREEN
                g2.clearRect(0, 0, screenWidth, screenHeight);

                // DRAW GAME
                tileM.draw(g2);
                npc.draw(g2);
                player.draw(g2);
                gui.draw(g2);

                g2.dispose();

            } while (bs.contentsRestored());

            bs.show();
            Toolkit.getDefaultToolkit().sync(); // reduces tearing on some systems

        } while (bs.contentsLost());
    }
}
