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

        gp.npcArray[0] = new NPC_Flamingo(gp,io,gui);

    }
}

