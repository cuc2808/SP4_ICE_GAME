package object;

import Main.GamePanel;
import util.FileIO;

import java.awt.*;
import java.awt.image.BufferedImage;

public class superObject {

    GamePanel gp;
    FileIO io;
    public BufferedImage image;

    public String name = "dust";
    public boolean collision = false;
    public int worldX,worldY;
    public Rectangle solidArea = new Rectangle(0,0,64,64);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public superObject(GamePanel gp, FileIO io) {
        this.gp = gp;
        this.io = io;


    }
    public void setDefaultValues(){

    }
    public void loadImage(String name){
        try {
            image = io.readImage("/object/images/"+name+".png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2, GamePanel gp){
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        g2.drawImage(image, screenX, screenY + 28, gp.tileSize, gp.tileSize, null);
    }


}
