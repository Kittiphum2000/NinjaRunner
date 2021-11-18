import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Gameover extends JPanel implements KeyListener{
    
    ImageIcon bg;
    private int currentChoice = 0;
    private String[] options = {"RESTART","HOME"," EXIT"};
    public Gameover(){
        bg = new ImageIcon(this.getClass().getResource("BackgroundMenu//gameover.png"));
        addKeyListener(this);
        setFocusable(true);
    }

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;

        g2d.drawImage(bg.getImage(), 0, 0, Display.width, Display.height-30, null);
        g2d.setFont(new Font("", Font.PLAIN,35));
        g2d.setColor(Color.RED);
        g2d.drawString("Character: "+Game.countchar,300,450);
        g2d.drawString("Score: " + Game.player.score,620,450);
        g2d.drawString("Lv."+Game.player.Lv,1000,450);
        if(currentChoice==0){
            g2d.setColor(Color.BLUE);
        }else{
            g2d.setColor(Color.GRAY);
        }
        g2d.setFont(new Font("Algerian", Font.PLAIN,50));
        g2d.drawString("START",622,520);
        g2d.drawRect(615, 480, 170, 50);

        if(currentChoice==1){
            g2d.setColor(Color.BLUE);
        }else{
            g2d.setColor(Color.GRAY);
        }
        g2d.drawString("HOME",635,578);
        g2d.drawRect(615, 535, 170, 50);

        if(currentChoice==2){
            g2d.setColor(Color.BLUE);
        }else{
            g2d.setColor(Color.GRAY);
        }
        g2d.drawString("EXIT",645,630);
        g2d.drawRect(643, 590, 115, 50);
    }

    //Key
    public void keyTyped(KeyEvent e){}
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == 10){
            //System.out.println(e.getKeyCode());
            if(currentChoice==0){
                Display.Start_game();
            }else if(currentChoice==1){
                Display.menu();
            }else if(currentChoice==2){
                System.exit(0);
            }
        }
        if(e.getKeyCode() == 38 || e.getKeyCode() == 87) {
            currentChoice--;
            if(currentChoice == -1) {
                currentChoice = options.length - 1;
            }
            repaint();
        }
        if(e.getKeyCode() == 40 || e.getKeyCode() == 83) {
            currentChoice++;
            if(currentChoice == options.length) {
                currentChoice = 0;
            }
            repaint();
        }
    }
    public void keyReleased(KeyEvent e){}
          
}



