package object;

import Main.GamePanel;
import util.FileIO;

public class OBJ_Dust2 extends superObject {

    public OBJ_Dust2(GamePanel gp, FileIO io) {
        super(gp,io);
        this.name = "dust2";
        loadImage(this.name);
    }
}
