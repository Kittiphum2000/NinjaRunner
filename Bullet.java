import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Bullet implements Runnable{
    //Attributes 
    public boolean start = true;
    public int x, y , width, height;
    //Image
    ImageIcon bulletNinja;

    //Constructor
    public Bullet(int x, int y, int width, int height){
        bulletNinja = new ImageIcon(this.getClass().getResource("NinjaGirl//Kunai.png"));
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        Music.play(Music.attack);
        new Thread(this).start();
    }

    //Methods
    public void update(){
        x+=1;
    }
    public void destroy(){
        start=false;
        Game.build_monster_bullet.bulletSet.remove(this);
    }
    public void check_map(){
        if(x>=1350){
            try{destroy();} catch(Exception e){}
        }
    }

    //Run & Draw
    public void run(){
        while(start){
            try{
                update();
                check_map();
                Thread.sleep(1);
            }catch(InterruptedException e){}
        }
    }
    public void draw(Graphics2D g2){
        g2.setColor(Color.RED);
        g2.drawImage(bulletNinja.getImage(), x, y, 70, 15, null);
    }
}