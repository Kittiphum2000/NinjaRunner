import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Menu  extends JPanel implements KeyListener{
    //Attributes
    private int currentChoice = 0;
    private String[] options = {"START"," EXIT"};
    //Image
    ImageIcon bg;
    ImageIcon ground02;
    ImageIcon Key;
    
    //Constructor
    public Menu (){
        bg = new ImageIcon(this.getClass().getResource("BackgroundMenu//Background_01.png"));
        ground02 = new ImageIcon(this.getClass().getResource("BackgroundMenu//Ground_02.png"));
        Key = new ImageIcon(this.getClass().getResource("BackgroundMenu//Key_02.png"));
        addKeyListener(this);
        setFocusable(true);
    }

    //Paintting
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;

        g2d.drawImage(bg.getImage(), 0, 0, Display.width, Display.height, null);
        g2d.drawImage(Key.getImage(), 500+currentChoice*20, 460+currentChoice*50, 100, 50, null);
        g2d.setFont(new Font("Algerian", Font.PLAIN,100));
        g2d.setColor(Color.white);
        g2d.drawString("Ninja Run",485,200);
        for(int i=0 ; i<30 ; i++){
            g2d.drawImage(ground02.getImage(), i*50, 610, 50, 50, null);
        }
        g2d.setFont(new Font("Algerian", Font.PLAIN,50));
        if(currentChoice==0){
            g2d.setColor(Color.RED);
        }else{
            g2d.setColor(Color.GRAY);
        }
       
        g2d.drawString("START",623,502);
        g2d.drawRect(615, 460, 170, 50);
        if(currentChoice==1){
            g2d.setColor(Color.RED);
        }else{
            g2d.setColor(Color.GRAY);
        }
        g2d.drawString("EXIT",645,560);
        g2d.drawRect(643, 520, 115, 50);

    }

    //Key
    public void keyTyped(KeyEvent e){}
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 10){
            
            if(currentChoice==0){
                Display.Start_game();
            }else if(currentChoice==1){
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
