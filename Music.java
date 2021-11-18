import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Music {

    public static File player_jump;
    public static File hurt;
    public static File attack;
    public static File mon_bullet;
    public static File menu;
    public static File gmae1;
    public static File gmae2;
    public static File end;
    public static File dead;
    public static File mon_hurt;
    public static File hp;
    public static File getCoin;



    private static float value;
    private static int frame;

    static Clip clip_loop;
    static Clip clip_play;
    
    public static int count = 0;

    public static void load() {

        player_jump = new File("Sound/player_jump.wav");
        attack = new File("Sound/attack.wav");
        menu = new File("Sound/Menu.wav");
        gmae1 = new File("Sound/Game1.wav");
        end = new File("Sound/Game2.wav");
        hurt = new File("Sound/hurt.wav");
        mon_bullet = new File("Sound/mon_bullet.wav");
        dead = new File("Sound/dead.wav");
        mon_hurt  = new File("Sound/mon_hurt.wav");
        hp = new File("Sound/Hp.wav");
        getCoin = new File("Sound/getCoin.wav");
        value = -30f;
    }
    
    public static void play(File file) {
        try {
            clip_play = AudioSystem.getClip();
            clip_play.open(AudioSystem.getAudioInputStream(file));
            FloatControl controller = (FloatControl) clip_play.getControl(FloatControl.Type.MASTER_GAIN);
            controller.setValue(value); //sound louder 
            clip_play.start();
        } catch (Exception e) {}
    }

    public static void loop(File file) {
        try {
            count++;
            clip_loop = AudioSystem.getClip();
            clip_loop.open(AudioSystem.getAudioInputStream(file));
            clip_loop.setFramePosition(frame);
            FloatControl controller = (FloatControl) clip_loop.getControl(FloatControl.Type.MASTER_GAIN);
            controller.setValue(value); //sound louder 
            clip_loop.loop(clip_loop.LOOP_CONTINUOUSLY);  //loop music
            clip_loop.start();
        } catch (Exception e) {}
    }

    public static void stopLoop() {
        frame = clip_loop.getFramePosition();
        clip_loop.stop();
    }
}