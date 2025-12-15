package entity.NPCs;

import Main.GUI;
import Main.GamePanel;
import entity.Entity;
import util.FileIO;

import java.awt.*;
import java.util.ArrayList;

public class NPC extends Entity {
    GamePanel gp;
    FileIO io;
    GUI gui;
    int screenX,screenY;
    protected boolean hasMainEvent;
    protected boolean hasSideEvent;
    protected ArrayList<String> allMessages;
    protected String currentMessage;
    protected int messageCounter;
    String npcIdleMessage = "Interact with E!";
    public boolean playerAroundNPC;

    public NPC(GamePanel gp, FileIO io, GUI gui) {
        this.gp = gp;
        this.io = io;
        this.gui = gui;

        dspIdleMsg();
    }
    public void setDefaultValues(){}
    public void update(){}
    public void draw(Graphics g2){}
    public boolean isNpcOnScreen(){
        screenX = worldX - gp.player.worldX + gp.player.screenX;
        screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            return true;
        }
        return false;
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
        allMessages = io.getMessagesNPC(location);
    }
    public void dspIdleMsg(){
        if (isNpcOnScreen()) {
            gui.getMessage(this, npcIdleMessage);
        }
    }
    public void dspNPCMsg() {
        //set current message
        currentMessage = allMessages.get(messageCounter);
        // giv current message til GUI
        gui.getMessage(this,currentMessage);
        //tæl message counter op
        messageCounter++;

        //hvis messageCounter er længere end arraylisten start forfra
        if (messageCounter > allMessages.size()){
            messageCounter = 0;
        }
    }
    public boolean checkPlayerAroundNpc(){
        int aroundNPCPositiveX = this.worldX + 2*gp.tileSize;
        int aroundNPCNegativeX = this.worldX - 2*gp.tileSize;
        int aroundNPCPositiveY = this.worldY + 2*gp.tileSize;
        int aroundNPCNegativeY = this.worldY - 2*gp.tileSize;

        //match med player
        if (gp.player.worldX < aroundNPCPositiveX &&
                gp.player.worldX > aroundNPCNegativeX &&
                gp.player.worldY < aroundNPCPositiveY &&
                gp.player.worldY > aroundNPCNegativeY) {
            return true;
        }
        return false;
    }
    public String getNpcIdleMessage() {
        return npcIdleMessage;
    }

}
