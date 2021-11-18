import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Game extends JPanel implements Runnable, KeyListener{
    //Attributes
    static Background background;
    static Player player;
    static Build_monster_bullet build_monster_bullet;
    Character character;
    private boolean start=true;
    static public long countchar=0;
    ImageIcon blood;
    static public boolean check_blood=false;

    //Constructor
    public Game(){
        blood = new ImageIcon(this.getClass().getResource("BackgroundMenu//blood.png"));
        background = new Background();
        player = new Player(150, background.y_ground-160);
        build_monster_bullet = new Build_monster_bullet();
        character = new Character();
        new Thread(this).start();
        addKeyListener(this);
        setFocusable(true);
    }

    //Methods
    public void stop(){
        this.start=false;
        build_monster_bullet.start=false;
        background.stop();
        while(build_monster_bullet.monster0Set.size()>0){
            build_monster_bullet.monster0Set.get(0).start=false;
            build_monster_bullet.monster0Set.remove(0);
        }
        while(build_monster_bullet.bulletSet.size()>0){
            build_monster_bullet.bulletSet.get(0).destroy();
        }
    }

    //Paintting
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        background.draw(g2d);
        player.draw(g2d);
        character.draw(g2d);
        for(int i=0 ; i<build_monster_bullet.coins.size() ; i++){
            build_monster_bullet.coins.get(i).draw(g2d);
        }
        for(int i=0 ; i<build_monster_bullet.lifes.size() ; i++){
            build_monster_bullet.lifes.get(i).draw(g2d);
        }
        for(int i=0 ; i<build_monster_bullet.bulletSet.size() ; i++){
            build_monster_bullet.bulletSet.get(i).draw(g2d);
        }
        for(int i=0 ; i<build_monster_bullet.monster0Set.size(); i++){
            build_monster_bullet.monster0Set.get(i).draw(g2d);
        }
        for(int i=0 ; i<build_monster_bullet.monster1Set.size(); i++){
            build_monster_bullet.monster1Set.get(i).draw(g2d);
        }
        for(int i=0 ; i<build_monster_bullet.monster2Set.size(); i++){
            build_monster_bullet.monster2Set.get(i).draw(g2d);
        }
        for(int i=0 ; i<build_monster_bullet.bulletmonsterSet.size() ; i++){
            build_monster_bullet.bulletmonsterSet.get(i).draw(g2d);
        }
        if(check_blood){
            g2d.drawImage(blood.getImage(), -120, -60, Display.width+200, Display.height+80, null);
        }
    }
    //Run & Key
    public void run(){
        while(start){
            try{repaint();
                Thread.sleep(1);
            }catch(InterruptedException e){}
        }
    }
    public void keyTyped(KeyEvent e){}
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode()==32){
            new Thread(player).start();
        }
        else if(/*true*/e.getKeyCode()-44==character.charInt.get(0)){
            build_monster_bullet.bulletSet.add(new Bullet(player.x, player.y+70, 70, 15));
            countchar++;
            if(countchar%7==6){
                build_monster_bullet.coins.add(new Coin());
            }
            if(countchar%75==74){
                build_monster_bullet.lifes.add(new Life());
            }
            new Thread(character).start();
        }
    }
    public void keyReleased(KeyEvent e){}

}
