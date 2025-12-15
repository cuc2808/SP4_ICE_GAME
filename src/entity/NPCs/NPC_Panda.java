package entity.NPCs;

import Main.GUI;
import Main.GamePanel;
import Main.GameState;
import util.FileIO;

import java.awt.*;
import java.awt.image.BufferedImage;

public class NPC_Panda extends NPC{
    public BufferedImage front_idle,front_left,front_right,
            away_idle,away_left,away_right,left_idle,left_left,left_right,
            right_idle,right_left,right_right;
    protected int actionCounter = 0;


    public NPC_Panda(GamePanel gp, FileIO io, GUI gui) {
        super(gp, io, gui);
        this.messageCounter = 0;

        loadNPCImage();
        loadMessages("Resources/Files/NPCs/PandaMessages.csv");
        setDefaultValues();
        unlockMainEvent();
        unlockSideEvent();

    }
    public void setDefaultValues(){
        worldX = gp.tileSize*15;
        worldY = gp.tileSize*18;
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


        //spriteCounter også brugt i player
        if ((actionCounter%2)==0) {
            spriteCounter++;
        }
        if (spriteCounter == 8) {
            spriteNumber++;
            spriteCounter = 0;
            if (spriteNumber == 5) {
                spriteNumber = 1;
            }
        }
    }
    public void loadNPCImage(){
        //loading Front
        front_idle = io.readImage("/entity/NPCs/NPCImages/Panda/Panda_front.png");
        front_left = io.readImage("/entity/NPCs/NPCImages/Panda/Panda_front_Lfoot.png");
        front_right = io.readImage("/entity/NPCs/NPCImages/Panda/Panda_front_Rfoot.png");
        //loading look away
        away_idle = io.readImage("/entity/NPCs/NPCImages/Panda/Panda_away.png");
        away_left = io.readImage("/entity/NPCs/NPCImages/Panda/Panda_away_Lfoot.png");
        away_right = io.readImage("/entity/NPCs/NPCImages/Panda/Panda_away_Rfoot.png");
        //loading left
        left_idle = io.readImage("/entity/NPCs/NPCImages/Panda/Panda_left.png");
        left_left = io.readImage("/entity/NPCs/NPCImages/Panda/Panda_left_Lfoot.png");
        left_right = io.readImage("/entity/NPCs/NPCImages/Panda/Panda_left_Rfoot.png");
        //loading right
        right_idle = io.readImage("/entity/NPCs/NPCImages/Panda/Panda_right.png");
        right_left = io.readImage("/entity/NPCs/NPCImages/Panda/Panda_right_Lfoot.png");
        right_right = io.readImage("/entity/NPCs/NPCImages/Panda/Panda_right_Rfoot.png");
    }
    public void draw(Graphics g2){

        if (isNpcOnScreen()) {
            g2.drawImage(drawNPCDirection(), screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
    public BufferedImage drawNPCDirection() {

        BufferedImage npcImage = null;

        switch (direction) {
            default:
                npcImage = null;
                break;
            case "up", "upLeft", "upRight":
                if (spriteNumber == 1) {
                    npcImage = front_left;
                } else if (spriteNumber == 2) {
                    npcImage = front_right;
                } else if (spriteNumber == 3) {
                    npcImage = front_left;
                } else if (spriteNumber == 4) {
                    npcImage = front_right;
                }
                break;
            case "down", "downLeft", "downRight":
                if (spriteNumber == 1) {
                    npcImage = away_left;
                } else if (spriteNumber == 2) {
                    npcImage = away_right;
                } else if (spriteNumber == 3) {
                    npcImage = away_left;
                } else if (spriteNumber == 4) {
                    npcImage = away_right;
                }
                break;
            case "left":
                if (spriteNumber == 1) {
                    npcImage = left_left;
                } else if (spriteNumber == 2) {
                    npcImage = left_right;
                } else if (spriteNumber == 3) {
                    npcImage = left_left;
                } else if (spriteNumber == 4) {
                    npcImage = left_right;
                }
                break;
            case "right":
                if (spriteNumber == 1) {
                    npcImage = right_left;
                } else if (spriteNumber == 2) {
                    npcImage = right_right;
                } else if (spriteNumber == 3) {
                    npcImage = right_left;
                } else if (spriteNumber == 4) {
                    npcImage = right_right;
                }
                break;
            case "idle":
                npcImage = null;
                break;
        }
        return npcImage;
    }
}
