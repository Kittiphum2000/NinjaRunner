import javax.swing.ImageIcon;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.Random;
import java.util.ArrayList;

public class Character implements Runnable{
    //Attributes
    private ImageIcon[] characters = new ImageIcon[51];
    public ArrayList<Integer> charInt = new ArrayList<Integer>();
    Random random = new Random();
    
    //Constructor
    public Character(){
        for(int i=0 ; i<=13 ; i++){
            String str = "Character//"+i+".png";
            characters[i] = new ImageIcon(this.getClass().getResource(str));
        }
        characters[15] = new ImageIcon(this.getClass().getResource("Character//15.png"));
        characters[17] = new ImageIcon(this.getClass().getResource("Character//17.png"));
        for(int i=21 ; i<=49 ; i++){
            String str = "Character//"+i+".png";
            characters[i] = new ImageIcon(this.getClass().getResource(str));
        }
        random_char();
    }

    //Methods
    public void random_char(){
        int r;
        for(int i=0 ; i<15 ; i++){
            do{r = random.nextInt(50);
            }while(r==14||r==16||r==18||r==19||r==20||r==48||r==0||r==1||r==2||r==3||r==47||r==48||r==49);
            charInt.add(r);
        }
    }

    //Run && Draw
    public void run(){
        charInt.remove(0);
        if(charInt.isEmpty()){
            random_char();
        }
    }
    public void draw(Graphics2D g2){
        g2.setColor(Color.RED);
        for(int i=charInt.size() ; i>0 ; i--){
            g2.drawImage(characters[charInt.get(charInt.size()-i)].getImage(),1275-i*75, 100, 70, 70, null);
        }
    }
    
}