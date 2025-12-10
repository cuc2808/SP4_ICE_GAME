package Main;

import Tile.TileManager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, ShiftPressed;

    @Override
    public void keyTyped(KeyEvent e) { //Might be unused....
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int pressedKeyCode = e.getKeyCode();        //A variable instantiated by our pressed Key.

        if(pressedKeyCode == KeyEvent.VK_W){        //Something happens when any of the keys are pressed. This is repeated for all our wished options to play the game.
            //WALK UP
            upPressed = true;
        }
        if(pressedKeyCode == KeyEvent.VK_A){
            //WALK LEFT
            leftPressed = true;

        }
        if(pressedKeyCode == KeyEvent.VK_S){
            //WALK DOWN
            downPressed = true;

        }
        if(pressedKeyCode == KeyEvent.VK_D){
            //WALK RIGHT
            rightPressed = true;

        }
        if(pressedKeyCode == KeyEvent.VK_SHIFT){
            ShiftPressed = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int releasedKeyCode = e.getKeyCode();

        if(releasedKeyCode == KeyEvent.VK_W){        //Whatever happened has to stop when the key is released again.
            //WALK UP
            upPressed = false;

        }
        if(releasedKeyCode == KeyEvent.VK_A){
            //WALK LEFT
            leftPressed = false;

        }
        if(releasedKeyCode == KeyEvent.VK_S){
            //WALK DOWN
            downPressed = false;

        }
        if(releasedKeyCode == KeyEvent.VK_D){
            //WALK RIGHT
            rightPressed = false;

        }
        if(releasedKeyCode == KeyEvent.VK_SHIFT){
            ShiftPressed = false;
        }

    }

}
