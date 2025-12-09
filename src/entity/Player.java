package entity;

import Main.GamePanel;
import Main.KeyHandler;
import util.FileIO;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity{

    public BufferedImage up1,up2,up3,down1,down2,down3,left1,left2,left3,right1,right2,right3;
    public BufferedImage idle;

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

        solidArea = new Rectangle();
        solidArea.x = 30;
        solidArea.y = 30;
        solidArea.width = 20;
        solidArea.height = 60;
        setDefaultValues();
        loadPlayerImage();

    }
public void setDefaultValues(){
    worldX = gp.tileSize * 15;        //We create a starting value for the player's x coordinate.
    worldY = gp.tileSize * 15;       //We create a starting value for the player's y coordinate.
    movementSpeed = 0;      //The player's movementSpeed. This will affect how fast the player changes position.
    walkSpeed = 5;      //This is the set walkSpeed, when the player isn't sprinting.
    sprintSpeed = 10; //The player's set sprintSpeed, that activates when pressing shift.

}

public void loadPlayerImage(){


    try {
        idle = util.readImage("/playerImages/Player1IdleDown.png");

        up1 = util.readImage("/playerImages/walk/Player1WalkUP1.png");
        up2 = util.readImage("/playerImages/walk/Player1WalkUP3.png");
        up3 = util.readImage("/playerImages/walk/Player1WalkUP2.png");

        down1 = util.readImage("/playerImages/walk/Player1WalkDown1.png");
        down2 = util.readImage("/playerImages/walk/Player1WalkDown2.png");
        down3 = util.readImage("/playerImages/walk/Player1WalkDown3.png");

        left1 = util.readImage("/playerImages/walk/Player1WalkLeft1.png");
        left2 = util.readImage("/playerImages/walk/Player1WalkLeft2.png");
        left3 = util.readImage("/playerImages/walk/Player1WalkLeft3.png");

        right1 = util.readImage("/playerImages/walk/Player1WalkRight1.png");
        right2 = util.readImage("/playerImages/walk/Player1WalkRight2.png");
        right3 = util.readImage("/playerImages/walk/Player1WalkRight3.png");

    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}



    public void update() {

        // 1. Find ud af hvilken retning spilleren prøver at gå

        if (keyH.ShiftPressed == true) {
            movementSpeed = sprintSpeed;
        } else {
            movementSpeed = walkSpeed;
        }

        if (keyH.upPressed) {
            direction = "up";
            if (keyH.leftPressed) {
                direction = "upLeft";
            } else if (keyH.rightPressed) {
                direction = "upRight";
            }
        } else if (keyH.downPressed) {
            direction = "down";
            if (keyH.leftPressed) {
                direction = "downLeft";
            } else if (keyH.rightPressed) {
                direction = "downRight";
            }
        } else if (keyH.leftPressed) {
            direction = "left";
        } else if (keyH.rightPressed) {
            direction = "right";
        } else {
            return;
        }

        // 2. Collision check
        collisionOn = false;
        gp.colCheck.checkTile(this);

        // 3. Hvis ingen collision → bevæg spilleren
        if (!collisionOn) {
            switch (direction) {
                case "up":
                    worldY -= movementSpeed;
                    break;
                case "upLeft":
                    worldY -= movementSpeed / diagonalMultiplier;
                    worldX -= movementSpeed / diagonalMultiplier;
                    break;
                case "upRight":
                    worldY -= movementSpeed / diagonalMultiplier;
                    worldX += movementSpeed / diagonalMultiplier;
                    break;

                case "down":
                    worldY += movementSpeed;
                    break;
                case "downLeft":
                    worldY += movementSpeed / diagonalMultiplier;
                    worldX -= movementSpeed / diagonalMultiplier;
                    break;
                case "downRight":
                    worldY += movementSpeed / diagonalMultiplier;
                    worldX += movementSpeed / diagonalMultiplier;
                    break;

                case "left":
                    worldX -= movementSpeed;
                    break;
                case "right":
                    worldX += movementSpeed;
                    break;
            }
        }

        //      ===== Sprite Counter and Number changer - so we can tell what sprite the painter should paint.

        if(!keyH.ShiftPressed) {
            spriteCounter++;
        } else if (keyH.ShiftPressed){
            spriteCounter++;
            spriteCounter++;
            spriteCounter++;
            spriteCounter++;
        }

        if (spriteCounter >= 8) {
            spriteNumber++;
            spriteCounter = 0;
            if(spriteNumber >= 4){
                spriteNumber = 1;
            }
        }
    }

    public BufferedImage drawPlayerDirection(){

        BufferedImage playerImage = idle;

        switch(direction) {
            default: playerImage = idle;
            break;
            case "up","upLeft","upRight": if(spriteNumber == 1) {
                playerImage = up1;
            } else if (spriteNumber == 2){
                playerImage = up2;
            } else if (spriteNumber == 3){
                playerImage = up3;
            } else if (spriteNumber == 4){
                playerImage = up2;
            }
            break;
            case "down","downLeft","downRight": if(spriteNumber == 1) {
                playerImage = down1;
            } else if (spriteNumber == 2){
                playerImage = down2;
            } else if (spriteNumber == 3){
                playerImage = down3;
            }else if (spriteNumber == 4) {
                playerImage = down2;
            }
                break;
            case "left": if(spriteNumber == 1) {
                playerImage = left1;
            } else if (spriteNumber == 2){
                playerImage = left2;
            } else if (spriteNumber == 3){
                playerImage = left3;
            }else if (spriteNumber == 4) {
                playerImage = left2;
            }
                break;
            case "right": if(spriteNumber == 1) {
                playerImage = right1;
            } else if (spriteNumber == 2){
                playerImage = right2;
            } else if (spriteNumber == 3){
                playerImage = right3;
            }else if (spriteNumber == 4) {
                playerImage = right2;
            }
                break;
            case "idle":  playerImage = idle;
            break;
        }
        return playerImage;
    }

public void draw(Graphics g2){


    g2.drawImage(drawPlayerDirection(),screenX, screenY, gp.tileSize, gp.tileSize, null);


        //g2.drawImage(playerImage, screenX, screenY, gp.tileSize, gp.tileSize, null);


}

}
