package sample;

import javafx.scene.control.skin.SliderSkin;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class allSearch {
    Scanner sc = new Scanner(System.in);

    handleError h = new handleError();

    List<Player> gc = new ArrayList<>();


    public List<Player> s_b_player(List<Player> ap, String input) {
        gc.clear();
        for (Player p : ap) {
            if (p.getName().equalsIgnoreCase(input)) {
                gc.add(p);
            }
        }
        return gc;
    }


    public List<Player> s_b_country(List<Player> ap, String input){
        gc.clear();
        for(Player p : ap){
            if(p.getCountry().equalsIgnoreCase(input) ) {
                    gc.add(p);
            }
        }
       return gc;
    }


    public List<Player> s_b_position(List<Player> ap, String input_pos){
        gc.clear();
        for(Player p : ap){
            if(p.getPosition().equalsIgnoreCase(input_pos) ) {
                gc.add(p);
            }
        }
        return gc;
    }


    public List<Player> s_b_salary(List<Player> ap, Double input_minsal, Double input_maxsal){

        gc.clear();
        for(Player p : ap){
            double t = p.getWeekly_salary();
            if(t >= input_minsal && t <= input_maxsal) {
                gc.add(p);
            }
        }

        return gc;
    }


    public List<countryCount> s_b_country_wise_pc(List<Player> ap){
        int k = 0,  contsz = 0;
        String[] conts = new String[ap.size()];
        int[] ccount = new int[ap.size()];

        List<countryCount> foo = new ArrayList<>();

        for(Player p : ap){
            int kk = 0;
            for(int i = 0; i < contsz; i++){
                if(p.getCountry().equalsIgnoreCase(conts[i])) {ccount[i]++; kk = 1;}
            }
            if(kk == 0){
                conts[contsz] = p.getCountry();
                ccount[contsz] = 1;
                contsz++;
            }
        }

        for(int i = 0; i<contsz; i++){
            foo.add(new countryCount(conts[i], ccount[i]));
        }

        return foo;
    }


    public List<Player> s_b_club_maxsal(List<Club> ac, String ip){
        gc.clear();

        for(Club c : ac){
            if(c.getName().equalsIgnoreCase(ip) ){
                gc = c.maxsal();
            }
        }

        return  gc;
    }

    public List<Player> s_b_club_maxage(List<Club> ac, String ip){
        gc.clear();
        for(Club c : ac){
            if(c.getName().equalsIgnoreCase(ip)){
                gc = c.maxage();
            }
        }

        return gc;
    }

    public List<Player> s_b_club_maxheight(List<Club> ac, String ip){
        gc.clear();

        for(Club c : ac){
            if(c.getName().equalsIgnoreCase(ip) ){
                gc = c.maxheight();
            }
        }

        return  gc;
    }


    public Double s_b_club_totsal(List<Player> ap){
        Double sal = 0.0;

        for(Player p : ap){
            sal += p.getWeekly_salary();
        }

        return sal*52*1.0;
    }

    public List<Player> maxsal(List<Player> ap){
        double tsal = 0.0;
        gc.clear();

        for(Player p : ap){
            if(p.getWeekly_salary() > tsal){
                tsal = p.getWeekly_salary();
            }
        }

        for(Player p : ap){
            if(p.getWeekly_salary() == tsal) {
                gc.add(p);
            }
        }

        return gc;
    }


    public List<Player> maxage(List<Player> ap){
        int tage= 0;
        gc.clear();

        for(Player p : ap){
            if(p.getAge() > tage){
                tage = p.getAge();
            }
        }

        for(Player p : ap){
            if(p.getAge() == tage) {
                gc.add(p);
            }
        }

        return gc;
    }



    public List<Player> maxheight(List<Player> ap){
        double theight = 0.0;
        gc.clear();

        for(Player p : ap){
            if(p.getHeight() > theight){
                theight = p.getHeight();
            }
        }

        for(Player p : ap){
            if(p.getHeight() == theight) {
                gc.add(p);
            }
        }

        return gc;
    }


}
