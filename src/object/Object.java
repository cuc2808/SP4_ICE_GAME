package object;

import Main.GamePanel;
import util.FileIO;

import javax.swing.text.html.HTMLDocument;
import java.awt.image.BufferedImage;

public class Object {

    GamePanel gp;
    FileIO io;
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX,worldY;

    public Object (GamePanel gp, FileIO io) {
        this.gp = gp;
        this.io = io;

        loadImage();

    }
    public void setDefaultValues(){
    }
    public void loadImage(){
        image = io.readImage("Resouces/Files/ObjectImages/"+name+".png");
    }


}
