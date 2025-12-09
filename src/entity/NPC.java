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
    protected boolean isInteractedWith = true;
    protected boolean hasMainEvent;
    protected boolean hasSideEvent;
    protected String currentMessage;
    protected int messageCounter;
    protected ArrayList<String> allMessages;

    public NPC(GamePanel gp, FileIO io, GUI gui) {
        this.gp = gp;
        this.io = io;
        this.gui = gui;

        loadNPCImage();
        setDefaultValues();
        loadMessages("Resources/Files/NPCs/npctalk.csv");
        interact();
        unlockMainEvent();
        unlockSideEvent();
    }

    public void setDefaultValues(){
        worldX = gp.screenWidth/4;
        worldY = gp.screenHeight/4;
        movementSpeed = 2;

    }
    public void update(){
        actionCounter++;
        if (actionCounter < 63){
            direction = "right";
            worldX += movementSpeed;
        } else {
            direction = "left";
            worldX -= movementSpeed;
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

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

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
            currentMessage = allMessages.get(messageCounter);
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
