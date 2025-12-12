package object;

import Main.GamePanel;
import util.FileIO;

import java.io.File;

public class ObjManager {

    GamePanel gp;
    FileIO io;

    public ObjManager(GamePanel gp, FileIO io) {
        this.gp = gp;
        this.io = io;


    }
    public void setObject(){

        gp.objArray[0] = new OBJ_Dust(gp,io);
        gp.objArray[0].worldX = 14 * gp.tileSize;
        gp.objArray[0].worldY = 15 * gp.tileSize;

        gp.objArray[1] = new OBJ_Dust2(gp,io);
        gp.objArray[1].worldX = 17 * gp.tileSize;
        gp.objArray[1].worldY = 19 * gp.tileSize;


    }
}
