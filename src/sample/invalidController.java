package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class invalidController {
    private Main main;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label Label;

    @FXML
    private Button closeButton;

    @FXML
    private Button yesButton;

    public void load(String s){
        System.out.println(s);
        yesButton.setVisible(false);
        if(s.equals("Already logged in")){
            s = "           " + s;
            Label.setText(s);
        }
        else if(s.equals("Are You Sure to Exit?")){
            s = "      " + s;
            Label.setText(s);
            yesButton.setVisible(true);
        }
    }

    @FXML
    public void prevPage() throws Exception{
        closePage();
        main.closeStage();
    }

    @FXML
    public void closePage(){
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.close();
    }

    public void setMain(Main main) { this.main = main; }
}
