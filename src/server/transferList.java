package server;

import javafx.application.Platform;
import sample.Player;

import java.util.ArrayList;
import java.util.List;

public class transferList {
    List<Player> ap;

    transferList(List<Player> ap){
        this.ap = ap;
    }

    List<Player> getList(){
        return ap;
    }
}
