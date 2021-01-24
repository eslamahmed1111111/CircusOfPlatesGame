package eg.edu.alexu.csd.oop.game;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Music {

    private static Music sound;

    private Clip clip,over;
    private static boolean AllIsFinished = false;

    private Music() {
    }

    public static Music getInstance() {
        if (sound == null)
            sound = new Music();
        return sound;
    }

    public void startMusic() {
        try {
            URL url = Music.class.getResource("/song.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-14.0f);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopMusic() {
        if (clip != null)
            clip.stop();
    }

    public void gameOver() {
        if (!AllIsFinished) {
            try {
                AllIsFinished = true;
                URL url = Music.class.getResource("/GameOver.wav");
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
                over = AudioSystem.getClip();
                over.open(audioIn);
                over.start();
                clip.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}