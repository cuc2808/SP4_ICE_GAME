package Main.battle;

import javax.imageio.ImageIO;
import java.io.IOException;

public class BattleMonster extends BattleEntity {

    public BattleMonster() {

        try {
            sprite = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/entity/NPCs/NPCImages/Flamingo/Flamongo1.png"
                    )
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        maxHp = 80;
        hp = 80;

        width = 220;
        height = 220;
        x = 550;
        y = 200;
    }
}

