import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;

public class Player implements Runnable{
    //Attributes
    public int x, y, width=50, height=150, Hp=2, Lv=1, score=0;
    private int animation=0, animationDead=0;
    private boolean dead=true;
    
    //Imagepublic 
    private ImageIcon[] playerRun = new ImageIcon[10];
    private ImageIcon[] playerJump = new ImageIcon[10];
    private ImageIcon[] playerThrow = new ImageIcon[10];
    private ImageIcon[] playerDead = new ImageIcon[10];
    private ImageIcon Life;
    //Constructor
    public Player(int x, int y){
        this.x=x;
        this.y=y;
        for(int i=0 ; i<10 ; i++){
            playerRun[i] = new ImageIcon(this.getClass().getResource("NinjaGirl//Run__00"+i+".png"));
            playerJump[i] = new ImageIcon(this.getClass().getResource("NinjaGirl//Jump__00"+i+".png"));
            playerThrow[i] = new ImageIcon(this.getClass().getResource("NinjaGirl//Throw__00"+i+".png"));
            playerDead[i] = new ImageIcon(this.getClass().getResource("NinjaGirl//Dead__00"+i+".png"));
        }
        Life = new ImageIcon(this.getClass().getResource("BackgroundMenu//Life.png"));
        
        
    }

    //Methods
    public void jump(){
        Music.play(Music.player_jump);
        for(int i=0 ; i<100 ; i++){
            y-=2;
            animation++;
            try{Thread.sleep(Game.background.speed);
            }catch(InterruptedException e){}
        }
        for(int i=0 ; i<100 ; i++){
            y+=2;
            animation++;
            try{Thread.sleep(Game.background.speed);
            }catch(InterruptedException e){}
        }
        animation=0;
    }
    public void check_hp(){
        if(Hp<=0&&dead){
            dead=false;
            Music.play(Music.dead);
            for(int i=0; i<10; i++){
                try{animationDead=i;
                    Thread.sleep(150);
                }catch(InterruptedException e){}
            }
            Display.gameover();
        }
    }

    //Run & Draw
    public void run(){
        try{Thread.sleep(1);
        if(y==Game.background.y_ground-160){
            jump();
        }
        }catch(InterruptedException e){}
    }
    public void draw(Graphics2D g2){
        if((Lv-1)%4==2 || (Lv-1)%4==3){
            g2.setColor(Color.BLACK);
        }else{
            g2.setColor(Color.WHITE);
        }
        g2.setFont(new Font("", Font.PLAIN,25));
        g2.drawString("Lv."+Lv,300,50);
        g2.drawString("Score: "+score,650,50);
        g2.drawString("Character: "+Game.countchar,1150,50);
        if(Hp<=0){
            g2.drawImage(playerDead[animationDead].getImage(), x-30, y-15, width+100, height+50, null);
        }
        else if(y==Game.background.y_ground-160){
            g2.drawImage(playerRun[(-Game.background.x_ground)/10%10].getImage(), x-30, y-30, width+70, height+50, null);
        }
        else{
            g2.drawImage(playerJump[(animation/20)%10].getImage(), x-30, y-30, width+70, height+50, null);
        }
        for(int i=0; i<Hp; i++){
            g2.drawImage(Life.getImage(), 20+i*52-Game.background.x_ground/8, 20-Game.background.x_ground/8, 50+Game.background.x_ground/4, 50+Game.background.x_ground/4, null);
        }
    }
}