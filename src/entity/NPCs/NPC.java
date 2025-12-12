package entity.NPCs;

import Main.GUI;
import Main.GamePanel;
import entity.Entity;
import util.FileIO;

import java.awt.*;

public class NPC extends Entity {
    GamePanel gp;
    FileIO io;
    GUI gui;

    public NPC(GamePanel gp, FileIO io, GUI gui) {
        this.gp = gp;
        this.io = io;
        this.gui = gui;
    }
    public void update(){}
    public void draw(Graphics g2){}
}
