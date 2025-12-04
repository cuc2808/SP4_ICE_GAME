package entity;

import Main.GamePanel;
import Main.KeyHandler;

import java.awt.*;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        name = "player";
        id += 1;
        setDefaultValues();
    }
public void setDefaultValues(){
    Xpos = 448;        //We create a starting value for the player's x coordinate.
    Ypos = 448;       //We create a starting value for the player's y coordinate.
    movementspeed = 5;      //The player's set movementSpeed. This will affect the player's position as it moves.

}
public void update(){
    if(keyH.upPressed == true) {
        Ypos -= movementspeed;
    } else if (keyH.downPressed == true){
        Ypos += movementspeed;
    } else if (keyH.leftPressed == true) {
        Xpos -= movementspeed;
    } else if (keyH.rightPressed == true){
        Xpos += movementspeed;
    }
}
public void draw(Graphics g2){
    g2.setColor(Color.black);

    g2.fillRect(Xpos, Ypos, gp.tileSize,gp.tileSize);
}

}
