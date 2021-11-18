public class Monster {
    //Attributes
    Monster(int x, int width, int height, int Hp, int damage, int speed, int score, int Lv){
        this.y-=(height-this.height);
        this.x=x;
        this.width=width;
        this.height=height;
        this.Hp=Hp;
        this.damage=damage;
        this.speed=speed;
        this.score=score;
        this.Lv=Lv;
    }
    public boolean start = true;
    public int damage=1, Hp=2, speed=10, Lv=0, score=1;
    public int x, y=Game.background.y_ground-160, width=50, height=150;
    
    public void update(){
        x--;
    }

}
