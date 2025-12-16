package Main;
import entity.Player;

import java.awt.*;
import java.util.Objects;

import static entity.Player.showVirusRemoved;

public class CutsceneManager {
    Color cutsceneTextColor = Color.WHITE;
    String cutsceneText ="";
    GamePanel gp;

    boolean cutsceneActive = false;

    // Flyttet fra GamePanel
    public static boolean change = false;
    public boolean cutsceneDone = false;
    boolean isFading = false;
    float fadeAlpha = 0.0f;
    float fadeSpeed = 0.02f;

    public int cutsceneStep = 0;
    int cutsceneTimer = 0;

    public CutsceneManager(GamePanel gp) {
        this.gp = gp;
    }

    public void startCutscene(boolean needChange) {
        gp.gameState = GameState.CUTSCENE;
        change = needChange;
        cutsceneActive = true;
        isFading = true;
        fadeAlpha = 0;
        cutsceneStep = 0;
        cutsceneTimer = 0;

        if (!Objects.equals(gp.tileM.mapName, "/tile/maps/BlueMap")) {
            cutsceneText = "Entering CPU...";
            cutsceneTextColor = new Color (0, 170, 255);
        } else {
            cutsceneText = "Entering Firewall...";
            cutsceneTextColor = new Color (255, 140, 0);
        }
        gp.player.movementSpeed = 0; // Freeze
    }

    public boolean isActive() {
        return cutsceneActive;
    }

    public void update() {

        if (!cutsceneActive) return;

        switch (cutsceneStep) {

            case 0: // Fade ind
                fadeAlpha += fadeSpeed;
                if (fadeAlpha >= 1) {
                    fadeAlpha = 1;
                    cutsceneTimer = 0;
                    cutsceneStep++;
                }
                break;

            case 1: // NPC snakker
                cutsceneTimer++;
                if (cutsceneTimer == 60) {
                    //gp.npcManager.getNpc(0).speak();
                }
                if (cutsceneTimer > 180) {
                    cutsceneTimer = 0;
                    cutsceneStep++;
                }
                break;

            case 2: // Fade ud
                    cutsceneDone = true;
                    if(gp.cutsceneManager.cutsceneDone  == true && change == true) {
                        if (Objects.equals(gp.tileM.mapName, "/tile/maps/levelOne.txt")) {
                            gp.tileM.changeMap("/tile/maps/BlueMap", "Resources/musicFiles/Untitled - 13_12_2025, 13.30.wav");
                            change = false;
                            showVirusRemoved = true;
                        } else if (gp.tileM.mapName.equals("/tile/maps/BlueMap")) {
                            gp.tileM.changeMap("/tile/maps/levelThree.txt", "Resources/musicFiles/lavaWorld_1.wav");
                            change = false;
                        }
                    }
                fadeAlpha -= fadeSpeed;
                if (fadeAlpha <= 0) {
                    fadeAlpha = 0;

                    // Reset efter cutscene
                    isFading = false;
                    gp.player.movementSpeed = gp.player.walkSpeed;
                    cutsceneActive = false;

                    gp.gameState = GameState.PLAY;
                }
                break;
        }
    }

    public void draw(Graphics2D g2) {
        if (!cutsceneActive) return;

        // Fade overlay
        if (isFading) {
            Graphics2D g2d = (Graphics2D) g2.create();
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fadeAlpha));
            g2d.setColor(Color.BLACK);
            g2d.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            // 👇 TEKST
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
            g2d.setColor(cutsceneTextColor);
            g2d.setFont(new Font("Arial", Font.BOLD, 36));

            FontMetrics fm = g2d.getFontMetrics();
            int textX = (gp.screenWidth - fm.stringWidth(cutsceneText)) / 2;
            int textY = gp.screenHeight / 2;

            g2d.drawString(cutsceneText, textX, textY);

            g2d.dispose();
        }
    }

}
