package sample;

import java.util.List;

public class handleError {
    public boolean intError(String s){
        if(s.equals("")) return false;
        char[] ss = s.toCharArray();

        for(int i = 0; i < s.length(); i++){
            if(ss[i] > '9' || ss[i] < '0'){
                return false;
            }
        }
        return true;
    }

    public boolean doubleError(String s){
        if(s.equals("")) return false;
        char[] ss = s.toCharArray();
        int dc = 0;

        for(int i = 0; i < s.length(); i++){
            if(ss[i] > '9' || ss[i] < '0'){
                if(ss[i] == '.' && dc == 0 && i != s.length() - 1){
                    dc++;
                }
                else {
                    return false;
                }

            }
        }
        return true;
    }

    public boolean errorPlayer(List<Player> ap, String name){
        for(Player p : ap){
            if(p.getName().equalsIgnoreCase(name)){
                return false;
            }
        }
        return true;
    }

    public boolean numError(String s, List<Club> ac, Player p){
        int x = Integer.parseInt(s);

        for (Club c : ac){
            if(c.getName().equalsIgnoreCase(p.getClub())){
                if(c.club_num_check(x)) return false;
            }
        }

        return true;
    }

}
