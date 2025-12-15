package Main;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BattleManager {
    BufferedImage battleBackground;
    GamePanel gp;
    BattleState battleState = BattleState.INTRO;

    int timer = 0;

    public BattleManager(GamePanel gp) {
        this.gp = gp;
        try {
            battleBackground = ImageIO.read(getClass().getResourceAsStream("/util/BattleBackground/levelTwoBackGround.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void startBattle() {
        battleState = BattleState.INTRO;
        timer = 0;
    }

    public void update() {
        switch (battleState) {
            case INTRO:
                timer++;
                if (timer > 60) {
                    battleState = BattleState.PLAYER_TURN;
                }
                break;

            case PLAYER_TURN:
                if (gp.keyH.ePressed) {
                    battleState = BattleState.ENEMY_TURN;
                }
                break;

            case ENEMY_TURN:
                timer++;
                if (timer > 60) {
                    endBattle();
                }
                break;
        }
    }

    public void draw(Graphics2D g2) {
        drawBackground(g2);
        drawUI(g2);
    }

    private void drawBackground(Graphics2D g2) {
       g2.drawImage(battleBackground, 0, 0, gp.screenWidth, gp.screenHeight, null);
    }

    private void drawUI(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.drawRect(20, gp.screenHeight - 140, gp.screenWidth - 40, 120);
        g2.drawString("FAKA YUR MATHER", 40, gp.screenHeight - 100);
    }

    private void endBattle() {
        gp.gameState = GameState.PLAY;
    }
}
