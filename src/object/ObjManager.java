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
    public void setObject (){

        gp.objArray[0] = new OBJ_Dust(gp,io);

    }
}
