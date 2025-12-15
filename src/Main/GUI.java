package Main;

import entity.Entity;
import entity.NPCs.NPC_Flamingo;
import entity.Player;
import util.FileIO;

import java.awt.*;
import java.awt.image.BufferedImage;

import static entity.Player.objectsCleaned;

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
    Player p;

    public GUI(GamePanel gp, FileIO io){
        this.gp = gp;
        this.io = io;
        fontA40 = new Font("Arial", Font.BOLD,32);

        loadTextBox();
    }
    public void draw(Graphics2D g2) {
        g2.setFont(fontA40);
        g2.setColor(Color.white);

        if (npc != null && npc.playerAroundNPC) {
            String npcIdleMessage = npc.getNpcIdleMessage();
            drawTextBox(g2,npcIdleMessage);
        }



        if (hasMessage && currentMessage != null) {
            int screenX = this.entity.worldX - gp.player.worldX + gp.player.screenX;
            int screenY = this.entity.worldY - gp.player.worldY + gp.player.screenY;
            displayingMessage = true;
            g2.drawString(currentMessage, screenX, screenY + (gp.tileSize * 2) - (gp.tileSize / 4));

        }

        // PLAYER INTERFACE GUI
        drawCounter(g2);
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
        //besked sat til currentMessage
        this.currentMessage = message;
    }
    public void setNPC(NPC_Flamingo npc){
        this.npc = npc;
    }
    public void loadTextBox(){
        textBox = io.readImage("/playerImages/TextBox2.png");
    }
    public void drawTextBox(Graphics2D g2, String message){
        String string = message;

        //Billedet lavet til at passe til skærm, så kan sætte det i x = 0, y = 0
        g2.drawImage(textBox,0,0,null);

        //instansiere x til midten af skærmen, y til midten af textBox
        int x = gp.screenWidth/2;
        int y = gp.screenHeight/2+gp.tileSize*3;

        //convert font size til pixels
        int fontSize = fontA40.getSize()/2;

        //Halvdelen af Stringens længde ganget med størrelse for hvert bogstav
        int halfOfStringLength = (string.length()/2)*fontSize;

        //lægger en fontSize til x fordi den starter i 0
        x = x+fontSize;

        //trækker halfdelen af antallet af bogstaver fra x, så det står i midten
        //lige gyldigt antallet af bogstaver
        x = x-halfOfStringLength;

        //tegner String på skærm
        g2.drawString(string,x,y);
    }


    // PLAYER GUI

    public void drawCounter(Graphics2D g2){
        g2.drawString(toString().valueOf(objectsCleaned), 70, 70);

    }
}
