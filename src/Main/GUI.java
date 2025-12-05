package Main;

import entity.Entity;

import java.awt.*;

public class GUI {

    GamePanel gp;
    Font fontA40;
    boolean hasMessage = false;
    int currentMessage;

    public GUI(GamePanel gp){
        this.gp = gp;
        fontA40 = new Font("Arial", Font.PLAIN,40);
    }
    public void draw(Graphics2D g2) {

        g2.setFont(fontA40);
        g2.setColor(Color.blue);
        if (hasMessage){
            g2.drawString(this.getMessage(gp.player),gp.screenWidth/4,gp.screenLength-(gp.tileSize*2)-(gp.tileSize/4));
        }
    }
    public String getMessage(Entity entity){
        return "Test"; // logik til at få besked
    }
}
