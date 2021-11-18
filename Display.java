import javax.swing.JFrame;

public class Display{

    //Attributes
    static int width=1400, height=700;
    static JFrame window;
    static Game game;
    static Menu menu;
    static Gameover gameover;

    public static void main(String[] args){
        Music.load();
        window = new JFrame("Ninja Run <3");
        window.setSize(width, height);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        menu();
        window.setVisible(true);
    }

    //Methods
    public static void Start_game(){
        try{Music.stopLoop();}catch(Exception e){}
        Music.loop(Music.gmae1);
        game = new Game();
        window.getContentPane().removeAll();
        window.add(game);
        game.requestFocus();
        window.setVisible(true);
        window.getContentPane().repaint();
    }
    public static void menu(){
        try{Music.stopLoop();}catch(Exception e){}
        Music.loop(Music.menu);
        menu = new Menu();
        window.getContentPane().removeAll();
        window.add(menu);
        menu.requestFocus();
        window.setVisible(true);
        window.getContentPane().repaint();
    }
    public static void gameover(){
        try{Music.stopLoop();}catch(Exception e){}
        Music.loop(Music.end);
        game.stop();
        gameover = new Gameover();
        window.getContentPane().removeAll();
        window.add(gameover);
        gameover.requestFocus();
        window.setVisible(true);
        window.getContentPane().repaint();
    }
}