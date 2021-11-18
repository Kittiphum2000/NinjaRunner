import java.awt.Graphics2D;
import javax.swing.ImageIcon;

public class Background implements Runnable{

    //Attributes
    public int x_ground=-1, y_ground=570, width_ground=50, height_ground=50, speed=4; //Max speed = 1;
    private boolean start=true;
    //Image
    ImageIcon[] bg = new ImageIcon[4];
    ImageIcon[] ground0 = new ImageIcon[4];
    ImageIcon[] ground1 = new ImageIcon[4];

    //Constructor
    public Background(){
        for(int i=0; i<4; i++){
            bg[i]=new ImageIcon(this.getClass().getResource("BackgroundGame//Bg"+i+".png"));
            ground0[i]=new ImageIcon(this.getClass().getResource("BackgroundGame//"+i+"_0.png"));
            ground1[i]=new ImageIcon(this.getClass().getResource("BackgroundGame//"+i+"_1.png"));
        }
        new Thread(this).start();
    }

    //Methods
    public void update_bg(){
        if(x_ground<=-width_ground*2){
            x_ground=-1;
            x_ground--;
        }else{
            x_ground--;
        }
    }
    public void stop(){
        start=false;
    }

    //Run & Draw
    public void run(){
        while(start){
            try{
                update_bg();
                Thread.sleep(speed);
            }catch(InterruptedException e){}
        }
    }
    public void draw(Graphics2D g2d){
        g2d.drawImage(bg[(Game.player.Lv-1)%4].getImage(), 0, 0, Display.width, Display.height, null);
        for(int i=0 ; i<30 ; i++){
            g2d.drawImage(ground0[(Game.player.Lv-1)%4].getImage(), x_ground+i*(width_ground), y_ground, width_ground, height_ground, null);
            g2d.drawImage(ground1[(Game.player.Lv-1)%4].getImage(), x_ground+i*(width_ground), y_ground+50, width_ground, height_ground, null);
            // g2d.setColor(Color.RED);
            // g2d.drawRect(x_ground+i*(width_ground), y_ground, width_ground, height_ground);
        }
    }

}