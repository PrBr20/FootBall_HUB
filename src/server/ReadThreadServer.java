package server;

import javafx.application.Platform;
import sample.Player;
import util.LoginDTO;
import util.NetworkUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReadThreadServer implements Runnable {
    private final Thread thr;
    private final NetworkUtil networkUtil;
    public HashMap<String, String> userMap;
    private Server server;



    public ReadThreadServer(HashMap<String, String> map, NetworkUtil networkUtil) {
        this.userMap = map;
        this.networkUtil = networkUtil;
        this.thr = new Thread(this);
        thr.start();
    }

    public void  setServer(Server server) {
        this.server = server;
    }

    public void run() {
        try {
            while (true) {
                Object o = networkUtil.read();
                if (o != null) {
                    if (o instanceof LoginDTO) {
                        LoginDTO loginDTO = (LoginDTO) o;
                        if(loginDTO.getUserName().equals("closeConnection") && loginDTO.isStatus()){
                            //System.out.println(loginDTO.getPassword());
                            if(loginDTO.getPassword()!=null) {
                                //NetworkUtil net = server.clientMap.get(loginDTO.getPassword());
                                //net.closeConnection();
                                server.clientMap.remove(loginDTO.getPassword());
                            }
                        }
                        else if(loginDTO.getPassword().equals("transferWindow") && loginDTO.isStatus()){
                            List<Player> ap = new ArrayList<>();
                            for(Player p : server.transferWindow){
                                if(!p.getClub().equals(loginDTO.getUserName())) ap.add(p);
                            }
                            networkUtil.write(ap);

                            //networkUtil.write(server.transferWindow);
                        }
                        else if(loginDTO.getPassword().equals("buyPlayer") && loginDTO.isStatus()){
                            Player g = loginDTO.getList().get(0);
                            //System.out.println(g);

                            System.out.println(g);
                            Player tmp = new Player();
                            for(Player p : server.transferWindow){
                                if(p.getName().equalsIgnoreCase(g.getName())){
                                    tmp = p;
                                    break;
                                }
                            }
                            for(Player pl : server.playerList){
                                if(pl.getName().equalsIgnoreCase(g.getName())) {
                                    pl.setClub(loginDTO.getUserName());
                                    break;
                                }
                            }
                            server.transferWindow.remove(tmp);

                            server.clientMap.forEach((key,value) -> {
                                List<Player> ap = new ArrayList<>();
                                for(Player p : server.transferWindow){
                                    if(!p.getClub().equals(key)) ap.add(p);
                                }
                                try {
                                    value.write(ap);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                        }
                        else if(loginDTO.getUserName().equals("sell") && loginDTO.getPassword().equals("player") && loginDTO.isStatus()){
                            //System.out.println("hi");
                            for(Player p : loginDTO.getList()){
                                server.transferWindow.add(p);
                            }

                            server.clientMap.forEach((key,value) -> {
                                List<Player> ap = new ArrayList<>();
                                for(Player p : server.transferWindow){
                                    if(!p.getClub().equals(key)) ap.add(p);
                                }
                                try {
                                    value.write(ap);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                            //networkUtil.write(server.transferWindow);
                        }
                        else {
                            String password = userMap.get(loginDTO.getUserName());
                            loginDTO.setStatus(true);
                            //loginDTO.setUserName("lol");

                            server.clientMap.forEach((key,value) -> {
                                if(key.equals(loginDTO.getUserName())) {
                                    loginDTO.setStatus(false);
                                }
                            });

                            if(loginDTO.isStatus() == false){
                                if(loginDTO.getPassword().equals(password) == true)loginDTO.setUserName("Already logged in");
                            }

                            if(loginDTO.isStatus()) loginDTO.setStatus(loginDTO.getPassword().equals(password));

                            if(loginDTO.isStatus()) {
                                List<Player> ap = new ArrayList<>();
                                //System.out.println(server.playerList);
                                for(Player p : server.playerList){
                                    if(p.getClub().equals(loginDTO.getUserName())) ap.add(p);
                                }
                                System.out.println(ap);
                                for(Player p : server.transferWindow){
                                    Player foo = null ;
                                    for(Player pl : ap){
                                        if(pl.getName().equals(p.getName())){
                                            foo = pl;
                                            break;
                                        }
                                    }
                                    if(foo != null){
                                       ap.remove(foo);
                                    }
                                }
                                loginDTO.setList(ap);
                            }
                            if(loginDTO.isStatus())server.clientMap.put(loginDTO.getUserName(), networkUtil);
                            networkUtil.write(loginDTO);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                networkUtil.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



