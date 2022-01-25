package snake.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import static snake.game.PlayGame.userName;
import static snake.game.Speed.*;

public class GameLevelTwo extends JPanel implements ActionListener {

    private final int B_WIDTH = 600;
    private final int B_HEIGHT = 600;
    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = 900;
    private final int RAND_POS = 50;

    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];

    private int dots;
    private int apple_x;
    private int apple_y;
    private boolean levelDraw = true;

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;
    private boolean stopGame = true;

    private Timer timer;
    private Image ball;
    private Image apple;
    private Image head;

    public GameLevelTwo() {

        initBoard();

    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);
        setDoubleBuffered(true);

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        loadImages();
        initGame();
    }

    private void loadImages() {

        ImageIcon iid = new ImageIcon("C:\\Users\\Mazedul Islam\\Documents\\NetBeansProjects\\Snake Game\\src\\snake\\game\\pic\\dot1.png");
        ball = iid.getImage();

        ImageIcon iia = new ImageIcon("C:\\Users\\Mazedul Islam\\Documents\\NetBeansProjects\\Snake Game\\src\\snake\\game\\pic\\apple1.png");
        apple = iia.getImage();

        ImageIcon iih = new ImageIcon("C:\\Users\\Mazedul Islam\\Documents\\NetBeansProjects\\Snake Game\\src\\snake\\game\\pic\\head1.png");
        head = iih.getImage();
    }

    private void initGame() {

        dots = 4;                  // initial snake

        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }

        locateApple();

        timer = new Timer(speed, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);

        if (levelDraw) {

            // Level Draw
            g.setColor(Color.blue);
            g.fillRect(290, 50, 10, 200);
            g.fillRect(290, 50, 200, 10);
            g.fillRect(290, 300, 10, 200);
            g.fillRect(90, 490, 210, 10);

            //Score board
            g.setColor(Color.white);
            g.fillRect(490, 490, 95, 75);
            Font small = new Font("Helvetica", Font.BOLD, 15);
            g.setFont(small);
            g.setColor(Color.blue);
            String s = "Score : " + totalScore;
            g.drawString(s, 490, 515);

        }

//        g.fillRect(200,50,200,5);
//        g.fillRect(200,500, 200, 5);
    }

    private void doDrawing(Graphics g) {

        if (inGame) {

            g.drawImage(apple, apple_x, apple_y, this);

            for (int z = 0; z < dots; z++) {
                if (z == 0) {
                    g.drawImage(head, x[z], y[z], this);
                } else {
                    g.drawImage(ball, x[z], y[z], this);
                }
            }

            Toolkit.getDefaultToolkit().sync();

        } else {

            gameOver(g);
            levelDraw = false;
        }
    }

    private void gameOver(Graphics g) {

        new GameSound().gameOver();
        String msg = "Game Over";
        String scoreeShow = "Your score is : " + totalScore;
        Font small = new Font("Helvetica", Font.BOLD, 20);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.RED);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 4);

        // show user name
        g.setColor(Color.BLUE);
        g.drawString(userName, (B_WIDTH - metr.stringWidth(userName)) / 2, B_HEIGHT / 3);
        //show score
        g.setColor(Color.GREEN);
        g.drawString(scoreeShow, (B_WIDTH - metr.stringWidth(scoreeShow)) / 2, B_HEIGHT / 2);

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
            @Override
            public void run() {
                // your code here
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        new TryAgain().setVisible(true);
                        setVisible(false);

                    }
                });
            }
        }, 5000
        );
    }

    private void checkApple() {

        if ((x[0] == apple_x) && (y[0] == apple_y)) {
            new GameSound().eatingSound();
            dots++;
            totalScore = totalScore + score;
            locateApple();

        }
    }

    private void move() {

        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (leftDirection) {
            x[0] -= DOT_SIZE;
        }

        if (rightDirection) {
            x[0] += DOT_SIZE;
        }

        if (upDirection) {
            y[0] -= DOT_SIZE;
        }

        if (downDirection) {
            y[0] += DOT_SIZE;
        }
    }

    private void checkCollision() {

        for (int z = dots; z > 0; z--) {

            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
            } else if ((x[z] >= 280 && x[z] <= 290) && (y[z] >= 50 && y[z] <= 240)) {
                inGame = false;
            } else if ((x[z] >= 280 && x[z] <= 290) && (y[z] >= 290 && y[z] <= 500)) {
                inGame = false;
            } else if ((x[z] >= 280 && x[z] <= 480) && (y[z] >= 45 && y[z] <= 55)) {
                inGame = false;
            } else if ((x[z] >= 90 && x[z] <= 290) && (y[z] >= 480 && y[z] <= 500)) {
                inGame = false;
            } else if ((x[z] >= 470 && x[z] <= 490) && (y[z] >= 490 && y[z] <= 570)) {
                inGame = false;
            } else if ((x[z] >= 480 && x[z] <= 590) && (y[z] >= 480 && y[z] <= 490)) {
                inGame = false;
            }

        }

        if (y[0] >= B_HEIGHT) {
            inGame = false;
        }

        if (y[0] < 0) {
            inGame = false;
        }

        if (x[0] >= B_WIDTH) {
            inGame = false;
        }

        if (x[0] < 0) {
            inGame = false;
        }

        if (!inGame) {
            timer.stop();
        }
    }

    private void locateApple() {

        int r = (int) (Math.random() * RAND_POS);
        apple_x = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND_POS);
        apple_y = ((r * DOT_SIZE));
        
        //System.out.println("x = "+apple_x+" ,y = "+apple_y);
            if ((apple_x >= 280 && apple_x <= 290) && (apple_y >= 50 && apple_y <= 240)) {
                locateApple();
            } else if ((apple_x >= 280 && apple_x <= 290) && (apple_y >= 290 && apple_y <= 500)) {
                locateApple();
            } else if ((apple_x >= 280 && apple_x <= 480) && (apple_y >= 45 && apple_y <= 55)) {
               locateApple();
            } else if ((apple_x >= 90 && apple_x <= 290) && (apple_y >= 480 && apple_y <= 500)) {
               locateApple();
            } else if ((apple_x >= 470 && apple_x <= 490) && (apple_y >= 490 && apple_y <= 570)) {
                locateApple();
            } else if ((apple_x >= 480 && apple_x <= 590) && (apple_y >= 480 && apple_y <= 490)) {
                locateApple();
            }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (inGame) {

            checkApple();
            checkCollision();
            move();
        }

        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if (key == KeyEvent.VK_SPACE && stopGame) {
                stopGame = false;
                timer.stop();

            } else if (key == KeyEvent.VK_SPACE && !stopGame) {

                stopGame = true;
                timer.start();

            } else {

                if ((key == KeyEvent.VK_LEFT | key == KeyEvent.VK_NUMPAD4) && (!rightDirection)) {
                    leftDirection = true;
                    upDirection = false;
                    downDirection = false;
                }

                if ((key == KeyEvent.VK_RIGHT | key == KeyEvent.VK_NUMPAD6) && (!leftDirection)) {
                    rightDirection = true;
                    upDirection = false;
                    downDirection = false;
                }

                if ((key == KeyEvent.VK_UP | key == KeyEvent.VK_NUMPAD8) && (!downDirection)) {
                    upDirection = true;
                    rightDirection = false;
                    leftDirection = false;
                }

                if ((key == KeyEvent.VK_DOWN | key == KeyEvent.VK_NUMPAD2) && (!upDirection)) {
                    downDirection = true;
                    rightDirection = false;
                    leftDirection = false;
                }

            }
        }

    }
}
