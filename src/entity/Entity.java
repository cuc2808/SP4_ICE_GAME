package entity;

import java.awt.*;

public class Entity {
    public int worldX, worldY;
    String name;
    int id = 0;
    int Xpos;
    int Ypos;
   public int movementspeed;
    public Rectangle solidArea;
    public boolean collisionOn = false;
    public String direction;

}
