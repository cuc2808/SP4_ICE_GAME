package Main;

import entity.Entity;

import java.awt.*;

public class GUI {

    GamePanel gp;
    Font fontA40;
    boolean hasMessage = false;
    String currentMessage;

    public GUI(GamePanel gp){
        this.gp = gp;
        fontA40 = new Font("Arial", Font.PLAIN,40);
    }
    public void draw(Graphics2D g2) {

        g2.setFont(fontA40);
        g2.setColor(Color.blue);
        if (hasMessage){
            g2.drawString(currentMessage,gp.screenWidth/4,gp.screenHeight-(gp.tileSize*2)-(gp.tileSize/4));
        }
    }
    public void getMessage(String message){
        hasMessage = true;
        currentMessage = message;
    }
}
