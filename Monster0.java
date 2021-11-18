import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.util.Random;
import java.awt.Font;
import java.util.ArrayList;

public class Monster0 extends Monster implements Runnable{
    //Tool
    Random rand = new Random();

    //Attributes
    private int animation=0;
    //Image
    private ImageIcon[] monster0Walk= new ImageIcon[10];
    private ImageIcon[] monster0Attack= new ImageIcon[8];
    private ImageIcon[] monster0Dead= new ImageIcon[12];

    //Constructor
    public Monster0(int x, int width, int height, int Hp, int damage, int speed, int score, int Lv){
        super(x, width, height, Hp, damage, speed, score, Lv);
        int gender = rand.nextInt(2);
        for(int i=1 ; i<=10 ; i++){
            String str = "Monster0//Walk "+gender+"("+i+").png";
            monster0Walk[i-1] = new ImageIcon(this.getClass().getResource(str));
        }
        for(int i=1 ; i<=8 ; i++){
            String str = "Monster0//Attack "+gender+"("+i+").png";
            monster0Attack[i-1] = new ImageIcon(this.getClass().getResource(str));
        }
        for(int i=1 ; i<=12 ; i++){
            String str = "Monster0//Dead "+gender+"("+i+").png";
            monster0Dead[i-1] = new ImageIcon(this.getClass().getResource(str));
        }
        new Thread(this).start();
    }

    //Methods
    public void dead(){
        Game.build_monster_bullet.countmon++;
        Game.player.score+=score;
        for(int i=0; i<=12; i++){
            try{
                for(int a=0; a<10; a++){
                    Thread.sleep(10);
                    update();
                }
                animation=i;
            }catch(InterruptedException e){}
        }
        start=false;
        Game.build_monster_bullet.monster0Set.remove(this);
    }
    public int check_bullet(ArrayList<Bullet> bulletSet){
        
        try{
        for(Bullet bullet : bulletSet){
            if(bullet.x+bullet.width>=x && bullet.x+bullet.width<=x+width){
                if(bullet.y+bullet.height>=y && bullet.y<=y+height){
                    Music.play(Music.mon_hurt);
                    try{bullet.destroy();} catch(Exception e) {}
                    Hp-=1;
                    if(Hp<=0){
                        dead();
                        Game.player.Lv+=Lv;
                    }
                    return 0;
                }
            }
        }
        }catch(Exception e) {}
        return 0;
    }

    public void check_player(){
        if(Game.player.x+Game.player.width>=x){
            Music.play(Music.attack);
            for(int i=0; i<=8; i++){
                try{
                    Thread.sleep(100);
                    animation=i;
                }catch(InterruptedException e){}
            }
            
            Game.player.Hp-=damage;
            Game.build_monster_bullet.monster0Set.remove(this);
            start=false;
            Game.check_blood=true;
            Music.play(Music.hurt);
            try{Thread.sleep(400);
            }catch(InterruptedException e){}
            Game.check_blood=false;
            Game.player.check_hp();
        }
    }

    //Run & Draw
    public void run(){
        while(start){
            try{Thread.sleep(speed);
                update();
                check_player();
                if(Game.build_monster_bullet.bulletSet.size()!=0){
                    check_bullet(Game.build_monster_bullet.bulletSet);
                }  
            }catch(InterruptedException e){}
        }
    }
    public void draw(Graphics2D g2){
        g2.setColor(Color.RED);
        // g2.drawRect(x, y, width, height);
        g2.setFont(new Font("HANGING_BASELINE", Font.PLAIN,25));
        g2.drawString("Hp "+Hp,x,y-10);
        if(Hp<=0){
            g2.drawImage(monster0Dead[animation].getImage(), x-40, y-25+width/4, width+70, height+50, null);
        }
        else if(Game.player.x+Game.player.width>=x){
            g2.drawImage(monster0Attack[animation].getImage(), x-40, y-30+width/30, width+70, height+50, null);
        }
        else{
            g2.drawImage(monster0Walk[((50000-x)/10)%10].getImage(), x-40, y-30+width/30, width+70, height+50, null);
        }
    }

}
