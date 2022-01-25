package snake.game;

import java.awt.EventQueue;

import javax.swing.JFrame;
import static snake.game.Speed.*;

public class Snake extends JFrame {

    public Snake() {
        initUI();
        setSize(600, 600);
        setResizable(false);
        

    }
    
    public void initUI() {

        if (checkSpeed == false) {
            speed = 170;
            score = 10;
        }

        if (gameLevel == 1) {

           setTitle("Level One");
           add(new GameLevelOne());
            
        }
        if (gameLevel == 2) {
            setTitle("Level Two");
            add(new GameLevelTwo());
        }

    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Snake().setVisible(true);
               

            }
        });
    }
}
