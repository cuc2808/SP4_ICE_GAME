package battle;

import javax.imageio.ImageIO;
import java.io.IOException;

public class BattlePlayer extends BattleEntity {

    public BattlePlayer() {

        try {
            sprite = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/playerImages/walk/Player1WalkUP3.png"
                    )
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        maxHp = 100;
        hp = 100;

        width = 192;
        height = 192;
        x = 150;
        y = 450;
    }
}
