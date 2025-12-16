package battle;

import Main.GamePanel;
import Main.GameState;
import entity.Player;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class BattleManager {
    GamePanel gp;
    BattlePlayer player;
    BattleMonster monster;

    // Battle settings
    BattleState battleState = BattleState.INTRO;
    int timer = 0;
    int battleLevel;

    // Action system
    BattleAction selectedAction = BattleAction.ATTACK;
    boolean playerDefending = false;


    // UI effects
    float messageAlpha = 1.0f;
    boolean fadingOut = true;
    float playerHpAlpha = 1.0f;
    boolean playerHpFlashing = false;
    float monsterHpAlpha = 1.0f;
    boolean monsterHpFlashing = false;

    //Backgrounds
    BufferedImage battleBackground;
    BufferedImage levelTwoBattlebackground;
    BufferedImage levelThreeBattlebackground;

    public BattleManager(GamePanel gp) {
        this.gp = gp;
        try {
            levelTwoBattlebackground = ImageIO.read(getClass().getResourceAsStream("/battle/BattleBackground/levelTwoBackground.png"));
            levelThreeBattlebackground = ImageIO.read(getClass().getResourceAsStream("/battle/BattleBackground/levelThreeBackground.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startBattle(int level) {
        battleLevel = level;

        battleState = BattleState.INTRO;
        timer = 0;

        player = new BattlePlayer();
        monster = new BattleMonster(level);

        //Boss hp on different levels
        if(level == 3){
            monster.setHp(220);
        }else {
            monster.setHp(120);
        }

        battleBackground = (level == 3)
                ? levelThreeBattlebackground
                : levelTwoBattlebackground;

        gp.gameState = GameState.BATTLE;
    }


    public void update() {
        switch (battleState) {
            case INTRO:
                timer++;
                if (timer > 60) {
                    battleState = BattleState.PLAYER_TURN;
                    timer = 0;
                }
                break;

            case PLAYER_TURN:
                // Navigate actions
                if (gp.keyH.upPressed) {
                    int current = selectedAction.ordinal();
                    current = (current - 1 + BattleAction.values().length) % BattleAction.values().length;
                    selectedAction = BattleAction.values()[current];
                    gp.keyH.upPressed = false;
                } else if (gp.keyH.downPressed) {
                    int current = selectedAction.ordinal();
                    current = (current + 1) % BattleAction.values().length;
                    selectedAction = BattleAction.values()[current];
                    gp.keyH.downPressed = false;
                }

                // Execute selected action
                if (gp.keyH.ePressed) {
                    gp.keyH.ePressed = false;

                    switch (selectedAction) {
                        case ATTACK:
                            int dmg = 10 + (int)(Math.random() * 11); // 10-20 damage
                            if (Math.random() < 0.1) dmg *= 2; // 10% crit
                            if (Math.random() < 0.05) dmg = 0; // 5% miss
                            monster.hp -= dmg;
                            monsterHpFlashing = true;
                            monsterHpAlpha = 1.0f;
                            break;

                        case HEAL:
                            int heal = 15 + (int)(Math.random() * 11); // 15-25 heal
                            player.hp += heal;
                            if (player.hp > player.maxHp) player.hp = player.maxHp;
                            break;

                        case DEFEND:
                            playerDefending = true;
                            break;

                        case SPECIAL:
                            int specialDmg = 25 + (int)(Math.random() * 11); // high risk
                            monster.hp -= specialDmg;
                            monsterHpFlashing = true;
                            monsterHpAlpha = 1.0f;
                            break;
                    }

                    battleState = BattleState.ENEMY_TURN;
                    timer = 0;
                    messageAlpha = 1.0f;
                }
                break;

            case ENEMY_TURN:
                timer++;

                // Animate message
                if (fadingOut) {
                    messageAlpha -= 0.02f;
                    if (messageAlpha <= 0.2f) fadingOut = false;
                } else {
                    messageAlpha += 0.02f;
                    if (messageAlpha >= 1.0f) fadingOut = true;
                }

                if (timer > 60) {
                    // Monster attack
                    int enemyDmg;

                    if (battleLevel == 3) {
                        // Level 3 boss = stronger
                        enemyDmg = 8 + (int)(Math.random() * 16); // 10–30 damage
                    } else {
                        // Normal enemies
                        enemyDmg = 5 + (int)(Math.random() * 16);  // 5–20 damage
                    }

                    if (playerDefending) enemyDmg /= 2;
                    player.hp -= enemyDmg;
                    if (player.hp < 0) player.hp = 0;

                    playerHpFlashing = true;
                    playerHpAlpha = 1.0f;

                    playerDefending = false;
                    battleState = BattleState.PLAYER_TURN;
                    timer = 0;
                }
                break;
        }

        // Handle HP flashing
        if (playerHpFlashing) {
            playerHpAlpha -= 0.05f;
            if (playerHpAlpha <= 0f) {
                playerHpAlpha = 1.0f;
                playerHpFlashing = false;
            }
        }

        if (monsterHpFlashing) {
            monsterHpAlpha -= 0.05f;
            if (monsterHpAlpha <= 0f) {
                monsterHpAlpha = 1.0f;
                monsterHpFlashing = false;
            }
        }



        // Only set battleState if it's still in battle
        if ((player.hp <= 0 || monster.hp <= 0) &&
                battleState != BattleState.VICTORY && battleState != BattleState.DEFEAT) {

            if (player.hp <= 0) {
                battleState = BattleState.DEFEAT;
            } else {
                battleState = BattleState.VICTORY;
            }
            timer = 0; // reset timer once
        }

// Handle victory/defeat timer
        if (battleState == BattleState.VICTORY || battleState == BattleState.DEFEAT) {
            timer++;
            if (timer > 120) { // 2 seconds at 60 FPS
                if(battleState == BattleState.VICTORY){
                    Player.enemyCounter++;
                    gp.flamingoBattleTriggered = true;
                } else if(battleState == BattleState.DEFEAT) {
                    gp.flamingoBattleTriggered = false;
                }
                endBattle();    // go back to normal play state

            }
        }




    }

    public void draw(Graphics2D g2) {
        // Draw battle background (depends on battle level)
        if (battleBackground != null) {
            g2.drawImage(
                    battleBackground,
                    0,
                    0,
                    gp.screenWidth,
                    gp.screenHeight,
                    null
            );
        }

        // Sprites
        if (player != null) player.draw(g2);
        if (monster != null) monster.draw(g2);

        drawUI(g2);
    }

    private void drawUI(Graphics2D g2) {
        int padding = 20;
        int boxHeight = 120;
        int boxTop = gp.screenHeight - boxHeight - padding;

        if (battleState == BattleState.VICTORY) {
            g2.setColor(new Color(0, 0, 0, 200));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            g2.setColor(Color.YELLOW);
            g2.setFont(new Font("Arial", Font.BOLD, 48));
            g2.drawString("Victory!", gp.screenWidth / 2 - 100, gp.screenHeight / 2);
            return; // stop drawing normal UI
        }

        if (battleState == BattleState.DEFEAT) {
            g2.setColor(new Color(0, 0, 0, 200));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            g2.setColor(Color.RED);
            g2.setFont(new Font("Arial", Font.BOLD, 48));
            g2.drawString("Defeat...", gp.screenWidth / 2 - 100, gp.screenHeight / 2);
            return; // stop drawing normal UI
        }

        // Bottom semi-transparent box
        g2.setColor(new Color(0, 0, 0, 180));
        g2.fillRect(padding, boxTop, gp.screenWidth - 2 * padding, boxHeight);

        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(2));
        g2.drawRect(padding, boxTop, gp.screenWidth - 2 * padding, boxHeight);

        // Text positions
        int textX = padding + 20;
        int textY = boxTop + 30;

        // Display battle messages
        if (battleState == BattleState.PLAYER_TURN) {
            g2.setColor(Color.WHITE);
            g2.drawString("Choose your action:", textX, textY);

            String[] actions = {"Attack", "Defend", "Heal", "Special"};
            for (int i = 0; i < actions.length; i++) {
                g2.setColor(selectedAction.ordinal() == i ? Color.YELLOW : Color.WHITE);
                g2.drawString(actions[i], textX, textY + 20 + i * 20);
            }
        } else if (battleState == BattleState.ENEMY_TURN) {
            // Fade the "Enemy is attacking!" message safely
            messageAlpha = Math.max(0f, Math.min(1f, messageAlpha)); // clamp between 0-1
            AlphaComposite original = (AlphaComposite) g2.getComposite();
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, messageAlpha));
            g2.setColor(Color.RED);
            g2.drawString("Enemy is attacking!", textX, textY);
            g2.setComposite(original);
        }

        // HP bars positions
        int barWidth = 200;
        int barHeight = 20;
        int barY = 50;
        int playerBarX = 50;
        int monsterBarX = gp.screenWidth - barWidth - 50;

        // Player HP bar with flashing
        playerHpAlpha = Math.max(0f, Math.min(1f, playerHpAlpha)); // clamp
        AlphaComposite original = (AlphaComposite) g2.getComposite();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, playerHpAlpha));
        g2.setColor(Color.RED);
        int playerBarWidth = (int) (barWidth * ((double) player.hp / player.maxHp));
        g2.fillRect(playerBarX, barY, playerBarWidth, barHeight);
        g2.setComposite(original);
        g2.setColor(Color.WHITE);
        g2.drawRect(playerBarX, barY, barWidth, barHeight);

        // Monster HP bar with flashing
        monsterHpAlpha = Math.max(0f, Math.min(1f, monsterHpAlpha)); // clamp
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, monsterHpAlpha));
        g2.setColor(Color.RED);
        int monsterBarWidth = (int) (barWidth * ((double) monster.hp / monster.maxHp));
        g2.fillRect(monsterBarX, barY, monsterBarWidth, barHeight);
        g2.setComposite(original);
        g2.setColor(Color.WHITE);
        g2.drawRect(monsterBarX, barY, barWidth, barHeight);
    }

    private void endBattle() {
        gp.isFading = false;
        gp.fadeAlpha = 0;
        gp.keyH.ePressed = false;
        gp.gameState = GameState.PLAY;
    }
}



