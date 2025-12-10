package Main;

import java.awt.*;

public class GUI {

    GamePanel gp;
    Font fontA40;
    boolean hasMessage = false;
    String currentMessage;
    int actionCounter = 0;

    public GUI(GamePanel gp){
        this.gp = gp;
        fontA40 = new Font("Arial", Font.PLAIN,40);
    }
    public void draw(Graphics2D g2) {
        g2.setFont(fontA40);
        g2.setColor(Color.blue);

        if (hasMessage) {
            int screenX = gp.npc.worldX- gp.player.worldX + gp.player.screenX;
            int screenY = gp.npc.worldY - gp.player.worldY + gp.player.screenY;
            g2.drawString(currentMessage, screenX, screenY + (gp.tileSize * 2) - (gp.tileSize / 4));
        }
    }
    public void update(){
        if (hasMessage) {
            actionCounter++;
            if (actionCounter == 1200){
                actionCounter = 0;
                hasMessage = false;
            }
        }
    }
    public void getMessage(String message){
        this.hasMessage = true;
        this.currentMessage = message;
    }
}
