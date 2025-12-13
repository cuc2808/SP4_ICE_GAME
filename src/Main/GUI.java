package Main;

import entity.Entity;
import entity.NPCs.NPC;
import entity.NPCs.NPC_Flamingo;
import util.FileIO;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GUI {

    GamePanel gp;
    FileIO io;
    Font fontA40;
    boolean hasMessage = false;
    String currentMessage;
    int actionCounter = 0;
    public boolean displayingMessage;
    Entity entity;
    NPC_Flamingo npc;
    BufferedImage textBox;

    public GUI(GamePanel gp, FileIO io){
        this.gp = gp;
        this.io = io;
        fontA40 = new Font("Arial", Font.PLAIN,40);

        loadImage("Resources/Files/GUI/TextBox2.png");
    }
    public void draw(Graphics2D g2) {
        g2.setFont(fontA40);
        g2.setColor(Color.blue);

        if (npc != null && npc.playerAroundNPC) {
            System.out.println("test1");
            g2.drawImage(textBox,gp.player.screenX,gp.player.screenY+(gp.tileSize*4),null);
            g2.drawString("Interact with E",gp.player.screenX,gp.player.screenY);
        }



        if (hasMessage && currentMessage != null) {
            int screenX = this.entity.worldX - gp.player.worldX + gp.player.screenX;
            int screenY = this.entity.worldY - gp.player.worldY + gp.player.screenY;
            displayingMessage = true;
            g2.drawString(currentMessage, screenX, screenY + (gp.tileSize * 2) - (gp.tileSize / 4));

        }
    }
    public void update(){
        if (hasMessage) {
            actionCounter++;
            if (actionCounter == 120){
                actionCounter = 0;
                displayingMessage = false;
                hasMessage = false;
            }
        }
    }
    public void getMessage(Entity entity,String message){
        //besked modtaget fra entity
        this.entity = entity;
        //modtaget en besked
        this.hasMessage = true;
        //besked sat til curretnMessage
        this.currentMessage = message;
    }
    public void setNPC(NPC_Flamingo npc){
        this.npc = npc;
    }
    public void loadImage(String path){
        textBox = io.readImage(path);
    }
}
