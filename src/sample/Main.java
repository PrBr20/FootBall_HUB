package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.LoginDTO;
import util.NetworkUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private Stage stage, transferStage;

    public static int mod = 1;
    public clubNewPageController clubNewPageController = null;

    public Stage getStage() {
        return stage;
    }

    FileOperations a = new FileOperations();
    List<Player> playerList, tp, curr;

    createclubs b = new createclubs();
    List<Club> clubList;

    allSearch c = new allSearch();


    private NetworkUtil networkUtil;

    public NetworkUtil getNetworkUtil() {
        return networkUtil;
    }

    public void closeConnection(String clubName) throws Exception {
        LoginDTO login = new LoginDTO();
        login.setUserName(clubName);
        login.setPassword("closeConnection");
        login.setStatus(true);
        getNetworkUtil().write(login);
    }

    private void connectToServer() throws IOException {
        String serverAddress = "127.0.0.1";
        int serverPort = 33333;
        networkUtil = new NetworkUtil(serverAddress, serverPort);
        new ReadThread(this);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        playerList = a.readFromFile();
        tp = new ArrayList<>();
        clubList = b.creating_clubs(playerList);

        stage = primaryStage;
        showLoginPage();

        stage.setOnCloseRequest(event ->{
            event.consume();
            try {
                showAlert("Are You Sure to Exit?");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void closeStage() throws Exception{
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUserName("closeConnection");
        if(clubNewPageController != null) {
            loginDTO.setPassword(clubNewPageController.getClubName());
            System.out.println(loginDTO.getPassword());
        }
        loginDTO.setStatus(true);
        getNetworkUtil().write(loginDTO);
        getNetworkUtil().closeConnection();
        stage.close();
    }

    public void showLoginPage() throws Exception {
        // connecting to server
        curr = new ArrayList<>();
        connectToServer();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("loginPage.fxml"));
        Parent root = loader.load();

        // Loading the controller
        LoginController controller = loader.getController();
        controller.setMain(this);
        controller.load();

        // Set the primary stage
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 500, 400));
        stage.show();
    }

    public void clubHomePage(List<Player> ap, String clubName) throws IOException {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("clubNewPage.fxml"));
        Parent root = loader.load();

        // Loading the controller
        clubNewPageController controller = loader.getController();
        controller.load(ap, clubName);
        controller.setMain(this);
        clubNewPageController = controller;

        // Set the primary stage
        stage.setTitle("clubName");
        stage.setScene(new Scene(root, 771, 527));
        stage.show();
    }

    public List<Player> getCurr() {return curr;}


    public void tW(List<Player> ap) throws Exception {
        clubNewPageController.updateTransferList(ap);
    }

    public void addToCLub(Player p){
        clubNewPageController.updateOwnPlayer(p);
    }

    public void changeClub(Player p, String s){
        for(Player pl : playerList){
            if(pl.getName().equals(p.getName())){
                pl.setCountry(s);
                break;
            }
        }
    }


    public void showAlert(String l) throws  Exception{
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("invalidMessage.fxml"));
        Parent root = loader.load();

        invalidController invalidController = loader.getController();
        invalidController.setMain(this);
        invalidController.load(l);

        // Set the primary stage
        transferStage = new Stage();
        transferStage.setTitle("Login Error");
        transferStage.setScene(new Scene(root, 362, 237));
        transferStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
