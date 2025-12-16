package Main;

import entity.Entity;
import entity.NPCs.NPC;
import entity.NPCs.NPC_Flamingo;
import entity.Player;
import util.FileIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static entity.Player.enemyCounter;
import static entity.Player.objectsCleaned;

public class GUI {

    GamePanel gp;
    FileIO io;
    boolean hasMessage = false;
    String currentMessage;
    public boolean displayingMessage;
    NPC npc;
    Player p;
    public static int dustNeedRemoved = 9;
    public static int enemyNeedRemoved = 1;

    //Resources for the GUI
    public Font fontA40;
    public BufferedImage textBox;
    public Font ArcadeFont;
    public BufferedImage cleanUpIcon;
    public BufferedImage enemyIcon;

    public GUI(GamePanel gp, FileIO io){
        this.gp = gp;
        this.io = io;
        fontA40 = new Font("Arial", Font.BOLD,32);

        ArcadeFont = FontLoader.loadFont(32f);

        loadTextBox();
    }
    public void draw(Graphics2D g2) {
        g2.setFont(fontA40);
        g2.setColor(Color.white);

        if (gp.gameState != GameState.INTERACT && hasMessage && currentMessage != null) {
            drawMsgOnNpc(g2);
        }

        if (gp.gameState == GameState.INTERACT && currentMessage != null) {
            if (gp.player.keyH.ePressed) {
                npc.dspNPCMsg();
                gp.player.keyH.ePressed = false;
            }
            drawTextBoxWithMessage(g2, currentMessage);
        }
        if (gp.gameState != GameState.INTERACT) {
            resetMessageAndNPC();
        }

        // PLAYER INTERFACE GUI
        if(Player.showObjectsCleaned) {
            drawCounter(g2);
        }
        if(Player.showVirusRemoved) {
            drawEnemyCounter(g2);
        }
    }
    public void update(){
    }
    public void getMessage(NPC npc,String message){
        //besked modtaget fra entity
        this.npc = npc;
        //modtaget en besked
        this.hasMessage = true;
        //besked sat til currentMessage
        this.currentMessage = message;
    }
    public void loadTextBox(){
        textBox = io.readImage("/playerImages/TextBox2.png");
    }
    public void drawTextBoxWithMessage(Graphics2D g2, String message){
        String string = message;

        //Billedet lavet til at passe til skærm, så kan sætte det i x = 0, y = 0
        g2.drawImage(textBox,0,0,null);

        //instansiere x til midten af skærmen
        int x = gp.screenWidth/2;
        //sætter postionen optimalt i forhold til Message længde
        x = calculatatorXForMessage(x,message);
        //y til midten af textBox
        int y = gp.screenHeight/2+gp.tileSize*3;



        //tegner String på skærm
        g2.drawString(string,x,y);
    }
    public int calculatatorXForMessage(int x,String message){
        //instansiere x til midten af skærmen

        //convert font size til pixels
        int fontSize = fontA40.getSize()/2;

        //Halvdelen af Stringens længde ganget med størrelse for hvert bogstav
        int halfOfStringLength = (message.length()/2)*fontSize;

        //lægger en fontSize til x fordi den starter i 0
        x = x+fontSize;

        //trækker halfdelen af antallet af bogstaver fra x, så det står i midten
        //lige gyldigt antallet af bogstaver
        x = x-halfOfStringLength;
        return x;
    }
    public void drawMsgOnNpc(Graphics g2){
        int screenX = this.npc.worldX - gp.player.worldX + gp.player.screenX;
        int screenY = this.npc.worldY - gp.player.worldY + gp.player.screenY;
        displayingMessage = true;

        //calculater i forhold til currentMessage Længde
        screenX = calculatatorXForMessage(screenX, currentMessage);
        g2.drawString(currentMessage, 50+screenX, screenY);
    }
    public void resetMessageAndNPC(){
        this.npc = null;
        this.hasMessage = false;
        this.currentMessage = null;
    }


    // PLAYER GUI

    public void drawCounter(Graphics2D g2){

        g2.drawImage(cleanUpIcon, 70, 70, gp.tileSize - 20, gp.tileSize - 20, null);
        if(p.showObjectsCleaned) {
            g2.setFont(ArcadeFont);

            if(objectsCleaned < 9) {
                g2.drawString(toString().valueOf(objectsCleaned + "/9"), 70 + gp.tileSize, 50 + gp.tileSize / 2 + 20);
            } else if (objectsCleaned == 9){
                g2.drawString(toString().valueOf("COMPLETE"), 70 + gp.tileSize, 50 + gp.tileSize / 2 + 20);
            }

            try {
                cleanUpIcon = io.readImage("/GUI_Images/trashbinIcon.png");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }

    }

    public void drawEnemyCounter(Graphics2D g2){

            try {
                enemyIcon = io.readImage("/GUI_Images/enemyIcon.png");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        g2.drawImage(enemyIcon, 70, 70 + 150, gp.tileSize - 20, gp.tileSize - 20, null);

        g2.setFont(ArcadeFont);

        if(enemyCounter != enemyNeedRemoved) {
            g2.drawString(toString().valueOf(enemyCounter) + "/" + enemyNeedRemoved, 70 + gp.tileSize, 50 + gp.tileSize / 2 + 20 + 150);

        } else if (enemyCounter == enemyNeedRemoved){
            g2.drawString(toString().valueOf("COMPLETE"), 70 + gp.tileSize, 50 + gp.tileSize / 2 + 20 + 150);
        }
    }

    public class FontLoader {

        public static Font loadFont(float size) {
            try {
                InputStream is = FontLoader.class
                        .getResourceAsStream("/Fonts/Pixel Digivolve.otf");

                Font baseFont = Font.createFont(Font.TRUETYPE_FONT, is);
                return baseFont.deriveFont(size);

            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
                return new Font("SansSerif", Font.PLAIN, (int) size);
            }
        }
    }
}
