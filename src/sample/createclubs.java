package sample;

import java.util.ArrayList;
import java.util.List;


public class createclubs {
    public List<Club> creating_clubs(List<Player> ap) {
        List <Club> all_clubs = new ArrayList<>();

        int clcnt = 0;
        Club[] clbs = new Club[5];
        for(int i = 0; i < 5; i++) clbs[i] = new Club();

        for(Player p : ap){
            int kk = 0;
            for(int i = 0; i < clcnt; i++){
                if(p.getClub().equals(clbs[i].getName()) ) {
                    clbs[i].add_player(p);
                    kk = 1;
                    break;
                }
            }
            if(kk == 0){
                clbs[clcnt].setName(p.getClub());
                clbs[clcnt].add_player(p);
                clcnt++;
            }
        }

        for (int i = 0; i < clcnt; i++) all_clubs.add(clbs[i]);

        return all_clubs;
    }


    public int add_p_c(Player p, List<Club> ac){
        for(Club c : ac){
            if(c.getName().equalsIgnoreCase(p.getClub())){
                if(c.getPlayercount() < 7) return 1;
                else return 0;
            }
        }
        return 2;
    }

    public List<Club> update_list(Player np, List<Club> ac){
        for(Club c : ac){
            if(c.getName().equalsIgnoreCase(np.getClub())){
                c.add_player(np);
                break;
            }
        }

        return ac;
    }

    public List<Club> add_club(Player np, List<Club> ac){

        Club c = new Club();
        c.setName(np.getClub());
        c.add_player(np);
        ac.add(c);


        return ac;
    }

}
