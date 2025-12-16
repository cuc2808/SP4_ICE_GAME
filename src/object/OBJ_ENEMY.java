package object;

import Main.GamePanel;
import util.FileIO;

public class OBJ_ENEMY extends superObject {

    public OBJ_ENEMY(GamePanel gp, FileIO io) {
        super(gp,io);
        this.name = "virus";
        loadImage(this.name);

        evil = true;
        dead = false;
        collision = true;
    }
}