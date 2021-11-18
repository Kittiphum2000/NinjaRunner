import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.util.ArrayList;

public class Monster1 extends Monster implements Runnable{

    //Attributes
    private int animation=0;
    //Image
    private ImageIcon[] monster1Run= new ImageIcon[8];
    private ImageIcon[] monster1Attack= new ImageIcon[8];
    private ImageIcon[] monster1Dead= new ImageIcon[9];
    private ImageIcon[] monster1Shoot= new ImageIcon[4];

    //Constructor
    public Monster1(int x, int width, int height, int Hp, int damage, int speed, int score, int Lv){
        super(x, width, height, Hp, damage, speed, score, Lv);
        for(int i=1 ; i<=8 ; i++){
            String str = "Monster1//Run "+"("+i+").png";
            monster1Run[i-1] = new ImageIcon(this.getClass().getResource(str));
        }
        for(int i=1 ; i<=8 ; i++){
            String str = "Monster1//Melee "+"("+i+").png";
            monster1Attack[i-1] = new ImageIcon(this.getClass().getResource(str));
        }
        for(int i=1 ; i<=9 ; i++){
            String str = "Monster1//Dead "+"("+i+").png";
            monster1Dead[i-1] = new ImageIcon(this.getClass().getResource(str));
        }
        for(int i=1 ; i<=4 ; i++){
            String str = "Monster1//Shoot "+"("+i+").png";
            monster1Shoot[i-1] = new ImageIcon(this.getClass().getResource(str));
        }
        new Thread(this).start();
    }

    //Methods

    public void dead(){
        Game.build_monster_bullet.countmon++;
        Game.player.score+=score;
        for(int i=1; i<=9; i++){
            try{
                for(int a=0; a<10; a++){
                    Thread.sleep(10);
                    update();
                }
                animation=i;
            }catch(InterruptedException e){}
        }
        start=false;
        Game.build_monster_bullet.monster1Set.remove(this);
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
        if(Game.player.x+30>=x-20){
            Music.play(Music.attack);
            for(int i=7; i>=1; i--){
                try{
                    Thread.sleep(70);
                    animation=i;
                }catch(InterruptedException e){}
            }

            try{
                Thread.sleep(70);
            }catch(InterruptedException e){}
            Game.player.Hp-=damage;
            Game.build_monster_bullet.monster1Set.remove(this);
            start=false;
            Game.check_blood=true;
            Music.play(Music.hurt);
            try{Thread.sleep(400);
            }catch(InterruptedException e){}
            Game.check_blood=false;
            Game.player.check_hp();
        }
    }
    public void shoot(){
        if(x==1200){
            Game.build_monster_bullet.bulletmonsterSet.add(new Bulletmonster(x, y+width/3, 20+height/5, 20+height/5));
        }
    }

    //Run & Draw
    public void run(){
        while(start){
            try{Thread.sleep(speed);
                update();
                shoot();
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
            g2.drawImage(monster1Dead[animation].getImage(), x-40, y-30+width/7, width+90, height+50, null);
        }
        else if(x<=1200 && x>1120){
            g2.drawImage(monster1Shoot[(x/20)%4].getImage(), x-40, y-35+width/7, width+90, height+50, null);
        }
        else if(Game.player.x+30>=x-20){
            g2.drawImage(monster1Attack[animation].getImage(), x-40, y-35+width/7, width+90, height+50, null);
        }
        else{
            g2.drawImage(monster1Run[((50000-x)/10)%6].getImage(), x-40, y-35+width/7, width+90, height+50, null);
        }
    }

}
