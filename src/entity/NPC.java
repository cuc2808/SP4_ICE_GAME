package entity;

import Main.GUI;
import Main.GamePanel;
import util.FileIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class NPC extends Entity {
    GamePanel gp;
    FileIO io;
    GUI gui;
    public BufferedImage left,right;
    public String direction;
    protected int actionCounter = 0;
    protected boolean isInteractedWith;
    protected boolean hasMainEvent;
    protected boolean hasSideEvent;
    protected int messageCounter;
    protected ArrayList<String> allMessages;
    protected String currentMessage;
    int screenX,screenY;

    public NPC(GamePanel gp, FileIO io, GUI gui) {
        this.gp = gp;
        this.io = io;
        this.gui = gui;
        this.messageCounter = 1;

        loadNPCImage();
        loadMessages("Resources/Files/NPCs/npctalk.csv");
        setDefaultValues();
        interact();
        unlockMainEvent();
        unlockSideEvent();

        solidArea = new Rectangle();
        solidArea.x = worldX;
        solidArea.y = worldY;
        solidArea.width = 20;
        solidArea.height = 60;
    }

    public void setDefaultValues(){
        worldX = 375;
        worldY = 250;
        movementSpeed = 2;
        currentMessage = allMessages.get(messageCounter);

    }
    public void update(){
        actionCounter++;
        if (actionCounter < 63) {
            this.direction = "right";
        } else {
            this.direction = "left";
        }
        collisionOn = false;
        gp.colCheck.checkTile(this);
        if (this.collisionOn) {
            System.out.println(this.collisionOn);
        }
        if(!collisionOn) {
            if (actionCounter < 63) {
                worldX += movementSpeed;
                solidArea.x = worldX;
            } else {
                worldX -= movementSpeed;
                solidArea.x = worldX;
            }
        }
        if (actionCounter == 120) {
            actionCounter = 0;
        }
    }
    public void loadNPCImage(){
       left = io.readImage("/playerImages/Flamongo1.png");
       right = io.readImage("/playerImages/Flamongo2.png");
    }
    public void draw(Graphics g2){

        screenX = worldX - gp.player.worldX + gp.player.screenX;
        screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldX + gp.player.screenX) {
            if (direction.equals("right")) {
                g2.drawImage(right, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
            if (direction.equals("left")) {
                g2.drawImage(left, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
        }


    }
    public void interact(){
        if (isInteractedWith) {
            gui.getMessage(currentMessage);
            messageCounter++;
        }
    }
    public void unlockMainEvent(){
        if (hasMainEvent) {

        }

    }
    public void unlockSideEvent(){
        if (hasSideEvent){

        }

    }
    public void loadMessages(String location){
        allMessages = new ArrayList<>();
        try {
            File file = new File(location);
            Scanner scan = new Scanner(file);
            scan.nextLine(); // skips header
            while (scan.hasNextLine()){
                String line = scan.nextLine();
                String[] array = line.split(",");
                for (int i = 0; i < array.length; i++){
                    allMessages.add(array[i]);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
