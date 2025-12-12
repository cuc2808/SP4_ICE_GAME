package entity.NPCs;

import Main.GUI;
import Main.GamePanel;
import util.FileIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class NPC_Flamingo extends NPC {
    public BufferedImage left,right;
    protected int actionCounter = 0;
    protected boolean hasMainEvent;
    protected boolean hasSideEvent;
    protected int messageCounter;
    protected ArrayList<String> allMessages;
    protected String currentMessage;
    int screenX,screenY;

    public NPC_Flamingo(GamePanel gp, FileIO io, GUI gui) {
        super(gp,io,gui);
        this.messageCounter = 0;

        loadNPCImage();
        loadMessages("Resources/Files/NPCs/npctalk.csv");
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
            if (gp.player.keyH.mPressed == true && gp.gui.displayingMessage == false) {
                // display msg på skærm
                dspNPCMsg();
                // ikke spamable eller holdt inde
                gp.player.keyH.mPressed = false;
            }
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
    public void dspNPCMsg() {
        //set current message
        currentMessage = allMessages.get(messageCounter);
        // giv current message til GUI
        gui.getMessage(currentMessage);
        //tæl message counter op
        messageCounter++;

        //hvis messageCounter er længere end arraylisten start forfra
        if (messageCounter > allMessages.size()){
            messageCounter = 0;
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
                String[] array = line.split(";");
                for (int i = 0; i < array.length; i++){
                    allMessages.add(array[i]);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
