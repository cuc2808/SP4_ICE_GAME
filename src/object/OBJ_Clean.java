package object;

import Main.GamePanel;
import util.FileIO;

public class OBJ_Clean extends superObject {

    public OBJ_Clean(GamePanel gp, FileIO io) {
        super(gp,io);
        this.name = "clean3";
        loadImage(this.name);

        collision = false;

    }
}
