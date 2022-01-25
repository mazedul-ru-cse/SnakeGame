package snake.game;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import static javafx.application.Platform.exit;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.*;
import javax.swing.*;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class GameSound {

    public static boolean selectSound;

    public void eatingSound() {

        try {
            String gongFile = "C:\\\\Users\\\\Mazedul Islam\\\\Documents\\\\NetBeansProjects\\\\Snake Game\\\\src\\\\snake\\\\game\\\\Sound\\\\Eating.wav";
            InputStream in = new FileInputStream(gongFile);

            // create an audiostream from the inputstream
            AudioStream audioStream = new AudioStream(in);

            // play the audio clip with the audioplayer class
            AudioPlayer.player.start(audioStream);
            exit();

        } catch (Exception ex) {
            System.out.println(ex);
        }

    }
    
     public void levelUpSound() {

        try {
            String gongFile = "C:\\\\Users\\\\Mazedul Islam\\\\Documents\\\\NetBeansProjects\\\\Snake Game\\\\src\\\\snake\\\\game\\\\Sound\\\\LevelUp.wav";
            InputStream in = new FileInputStream(gongFile);

            // create an audiostream from the inputstream
            AudioStream audioStream = new AudioStream(in);

            // play the audio clip with the audioplayer class
            AudioPlayer.player.start(audioStream);
            exit();

        } catch (Exception ex) {
            System.out.println(ex);
        }

    }
    
    public void loadingSound() {

        try {
            String gongFile = "C:\\\\Users\\\\Mazedul Islam\\\\Documents\\\\NetBeansProjects\\\\Snake Game\\\\src\\\\snake\\\\game\\\\Sound\\\\Loading.wav";
            InputStream in = new FileInputStream(gongFile);

            // create an audiostream from the inputstream
            AudioStream audioStream = new AudioStream(in);

            // play the audio clip with the audioplayer class
            AudioPlayer.player.start(audioStream);
            exit();

        } catch (Exception ex) {
            System.out.println(ex);
        }

    }
    
    public void buttonSound() {

        try {
            String gongFile = "C:\\\\Users\\\\Mazedul Islam\\\\Documents\\\\NetBeansProjects\\\\Snake Game\\\\src\\\\snake\\\\game\\\\Sound\\\\Button.wav";
            InputStream in = new FileInputStream(gongFile);

            // create an audiostream from the inputstream
            AudioStream audioStream = new AudioStream(in);

            // play the audio clip with the audioplayer class
            AudioPlayer.player.start(audioStream);
            exit();

        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    public void gameOver() {

        try {
            String gongFile = "C:\\\\Users\\\\Mazedul Islam\\\\Documents\\\\NetBeansProjects\\\\Snake Game\\\\src\\\\snake\\\\game\\\\Sound\\\\GameOver.wav";
            InputStream in = new FileInputStream(gongFile);

            // create an audiostream from the inputstream
            AudioStream audioStream = new AudioStream(in);

            // play the audio clip with the audioplayer class
            AudioPlayer.player.start(audioStream);

        } catch (Exception ex) {
            System.out.println(ex);

        }

    }
    

    public void coverSound() {

        try {
            String gongFile = "C:\\\\Users\\\\Mazedul Islam\\\\Documents\\\\NetBeansProjects\\\\Snake Game\\\\src\\\\snake\\\\game\\\\Sound\\\\CoverSound.wav";
            InputStream in = new FileInputStream(gongFile);

            // create an audiostream from the inputstream
            AudioStream audioStream = new AudioStream(in);

            // play the audio clip with the audioplayer class
            AudioPlayer.player.start(audioStream);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
            @Override
            public void run() {
                // your code here
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        coverSound();

                    }
                });
            }
        }, 161000
        );

    }

    public static void main(String[] args) {
        new GameSound().coverSound();
        
    }

}
