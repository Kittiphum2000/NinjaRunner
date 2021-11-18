import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.util.Random;
import java.awt.Font;
import java.util.ArrayList;

public class Monster2 extends Monster implements Runnable{
    //Tool
    Random rand = new Random();

    //Attributes
    private int animation=10;
    //Image
    private ImageIcon[] monster2Attack= new ImageIcon[12];
    private ImageIcon[] monster2Dead= new ImageIcon[15];
    private ImageIcon[] monster2Walk= new ImageIcon[12];

    //Constructor
    public Monster2(int x, int width, int height, int Hp, int damage, int speed, int score, int Lv){
        super(x, width, height, Hp, damage, speed, score, Lv);
        int gender = rand.nextInt(3);
        for(int i=0 ; i<12 ; i++){
            String str = "Monster2//Wraith_0"+(gender+1)+"_Attack_"+i+".png";
            monster2Attack[i] = new ImageIcon(this.getClass().getResource(str));
        }
        for(int i=0 ; i<15 ; i++){
            String str = "Monster2//Wraith_0"+(gender+1)+"_Dying_"+i+".png";
            monster2Dead[i] = new ImageIcon(this.getClass().getResource(str));
        }
        for(int i=0 ; i<12 ; i++){
            String str = "Monster2//Wraith_0"+(gender+1)+"_Moving Forward_"+i+".png";
            monster2Walk[i] = new ImageIcon(this.getClass().getResource(str));
        }
        new Thread(this).start();
    }

    //Methods
    public void update(){
        x-=1;
    }
    public void dead(){
        Game.build_monster_bullet.countmon++;
        Game.player.score+=1;
        for(int i=0; i<15; i++){
            try{
                for(int a=0; a<12; a++){
                    Thread.sleep(10);
                    update();
                }
                animation=i;
            }catch(InterruptedException e){}
        }
        start=false;
        Game.build_monster_bullet.monster2Set.remove(this);
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
        if(Game.player.x+30>=x){
            
            while(y<Game.background.y_ground-height-50){
                try{
                    Thread.sleep(speed);
                    y+=2;
                }catch(InterruptedException e){}
            }
            Music.play(Music.attack);
            for(int i=0; i<12; i++){
                try{
                    Thread.sleep(100);
                    animation=i;
                }catch(InterruptedException e){}
            }
            Game.build_monster_bullet.monster2Set.remove(this);
            start=false;
            Game.player.Hp-=damage;
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
            int moving = rand.nextInt(2);
            for(int i=0; i<70; i++){
                try{
                if(moving==0){
                    if(y>=300-width){
                        y--;
                    }else{
                        break;
                    }
                }else if(moving==1){
                    if(y+width<=Game.background.y_ground-50){
                        y++;
                    }else{
                        break;
                    }
                }
                Thread.sleep(speed);
                }catch(InterruptedException e){}

                if(!start){break;}
                check_player();
                if(Game.build_monster_bullet.bulletSet.size()!=0){
                    check_bullet(Game.build_monster_bullet.bulletSet);
                }
                update();
            }
        }
    }
    public void draw(Graphics2D g2){
        g2.setColor(Color.RED);
        // g2.drawRect(x, y, width, height);
        g2.setFont(new Font("HANGING_BASELINE", Font.PLAIN,25));
        g2.drawString("Hp "+Hp,x,y-30);
        if(Hp<=0){
            g2.drawImage(monster2Dead[animation].getImage(), x-50, y-20, width+110, height+110, null);
        }
        else if(Game.player.x+30>=x){
            g2.drawImage(monster2Attack[animation].getImage(), x-50, y-40, width+110, height+110, null);
        }
        else{
            g2.drawImage(monster2Walk[((50000-x)/10)%12].getImage(), x-50, y-40, width+110, height+110, null);
        }
    }

}
