package entity.NPCs;

import Main.GUI;
import Main.GamePanel;
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
        worldX = gp.tileSize*4;
        worldY = gp.tileSize*3;
        direction = "right";
        movementSpeed = 2;

    }
    public void update(){
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
        //check player er 2 tiles inden for player
        int aroundNPCPositiveX = this.worldX + 2*gp.tileSize;
        int aroundNPCNegativeX = this.worldX - 2*gp.tileSize;
        int aroundNPCPositiveY = this.worldY + 2*gp.tileSize;
        int aroundNPCNegativeY = this.worldY - 2*gp.tileSize;

        //match med player
        if (gp.player.worldX < aroundNPCPositiveX &&
            gp.player.worldX > aroundNPCNegativeX &&
            gp.player.worldY < aroundNPCPositiveY &&
            gp.player.worldY > aroundNPCNegativeY) {
            // check om M key er pressed
            if (gp.player.keyH.ePressed == true && gp.gui.displayingMessage == false) {
                // display msg på skærm
                dspNPCMsg();
                // ikke spamable eller holdt inde
                gp.player.keyH.ePressed = false;
            }
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
