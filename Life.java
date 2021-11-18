import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import java.util.Random;

public class Life implements Runnable{
    //Tool
    Random rand = new Random();

    //Attributes
    public boolean start = true;
    private int width=50, height=50, y, x=1500, speed=7 ,animation_y=-50;
    //Image
    private ImageIcon life_img;

    //Constructor
    public Life(){
        int ran_y = rand.nextInt(230);
        this.y=Game.background.y_ground-360+ran_y;
        life_img = new ImageIcon(this.getClass().getResource("BackgroundMenu//Life.png"));
        new Thread(this).start();
    }

    //Methods
    public void update(){
        x--;
    }
    

    public void check_player(){
        if(Game.player.x+Game.player.width>=x && Game.player.x<=x+width){
            if(Game.player.y+Game.player.height>=y && Game.player.y<=y+height){
                Game.build_monster_bullet.lifes.remove(this);
                Music.play(Music.hp);
                if(Game.player.Hp<5){
                    Game.player.Hp++;
                }
                start=false;
            }
        }
    }

    public void check_map(){
        if(x<-100){
            try{start=false;
                Game.build_monster_bullet.lifes.remove(this);
            } catch(Exception e){}
        }
    }

    public void animation(){
        if(x%3==0){
            if(animation_y<50){
                this.animation_y++;
            }else{
                this.animation_y=-50;
            }
        }
    }

    //Run & Draw
    public void run(){
        while(start){
            try{Thread.sleep(speed);
                update();
                animation();
                check_player();
                check_map();
            }catch(InterruptedException e){}
        }
    }
    public void draw(Graphics2D g2){
        g2.drawImage(life_img.getImage(), x-Game.background.x_ground/8, y-Game.background.x_ground/8+Math.abs(animation_y), 50+Game.background.x_ground/4, 50+Game.background.x_ground/4, null);
    }

}
