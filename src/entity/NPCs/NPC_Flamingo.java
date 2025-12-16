package entity.NPCs;

import Main.GUI;
import Main.GamePanel;
import Main.GameState;
import util.FileIO;

import java.awt.*;
import java.awt.image.BufferedImage;

public class NPC_Flamingo extends NPC {
    public BufferedImage left,right;
    protected int actionCounter = 0;

    public NPC_Flamingo(GamePanel gp, FileIO io, GUI gui) {
        super(gp,io,gui);
        this.messageCounter = 0;

        loadNPCImage();
        loadMessages("Resources/Files/NPCs/FlamingoMessages.csv");
        setDefaultValues();
        unlockMainEvent();
        unlockSideEvent();
    }

    public void setDefaultValues(){
        worldX = gp.tileSize*24;
        worldY = gp.tileSize*30;
        direction = "right";
        movementSpeed = 2;

    }
    public void update(){
        dspIdleMsg();
        if (checkPlayerAroundNpc()){
            if(gp.player.keyH.ePressed) {
                if (gp.gameState != GameState.INTERACT) {
                    gp.gameState = GameState.INTERACT;
                    dspNPCMsg();
                    gp.player.keyH.ePressed = false;
                }
            }
        }
        actionCounter++;
        collisionOn = false;
        gp.colCheck.checkTile(this);
        if(!collisionOn) {
            if (direction == "right"){
                worldX += movementSpeed;
            }
            if (direction == "left"){
                worldX -= movementSpeed;
            }
        }
        if (actionCounter == 120) {
            if (direction == "right"){
                direction = "left";
            } else if (direction == "left"){
                direction = "right";
            }
            actionCounter = 0;
        }
    }
    public void loadNPCImage(){
       left = io.readImage("/entity/NPCs/NPCImages/Flamingo/Flamongo1.png");
       right = io.readImage("/entity/NPCs/NPCImages/Flamingo/Flamongo2.png");
    }
    public void draw(Graphics g2){

        if (isNpcOnScreen()){
            if (direction.equals("right")) {
                g2.drawImage(right, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
            if (direction.equals("left")) {
                g2.drawImage(left, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
        }


    }

}
