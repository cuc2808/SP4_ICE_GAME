package battle;

import javax.imageio.ImageIO;
import java.io.IOException;

public class BattleMonster extends BattleEntity {

    public BattleMonster(int level) {

        try {
            if(level == 3) {
                sprite = ImageIO.read(
                        getClass().getResourceAsStream(
                                "/entity/NPCs/NPCImages/FirewallMonster.png"
                        )
                );
            } else{
            sprite = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/entity/NPCs/NPCImages/CpuMonster.png"
                    )
            );}

        } catch (IOException e) {
            e.printStackTrace();
        }

        maxHp = 80;
        hp = 80;

        width = 500;
        height = 500;
        x = 300;
        y = 100;

    }
    public void setHp(int newMaxHp){
        maxHp = newMaxHp;
        hp = maxHp;
    }
}

