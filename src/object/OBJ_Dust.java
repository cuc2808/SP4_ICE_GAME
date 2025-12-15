package object;

import Main.GamePanel;
import util.FileIO;

public class OBJ_Dust extends superObject {

    public OBJ_Dust(GamePanel gp, FileIO io) {
        super(gp,io);
        this.name = "dust";
        loadImage(this.name);

        canPickUp = true;
        collision = true;
        cleanable = true;

    }
}
