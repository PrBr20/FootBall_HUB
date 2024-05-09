package util;

import sample.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LoginDTO implements Serializable {

    public LoginDTO(){
        ap = new ArrayList<>();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    public void setList(List<Player> ap) {
        this.ap = ap;
    }

    public List<Player> getList() {
        return ap;
    }

    private String userName;
    private String password;
    private boolean status;
    private List<Player> ap;
    private boolean logoutPressed;

    public boolean isLogoutPressed() {
        return logoutPressed;
    }

    public void setLogoutPressed(boolean logoutPressed) {
        this.logoutPressed = logoutPressed;
    }
}
