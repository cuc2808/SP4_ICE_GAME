package entity;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class NPC extends Entity {
    GamePanel gp;
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

    }
    public void draw(Graphics g2){

        BufferedImage playerImage = null;

        try {
            playerImage = ImageIO.read(getClass().getResourceAsStream("/playerImages/Flamongo1.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        g2.drawImage(playerImage, worldX, worldY, gp.tileSize, gp.tileSize, null);


    }
    public void interact(){

    }
    public void unlockMainEvent(){

    }
    public void unlockSideEvent(){

    }
}
