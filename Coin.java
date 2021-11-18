import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import java.util.Random;

public class Coin  implements Runnable{
    //Tool
    Random rand = new Random();

    //Attributes
    public boolean start = true;
    private int width=50, height=50, y, x=1500, speed=7 ,animation_y=-50;
    //Image
    private ImageIcon[] coin_img= new ImageIcon[6];

    //Constructor
    public Coin(){
        int ran_y = rand.nextInt(230);
        this.y=Game.background.y_ground-360+ran_y;
        for(int i=1 ; i<=6 ; i++){
            String str = "BackgroundMenu//Coin_0"+i+".png";
            coin_img[i-1] = new ImageIcon(this.getClass().getResource(str));
        }
        new Thread(this).start();
    }

    //Methods
    public void update(){
        x--;
    }
    

    public void check_player(){
        if(Game.player.x+Game.player.width>=x && Game.player.x<=x+width){
            if(Game.player.y+Game.player.height>=y && Game.player.y<=y+height){
                Music.play(Music.getCoin);
                Game.build_monster_bullet.coins.remove(this);
                Game.player.score+= Game.player.Lv;
                start=false;
            }
        }
    }

    public void check_map(){
        if(x<-100){
            try{start=false;
                Game.build_monster_bullet.coins.remove(this);
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
        g2.drawImage(coin_img[((50000-x)/5)%6].getImage(), x, y+Math.abs(animation_y), width, height, null);
    }

}
