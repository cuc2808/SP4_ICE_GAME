package entity.NPCs;

import Main.GUI;
import Main.GamePanel;
import object.OBJ_Dust;
import util.FileIO;

public class NPCManager {

    GamePanel gp;
    FileIO io;
    GUI gui;

    public NPCManager(GamePanel gp, FileIO io, GUI gui) {
        this.gp = gp;
        this.io = io;
        this.gui = gui;

    }
    public void setNPC (){

        //Flamingo
        gp.npcArray[0] = new NPC_Flamingo(gp,io,gui);

        //Panda
        gp.npcArray[1] = new NPC_Panda(gp,io,gui);

    }
}

