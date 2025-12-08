package entity;

import Main.GamePanel;
import Main.KeyHandler;
import util.FileIO;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

public class Player extends Entity{
    GamePanel gp;
    FileIO util = new FileIO(gp);
    KeyHandler keyH;

        public final int screenX;
        public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        name = "player";
        id += 1;
        setDefaultValues();
    }
public void setDefaultValues(){
    worldX = gp.tileSize * 15;        //We create a starting value for the player's x coordinate.
    worldY = gp.tileSize * 15;       //We create a starting value for the player's y coordinate.
    movementspeed = 5;      //The player's set movementSpeed. This will affect the player's position as it moves.

}
public void update(){
    if(keyH.upPressed == true) {
        worldY -= movementspeed;
        if (keyH.leftPressed == true) {
            worldX -= movementspeed;
        }
        if (keyH.rightPressed == true) {
            worldX += movementspeed;
        }
    } else if (keyH.downPressed == true){
        worldY += movementspeed;
        if (keyH.leftPressed == true) {
            worldX -= movementspeed;
        }
        if (keyH.rightPressed == true) {
            worldX += movementspeed;
        }
    } else if (keyH.leftPressed == true) {
        worldX -= movementspeed;
        if (keyH.upPressed == true) {
            worldY -= movementspeed;
        }
        if (keyH.downPressed == true) {
            worldY += movementspeed;
        }
    } else if (keyH.rightPressed == true){
        worldX += movementspeed;
        if (keyH.upPressed == true) {
            worldY -= movementspeed;
        }
        if (keyH.downPressed == true) {
            worldY += movementspeed;
        }
    }
}

//public BufferedImage animation(){
//        int i = 0;
//
//}

public void draw(Graphics g2){

    BufferedImage playerImage = null;

    try {
        playerImage = ImageIO.read(getClass().getResourceAsStream("/playerImages/Flamongo1.png"));
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

        g2.drawImage(playerImage, screenX, screenY, gp.tileSize, gp.tileSize, null);


}

}
