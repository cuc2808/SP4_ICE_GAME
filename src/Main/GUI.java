package Main;

import entity.Entity;

import java.awt.*;

public class GUI {

    GamePanel gp;
    Font fontA40;
    boolean hasMessage = false;
    String currentMessage;
    int actionCounter = 0;
    public boolean displayingMessage;
    Entity entity;

    public GUI(GamePanel gp){
        this.gp = gp;
        fontA40 = new Font("Arial", Font.PLAIN,40);
    }
    public void draw(Graphics2D g2) {
        g2.setFont(fontA40);
        g2.setColor(Color.blue);

        if (hasMessage && currentMessage != null) {
            int screenX = this.entity.worldX- gp.player.worldX + gp.player.screenX;
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
}
