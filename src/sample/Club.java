package sample;

import java.util.ArrayList;
import java.util.List;

public class Club {
    private int playercount;
    private String name;
    private Player[] players;
    List<Player> gg = new ArrayList<>();

    Club(){
        players = new Player[7];
    }

    public void add_player(Player p1){
        players[playercount] = p1;
        playercount++;
    }


    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public int getPlayercount(){
        return this.playercount;
    }


    public List<Player> maxsal(){
        double tsal = 0.0;
        gg.clear();

        for(int i=0; i<playercount; i++){
            if(players[i].getWeekly_salary() > tsal){
                tsal = players[i].getWeekly_salary();
            }
        }

        for(int i=0; i<playercount; i++){
            if(players[i].getWeekly_salary() == tsal) {
                gg.add(players[i]);
            }
        }

        return gg;
    }


    public List<Player> maxage(){
        int tage= 0;
        gg.clear();

        for(int i=0; i<playercount; i++){
            if(players[i].getAge() > tage){
                tage = players[i].getAge();
            }
        }

        for(int i=0; i<playercount; i++){
            if(players[i].getAge() == tage) {
                gg.add(players[i]);
            }
        }

        return gg;
    }



    public List<Player> maxheight(){
        double theight = 0.0;
        gg.clear();

        for(int i=0; i<playercount; i++){
            if(players[i].getHeight() > theight){
                theight = players[i].getHeight();
            }
        }

        for(int i=0; i<playercount; i++){
            if(players[i].getHeight() == theight) {
                gg.add(players[i]);
            }
        }

        return gg;
    }


    public double totsal (){
        double s = 0.0;

        for(int i = 0; i < playercount; i++){
            s += players[i].getWeekly_salary();
        }

        return s*52;
    }

    public boolean club_num_check(int x){
        for(int i = 0; i<playercount; i++){
            if(players[i].getNumber() == x) return true;
        }
        return false;
    }
}
