package Main.battle;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class BattleEntity {
    public int x, y;
    public int width, height;



    public int maxHp = 100;
    public int hp = 100;

    protected BufferedImage sprite;

    public void draw(Graphics2D g2) {
        if (sprite != null) {
            g2.drawImage(sprite, x, y, width, height, null);
        }
    }
}
