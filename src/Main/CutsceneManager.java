package Main;
import java.awt.*;

public class CutsceneManager {

    GamePanel gp;
    boolean cutsceneActive = false;
    int cutsceneStep = 0;
    int cutsceneTimer = 0;

    public CutsceneManager(GamePanel gp) {
        this.gp = gp;
    }

    public void startCutscene() {
        gp.isFading = true;
        cutsceneActive = true;
        cutsceneStep = 0;
        cutsceneTimer = 0;

        // Stop player movement
        gp.player.movementSpeed = 0;
    }

    public void update() {
        if (!cutsceneActive) return;

        cutsceneTimer++;

        switch (cutsceneStep) {
            case 0:
                // Example: NPC walks towards player
                gp.npc.worldX -= 2;
                if (cutsceneTimer > 60) { // wait 1 second
                    cutsceneStep++;
                    cutsceneTimer = 0;
                }
                break;
            case 1:
                // Example: Display message
                gp.gui.hasMessage = true;
                if (cutsceneTimer > 120) { // 2 seconds
                    cutsceneStep++;
                    cutsceneTimer = 0;
                    gp.gui.hasMessage = false;
                }
                break;
            case 2:
                // End cutscene
                cutsceneActive = false;
                gp.player.movementSpeed = gp.player.walkSpeed;
                break;
        }
    }

    public boolean isActive() {
        return cutsceneActive;
    }
}

