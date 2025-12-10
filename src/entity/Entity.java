package entity;

import java.awt.*;

public class Entity {
    public int worldX, worldY;
    String name;
    int id = 0;
    int Xpos;
    int Ypos;
    public int movementSpeed;
    public int walkSpeed;
    public int sprintSpeed;
    double diagonalMultiplier = 1.414; //For when going diagonally. We need to divide alle the instances of moving by 1.414
    public Rectangle solidArea = new Rectangle();
    public boolean collisionOn = false;
    public String direction = "idle";
    public int spriteCounter = 0;    //Counter we need to switch to the next sprite in an animation.
    public int spriteNumber = 1;    //The number of the sprite in the collection.

}
