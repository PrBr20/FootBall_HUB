package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.LoginDTO;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class clubNewPageController extends Application implements Initializable {
    private Main main;
    private Stage stage;

    private allSearch c = new allSearch();

    private boolean init = true;
    private int sellPrice;

    ObservableList<Player> data, tata;
    List<Player> ownPlayer, curr, buyable;
    List<countryCount> curr2;
    ObservableList<countryCount> data2;

    private String clubName;

    @FXML
    private ImageView clubImage;

    @FXML
    private ImageView footballImage;

    @FXML
    private Label clubDetails;

    @FXML
    private Label uiLabel;

    @FXML
    private Label loginAs;

    @FXML
    private Label pageTitle;

    @FXML
    private Label totalPlayers;

    @FXML
    private AnchorPane homePage;

    @FXML
    private AnchorPane Players;

    @FXML
    private AnchorPane searchPane;

    @FXML
    private AnchorPane salaryPane;

    @FXML
    private AnchorPane table;

    @FXML
    private ChoiceBox<String> searchChoice;
    String[] choiceValues = {"None","Search By Name", "Search By Country" , "Search By Position", "Search By Salary Range", "Country-wise Player Count", "Max Salary", "Max Age", "Max Height"};

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchString = new TextField();

    @FXML
    private TextField minimumSalary = new TextField();

    @FXML
    private TextField maximumSalary = new TextField();

    @FXML
    private TableView playersTable;

    @FXML
    private TableView transferTable;

    @FXML
    private TableView countryTable;

    @FXML
    private Button logOut;

    @FXML
    private Button homeButton;

    @FXML
    private Button playersButton;

    @FXML
    private Button transferButton;

    @FXML
    private Button buyButton;

    @FXML
    private Button sellButton;

    @FXML
    private Button detailsButton;

    @FXML
    void searchPlayers(ActionEvent event) {
        buyButton.setVisible(false);
        sellButton.setVisible(true);
        detailsButton.setVisible(true);
        Players.setVisible(true);
        searchChoice.setVisible(true);
        searchButton.setVisible(true);
        searchPane.setVisible(true);
        salaryPane.setVisible(false);
        homePage.setVisible(false);
        playersTable.setVisible(true);
        transferTable.setVisible(false);
        countryTable.setVisible(false);
        pageTitle.setText("Players");
        clubDetails.setText("");
        searchChoice.getSelectionModel().select(0);

        data = FXCollections.observableArrayList(ownPlayer);
        playersTable.setEditable(true);
        playersTable.setItems(data);
        playersTable.refresh();
    }

    @FXML
    void showHomePage(ActionEvent event) {
        homePage.setVisible(true);
        Players.setVisible(false);
        buyButton.setVisible(false);
        sellButton.setVisible(false);
        detailsButton.setVisible(false);
        pageTitle.setText("HomePage");
        clubDetails.setText(clubName + "\n" + "Total Players : " + ownPlayer.size() + "\n" + "Total Salary : " + c.s_b_club_totsal(ownPlayer).intValue() + "$");
    }

    @FXML
    void showTransferList(ActionEvent event) throws Exception{
        buyButton.setVisible(true);
        sellButton.setVisible(false);
        detailsButton.setVisible(true);
        Players.setVisible(true);
        searchChoice.setVisible(false);
        searchButton.setVisible(false);
        searchPane.setVisible(false);
        salaryPane.setVisible(false);
        playersTable.setVisible(false);
        transferTable.setVisible(true);
        countryTable.setVisible(false);
        clubDetails.setText("");
        pageTitle.setText("Transfer List");

        LoginDTO login = new LoginDTO();
        login.setUserName(clubName);
        login.setPassword("transferWindow");
        login.setStatus(true);

        main.getNetworkUtil().write(login);

        data.clear();
        data = FXCollections.observableArrayList(buyable);
        transferTable.setEditable(true);
        transferTable.setItems(data);
        transferTable.refresh();
    }

    public void updateTransferList(List<Player> ap){
        buyable = ap;

        if(transferTable.isVisible()){
            data.clear();
            data = FXCollections.observableArrayList(buyable);
            transferTable.setEditable(true);
            transferTable.setItems(data);
            transferTable.refresh();
        }
    }

    private void initializePlayerColumns() {
        TableColumn<Player, String> firstNameCol = new TableColumn<>("Name");
        firstNameCol.setMinWidth(80);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Player, String> lastNameCol = new TableColumn<>("Country");
        lastNameCol.setMinWidth(80);
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("country"));

        TableColumn<Player, String> emailCol = new TableColumn<>("Club");
        emailCol.setMinWidth(80);
        emailCol.setCellValueFactory(new PropertyValueFactory<>("club"));

        TableColumn<Player, String> buttonCol = new TableColumn<>("Position");
        buttonCol.setMinWidth(80);
        buttonCol.setCellValueFactory(new PropertyValueFactory<>("position"));

        TableColumn<Player, String> ageCol = new TableColumn<>("Age");
        ageCol.setMinWidth(80);
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn<Player, String> heightCol = new TableColumn<>("Height");
        heightCol.setMinWidth(80);
        heightCol.setCellValueFactory(new PropertyValueFactory<>("height"));

        TableColumn<Player, String> numberCol = new TableColumn<>("Number");
        numberCol.setMinWidth(80);
        numberCol.setCellValueFactory(new PropertyValueFactory<>("number"));

        TableColumn<Player, String> salaryCol = new TableColumn<>("Salary");
        salaryCol.setMinWidth(80);
        salaryCol.setCellValueFactory(new PropertyValueFactory<>("weekly_salary"));

        playersTable.getColumns().addAll(firstNameCol, lastNameCol, emailCol, buttonCol, ageCol, heightCol, numberCol, salaryCol);
    }

    private void initializeTransferColumns() {
        TableColumn<Player, String> firstNameCol = new TableColumn<>("Name");
        firstNameCol.setMinWidth(80);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Player, String> lastNameCol = new TableColumn<>("Country");
        lastNameCol.setMinWidth(80);
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("country"));

        TableColumn<Player, String> emailCol = new TableColumn<>("Club");
        emailCol.setMinWidth(80);
        emailCol.setCellValueFactory(new PropertyValueFactory<>("club"));

        TableColumn<Player, String> buttonCol = new TableColumn<>("Position");
        buttonCol.setMinWidth(80);
        buttonCol.setCellValueFactory(new PropertyValueFactory<>("position"));

        TableColumn<Player, String> ageCol = new TableColumn<>("Age");
        ageCol.setMinWidth(80);
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn<Player, String> heightCol = new TableColumn<>("Height");
        heightCol.setMinWidth(80);
        heightCol.setCellValueFactory(new PropertyValueFactory<>("height"));

        TableColumn<Player, String> numberCol = new TableColumn<>("Number");
        numberCol.setMinWidth(80);
        numberCol.setCellValueFactory(new PropertyValueFactory<>("number"));

        TableColumn<Player, String> salaryCol = new TableColumn<>("Salary");
        salaryCol.setMinWidth(80);
        salaryCol.setCellValueFactory(new PropertyValueFactory<>("weekly_salary"));

        TableColumn<Player, String> priceCol = new TableColumn<>("Price");
        priceCol.setMinWidth(80);
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        transferTable.getColumns().addAll(firstNameCol, lastNameCol, emailCol, buttonCol, ageCol, heightCol, numberCol, salaryCol, priceCol);
    }

    private void initializeCountryColumns(){
        TableColumn<countryCount, String> firstNameCol = new TableColumn<>("Country");
        firstNameCol.setMinWidth(272);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("countryName"));

        TableColumn<countryCount, String> lastNameCol = new TableColumn<>("Player Count");
        lastNameCol.setMinWidth(272);
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("count"));

        countryTable.getColumns().addAll(firstNameCol,lastNameCol);
    }


    public void load(List<Player> pl, String clubName) {
        if (init) {
            homePage.setVisible(true);
            Players.setVisible(false);
            sellButton.setVisible(false);
            buyButton.setVisible(false);
            detailsButton.setVisible(false);
            searchChoice.getItems().addAll(choiceValues);

            initializePlayerColumns();
            initializeTransferColumns();
            initializeCountryColumns();
            pageTitle.setText("HomePage");
            uiLabel.setText("FootBall" + "\n" + "      HUB");
            loginAs.setText("logged in as " + clubName);
            ownPlayer = new ArrayList<>();
            curr = new ArrayList<>();
            buyable = new ArrayList<>();
            curr2 = new ArrayList<>();
            ownPlayer = pl;
            init = false;
            this.clubName = clubName;
            clubDetails.setText(clubName + "\n" + "Total Players : " + ownPlayer.size() + "\n" + "Total Salary : " + c.s_b_club_totsal(ownPlayer).intValue() + "$");
            String s = clubName + ".png";
            Image img = new Image(Main.class.getResourceAsStream(s));
            clubImage.setImage(img);
            img = new Image(Main.class.getResourceAsStream("Football1.png"));
            footballImage.setImage(img);
        }

        data = FXCollections.observableArrayList(ownPlayer);

        //playerTableView = new TableView();
        playersTable.setEditable(true);
        playersTable.setItems(data);
    }

    @FXML
    void startSearch(ActionEvent event) {
        String p = (String) searchChoice.getValue();
        data.clear();
        curr.clear();

        if(p.equals("Search By Name")) {
            String s = (String) searchString.getText();
            curr = c.s_b_player(ownPlayer, s);
        }
        else if(p.equals("Search By Country")){
            String s = (String) searchString.getText();
            curr = c.s_b_country(ownPlayer, s);
        }
        else if(p.equals("Search By Position")){
            String s = (String) searchString.getText();
            //System.out.println("hi");
            curr = c.s_b_position(ownPlayer, s);
        }
        else if(p.equals("Search By Salary Range")){
            Double min = Double.parseDouble(minimumSalary.getText()),  max = Double.parseDouble(maximumSalary.getText());
            curr = c.s_b_salary(ownPlayer, min, max);
        }
        else if(p.equals("Country-wise Player Count")){
            curr2 = c.s_b_country_wise_pc(ownPlayer);
        }
        else if(p.equals("Max Salary")){
            String s = (String) searchString.getText();
            curr = c.maxsal(ownPlayer);
        }
        else if(p.equals("Max Age")){
            String s = (String) searchString.getText();
            curr = c.maxage(ownPlayer);
        }
        else if(p.equals("Max Height")){
            String s = (String) searchString.getText();
            curr = c.maxheight(ownPlayer);
        }

        if(!p.equals("Country-wise Player Count")){
            data = FXCollections.observableArrayList(curr);
            playersTable.setEditable(true);
            playersTable.setItems(data);
            playersTable.refresh();
        }
        else {
            data2 = FXCollections.observableArrayList(curr2);
            countryTable.setEditable(true);
            countryTable.setItems(data2);
            countryTable.refresh();
        }

    }

    public void sellPlayer1() throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("setPrice.fxml"));
        Parent root = loader.load();

        // Loading the controller
        setPriceController controller = loader.getController();
        controller.setClubController(this);

        // Set the primary stage
        stage = new Stage();
        stage.setTitle("PricePage");
        stage.setScene(new Scene(root, 468, 288));
        stage.show();
    }

    public void sellPlayer2(){
        Player p = (Player) playersTable.getSelectionModel().getSelectedItem();
        System.out.println(sellPrice);
        p.setPrice(sellPrice);
        ownPlayer.remove(p);

        data = FXCollections.observableArrayList(ownPlayer);
        playersTable.setEditable(true);
        playersTable.setItems(data);
        playersTable.refresh();

        List<Player> nl = new ArrayList<>();
        nl.add(p);

        LoginDTO login = new LoginDTO();
        login.setUserName("sell");
        login.setPassword("player");
        login.setStatus(true);
        login.setList(nl);
        try {
            main.getNetworkUtil().write(login);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void sellPlayer() throws Exception {
        sellPlayer1();
    }

    public void getPrice(String s){
        this.sellPrice = Integer.parseInt(s);
    }

    public void updateOwnPlayer(Player p) {
        ownPlayer.add(p);
        data = FXCollections.observableArrayList(ownPlayer);
        playersTable.setEditable(true);
        playersTable.setItems(data);
        playersTable.refresh();
    }

    public void setMain(Main main){this.main = main;}


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        searchChoice.setOnAction(this::changePrompt);
    }

    private void changePrompt(ActionEvent actionEvent) {
        if(searchChoice.getValue().equals("Search By Name")) {
            minimumSalary.setVisible(false);
            maximumSalary.setVisible(false);
            searchString.setVisible(true);
            searchPane.setVisible(true);
            salaryPane.setVisible(false);
            countryTable.setVisible(false);
            playersTable.setVisible(true);
            searchString.setPromptText("Enter Player name");
            searchString.setFocusTraversable(false);
        }
        else if(searchChoice.getValue().equals("Search By Country")){
            minimumSalary.setVisible(false);
            maximumSalary.setVisible(false);
            searchString.setVisible(true);
            searchPane.setVisible(true);
            salaryPane.setVisible(false);
            countryTable.setVisible(false);
            playersTable.setVisible(true);
            searchString.setPromptText("Enter Player Country");
            searchString.setFocusTraversable(false);
        }
        else if(searchChoice.getValue().equals("Search By Position")){
            minimumSalary.setVisible(false);
            maximumSalary.setVisible(false);
            searchString.setVisible(true);
            searchPane.setVisible(true);
            salaryPane.setVisible(false);
            countryTable.setVisible(false);
            playersTable.setVisible(true);
            searchString.setPromptText("Enter Player Position");
            searchString.setFocusTraversable(false);
        }
        else if(searchChoice.getValue().equals("Search By Salary Range")){
            minimumSalary.setVisible(true);
            maximumSalary.setVisible(true);
            searchString.setVisible(false);
            searchPane.setVisible(false);
            salaryPane.setVisible(true);
            countryTable.setVisible(false);
            playersTable.setVisible(true);
            minimumSalary.setPromptText("Minimum Salary");
            minimumSalary.setFocusTraversable(false);
            maximumSalary.setPromptText("Maximum Salary");
            maximumSalary.setFocusTraversable(false);
        }
        else if(searchChoice.getValue().equals("Country-wise Player Count")){
            minimumSalary.setVisible(false);
            maximumSalary.setVisible(false);
            searchString.setVisible(true);
            searchPane.setVisible(true);
            salaryPane.setVisible(false);
            playersTable.setVisible(false);
            countryTable.setVisible(true);
            //playersTable.setVisible(true);
            searchString.setPromptText("None");
            searchString.setFocusTraversable(false);

            curr2.clear();
            data2 = FXCollections.observableArrayList(curr2);
            countryTable.setEditable(true);
            countryTable.setItems(data2);
            countryTable.refresh();
        }
        else if(searchChoice.getValue().equals("Max Salary")){
            minimumSalary.setVisible(false);
            maximumSalary.setVisible(false);
            searchString.setVisible(true);
            searchPane.setVisible(true);
            salaryPane.setVisible(false);
            countryTable.setVisible(false);
            playersTable.setVisible(true);
            searchString.setPromptText("None");
            searchString.setFocusTraversable(false);
        }
        else if(searchChoice.getValue().equals("Max Age")){
            minimumSalary.setVisible(false);
            maximumSalary.setVisible(false);
            searchString.setVisible(true);
            searchPane.setVisible(true);
            salaryPane.setVisible(false);
            countryTable.setVisible(false);
            playersTable.setVisible(true);
            searchString.setPromptText("None");
            searchString.setFocusTraversable(false);
        }
        else if(searchChoice.getValue().equals("Max Height")){
            minimumSalary.setVisible(false);
            maximumSalary.setVisible(false);
            searchString.setVisible(true);
            searchPane.setVisible(true);
            salaryPane.setVisible(false);
            countryTable.setVisible(false);
            playersTable.setVisible(true);
            searchString.setPromptText("None");
            searchString.setFocusTraversable(false);
        }
    }

    @FXML
    public void buyPlayer() throws Exception{
        Player p = (Player) transferTable.getSelectionModel().getSelectedItem();

        ObservableList<Player> ProductsSelected;
        ProductsSelected = transferTable.getSelectionModel().getSelectedItems();
        transferTable.getItems().removeAll(List.copyOf(ProductsSelected));
        transferTable.refresh();

        buyable.remove(p);
        p.setClub(clubName);
        updateOwnPlayer(p);
        main.changeClub(p, clubName);

        List<Player> aa = new ArrayList<>();
        aa.add(p);

        LoginDTO login = new LoginDTO();
        login.setUserName(clubName);
        login.setPassword("buyPlayer");
        login.setStatus(true);
        login.setList(aa);

        System.out.println(login.getUserName());
        System.out.println(aa);

        main.getNetworkUtil().write(login);
    }

    @FXML
    public void LogOutClub() throws  Exception{
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUserName("closeConnection");
        loginDTO.setPassword(clubName);
        loginDTO.setStatus(true);
        main.getNetworkUtil().write(loginDTO);
        //main.getNetworkUtil().closeConnection();
        main.showLoginPage();
    }

    @FXML
    public void showDetails() throws Exception{
        Player p;
        if(playersTable.isVisible()) {
            p = (Player) playersTable.getSelectionModel().getSelectedItem();
        }else{
            p = (Player) transferTable.getSelectionModel().getSelectedItem();
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("detailsPage.fxml"));
        Parent root = loader.load();

        // Loading the controller
        detailsController controller = loader.getController();
        controller.load(p);

        // Set the primary stage
        stage = new Stage();
        stage.setTitle("detailsPage");
        stage.setScene(new Scene(root, 530, 400));
        stage.show();
    }

    public String getClubName(){
        return clubName;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
    }
}
