package entity;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class NPC extends Entity {
    GamePanel gp;
    protected int actionCounter = 0;
    protected boolean hasMainEvent;
    protected boolean hasSideEvent;

    public NPC(GamePanel gp) {
        this.gp = gp;

        setDefaultValues();
    }

    public void setDefaultValues(){
        worldX = gp.screenWidth/4;
        worldY = gp.screenHeight/4;
        movementspeed = 5;

    }
    public void update(){
        actionCounter++;
        if (actionCounter < 21){
            worldX += movementspeed;
        } else {
            worldX -= movementspeed;
        }
        if (actionCounter == 40) {
            actionCounter = 0;
        }
    }
    public void draw(Graphics g2){

        BufferedImage playerImage = null;

        try {
            playerImage = ImageIO.read(getClass().getResourceAsStream("/playerImages/Flamongo1.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldX + gp.player.screenX) {
            g2.drawImage(playerImage, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }


    }
    public void interact(){

    }
    public void unlockMainEvent(){

    }
    public void unlockSideEvent(){

    }
}
