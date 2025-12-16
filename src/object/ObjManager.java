package object;

import Main.GamePanel;
import util.FileIO;

public class ObjManager {

    GamePanel gp;
    FileIO io;

    public ObjManager(GamePanel gp, FileIO io) {
        this.gp = gp;
        this.io = io;

    }
    public void setObject(String mapName){

        // 1st Map
        if(mapName == "/tile/maps/levelOne.txt"){
        gp.objArray[0] = new OBJ_Dust(gp,io);
        gp.objArray[0].worldX = 16 * gp.tileSize;
        gp.objArray[0].worldY = 15 * gp.tileSize;

        gp.objArray[1] = new OBJ_Dust2(gp,io);
        gp.objArray[1].worldX = 17 * gp.tileSize;
        gp.objArray[1].worldY = 19 * gp.tileSize;

        gp.objArray[2] = new OBJ_Dust(gp,io);
        gp.objArray[2].worldX = 17 * gp.tileSize;
        gp.objArray[2].worldY = 17 * gp.tileSize;

        gp.objArray[3] = new OBJ_Dust(gp,io);
        gp.objArray[3].worldX = 19 * gp.tileSize;
        gp.objArray[3].worldY = 17 * gp.tileSize;
        }


    }

    public void updateObjects(){
        if(gp.objArray[0] == null){
            gp.objArray[0] = new OBJ_Clean(gp,io);
            gp.objArray[0].worldX = 14 * gp.tileSize;
            gp.objArray[0].worldY = 15 * gp.tileSize;
        }
    }

    public void resetAllObjects(){
        for(int i = 0; i < gp.objArray.length; i++){
            gp.objArray[i] = null;
        }
    }
}


