package server;

import javafx.application.Platform;
import sample.*;
import util.NetworkUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Server {

    private ServerSocket serverSocket;
    public HashMap<String, String> userMap;
    public HashMap<String, NetworkUtil> clientMap;

    public List<Player> transferWindow;
    int i = 1;

    FileOperations a = new FileOperations();
    List<Player> playerList, tp;

    createclubs b = new createclubs();
    List<Club> clubList;

    allSearch c = new allSearch();

    Server() throws Exception {
        playerList = a.readFromFile();
        tp = new ArrayList<>();
        transferWindow = new ArrayList<>();
        clubList = b.creating_clubs(playerList);
        clientMap = new HashMap<>();

        userMap = new HashMap<>();
        for(Club c : clubList){
            userMap.put(c.getName(), "1234");
        }

        try {
            serverSocket = new ServerSocket(33333);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
    }

    public void addtoList(List<Player> ap){
        for(Player p : ap){
            transferWindow.add(p);
        }
    }

    public void removetW(Player p){
        transferWindow.remove(p);
    }


    public void serve(Socket clientSocket) throws IOException {
        NetworkUtil networkUtil = new NetworkUtil(clientSocket);
        ReadThreadServer readThreadServer = new ReadThreadServer(userMap, networkUtil);
        readThreadServer.setServer(this);
    }

    public static void main(String[] args) throws Exception {
        new Server();
    }
}
