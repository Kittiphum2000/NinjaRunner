import java.awt.Graphics2D;
import javax.swing.ImageIcon;

public class Bulletmonster implements Runnable{
    //Attributes 
    public boolean start = true;
    public int x, y, animation, damage=1, width, height;
    //Image
    ImageIcon[] bullet = new ImageIcon[5];
    ImageIcon[] endbullet = new ImageIcon[5];

    //Constructor
    public Bulletmonster(int x, int y, int width, int height){
        for(int i=0; i<5; i++){
            bullet[i] = new ImageIcon(this.getClass().getResource("Monster1//Bullet_00"+i+".png"));
            endbullet[i] = new ImageIcon(this.getClass().getResource("Monster1//Muzzle_00"+i+".png"));
        }
        this.y=y;
        this.x=x;
        this.width=width;
        this.height=height;
        new Thread(this).start();
        Music.play(Music.mon_bullet);
    }

    //Methods
    public void update(){
        if(y<Game.background.y_ground-width && x%(10-height/15)==0){
            y+=1;
        }
        x-=1;
    }
    public void destroy(){
        start=false;
        Game.build_monster_bullet.bulletmonsterSet.remove(this);
    }
    public void check_map(){
        if(x<=-20){
            destroy();
        }
    }
    public void check_player(){
        if(Game.player.x+Game.player.width>=x && Game.player.x<=x+width && Game.player.y+Game.player.height>=y){
            for(int i=1; i<=5; i++){
                try{
                    Thread.sleep(1);
                    animation=i;
                }catch(InterruptedException e){}
            }
            Game.player.Hp-=damage;
            Game.build_monster_bullet.bulletmonsterSet.remove(this);
            start=false;
            Game.check_blood=true;
            try{Thread.sleep(400);
            }catch(InterruptedException e){}
            Game.check_blood=false;
            Game.player.check_hp();
        }
    }

    //Run & Draw
    public void run(){
        while(start){
            try{
                update();
                check_player();
                check_map();
                Thread.sleep(1);
            }catch(InterruptedException e){}
        }
    }
    public void draw(Graphics2D g2){
        if(animation!=0){
            g2.drawImage(endbullet[animation-1].getImage(), x, y, width, height, null);
        }
        else{
            g2.drawImage(bullet[(Math.abs(x)/10)%4].getImage(), x, y, width, height, null);
        }
    }
}
