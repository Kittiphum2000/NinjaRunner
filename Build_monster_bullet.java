import java.util.ArrayList;
import java.util.Random;

public class Build_monster_bullet implements Runnable{
    //Tool
    Random rand = new Random();
    //Attributes
    int countmon=0;
    boolean start=true,start1=true,start2=false;
    ArrayList<Bullet> bulletSet = new ArrayList<Bullet>();
    ArrayList<Bulletmonster> bulletmonsterSet = new ArrayList<Bulletmonster>();
    ArrayList<Monster0> monster0Set = new ArrayList<Monster0>();
    ArrayList<Monster1> monster1Set = new ArrayList<Monster1>();
    ArrayList<Monster2> monster2Set = new ArrayList<Monster2>();
    ArrayList<Coin> coins = new ArrayList<Coin>();
    ArrayList<Life> lifes = new ArrayList<Life>();

    //Constructor
    public Build_monster_bullet(){
        new Thread(this).start();
    }

    //Methods
    public void build_monster0(int number, int size, int Hp, int damage, int speed, int score, int Lv){
        for(int i=0 ; i<number ; i++){
            monster0Set.add(new Monster0(1400+rand.nextInt(number*3)*100+i*30, 60*size, 150*size, Hp, damage, speed, score, Lv));
        }
    }

    public void build_monster1(int number, int size, int Hp, int damage, int speed, int score, int Lv){
        for(int i=0 ; i<number ; i++){
            monster1Set.add(new Monster1(1400+rand.nextInt(number*3)*100+i*30, 70*size, 150*size, Hp, damage, speed, score, Lv));
        }
    }

    public void build_monster2(int number, int size, int Hp, int damage, int speed, int score, int Lv){
        for(int i=0 ; i<number ; i++){
            monster2Set.add(new Monster2(1400+rand.nextInt(number*3)*100+i*30, 70*size, 70*size, Hp, damage, speed, score, Lv));
        }
    }

    public void end(){
        while(monster0Set.size()!=0){
            Game.player.score-=monster0Set.get(0).score;
            monster0Set.get(0).dead();
        }
        while(monster1Set.size()!=0){
            Game.player.score-=monster1Set.get(0).score;
            monster1Set.get(0).dead();
        }
        while(monster2Set.size()!=0){
            Game.player.score-=monster2Set.get(0).score;
            monster2Set.get(0).dead();
        }
    }
    //Run
    public void run(){
        while(start1&&start){
            if(monster0Set.size()==0&&countmon>=5&&Game.player.Lv==1){
                build_monster0(1, 2, 5, 1, 15, 5, 1);
            }else if(monster0Set.size()==0&&Game.player.Lv==1){
                build_monster0(5, 1, 1, 1, 10, 1, 0);
            }
            
            if(monster1Set.size()==0&&countmon>=11&&Game.player.Lv==2){
                build_monster1(1, 2, 10, 1, 15, 5, 1);
            }else if(monster1Set.size()==0&&Game.player.Lv==2){
                build_monster1(5, 1, 2, 1, 10, 1, 0);
            }

            if(monster2Set.size()==0&&countmon>=17&&Game.player.Lv==3){
                build_monster2(1, 2, 5, 1, 15, 5, 1);
            }else if(monster2Set.size()==0&&Game.player.Lv==3){
                build_monster2(5, 1, 2, 1, 10, 1, 0);
            }

            if(Game.player.Lv==4){
                build_monster0(1, 2, 5, 1, 15, 5, 0);
                build_monster1(1, 2, 10, 1, 15, 5, 0);
                build_monster2(1, 2, 5, 1, 15, 5, 0);
                start1=false;
                start2=true;
            }
            try{Thread.sleep(1000);
            }catch(InterruptedException e){}
        }

        while(start2&&start){
            if(monster0Set.size()==0&&countmon>=24&&Game.player.Lv==4){
                build_monster0(1, 3, 10, 1, 30, 5, 1);
            }else if(monster0Set.size()==0&&countmon>=21&&Game.player.Lv==4){
                build_monster0(1, 2, 5, 1, 10, 5, 0);
            }
            
            if(monster1Set.size()==0&&countmon>=28&&Game.player.Lv==5){
                build_monster1(1, 3, 20, 1, 30, 5, 1);
            }else if(monster1Set.size()==0&&Game.player.Lv==5){
                build_monster1(1, 2, 10, 1, 10, 5, 0);
            }

            if(monster2Set.size()==0&&countmon>=33&&Game.player.Lv==6){
                build_monster2(1, 3, 10, 1, 30, 5, 1);
            }else if(monster2Set.size()==0&&Game.player.Lv==6){
                build_monster2(1, 2, 5, 1, 10, 5, 0);
            }

            if(Game.player.Lv==7){
                build_monster0(1, 3, 10, 1, 30, 5, 0);
                build_monster1(1, 3, 20, 1, 30, 5, 0);
                build_monster2(1, 3, 20, 1, 30, 5, 0);
                start2=false;
            }
            try{Thread.sleep(1000);
            }catch(InterruptedException e){}
        }

        while(start){
            if(countmon>=33){
                if(countmon%15==0){
                    int ranboss = rand.nextInt(6);
                    switch(ranboss)
                    {
                        case 0:build_monster0(1, 2, 3*Game.player.Lv, 1, 15, Game.player.Lv, 1);break;
                        case 1:build_monster0(1, 3, 3*Game.player.Lv, 1, 30, Game.player.Lv, 1);break;
                        case 2:build_monster1(1, 2, 4*Game.player.Lv, 1, 15, Game.player.Lv, 1);break;
                        case 3:build_monster1(1, 3, 4*Game.player.Lv, 1, 30, Game.player.Lv, 1);break;
                        case 4:build_monster2(1, 2, 3*Game.player.Lv, 1, 15, Game.player.Lv, 1);break;
                        case 5:build_monster2(1, 3, 3*Game.player.Lv, 1, 30, Game.player.Lv, 1);break;
                    }
                    while(monster0Set.size()!=0||monster1Set.size()!=0||monster2Set.size()!=0){
                        try{Thread.sleep(1000);
                        }catch(InterruptedException e){}
                    }
                }else if(monster0Set.size()+monster1Set.size()+monster2Set.size()<Game.player.Lv){
                    int ranmon = rand.nextInt(3);
                    switch(ranmon)
                    {
                        case 0:build_monster0(1, 1, 1+Game.player.Lv/10, 1, 10, 1, 0);break;
                        case 1:build_monster1(1, 1, 1+Game.player.Lv/10, 1, 10, 1, 0);break;
                        case 2:build_monster2(1, 1, 1+Game.player.Lv/10, 1, 10, 1, 0);break;
                    }
                }
            }
            try{Thread.sleep(1000);
            }catch(InterruptedException e){}
        }
    end();
    }
    
    
}