package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class detailsController {
    @FXML
    private ImageView playerImage;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label playerInfo;

    @FXML
    private Button closeButton;

    @FXML
    public void closePage(){
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.close();
    }

    public void load(Player p){
        String t = p.toString();
        playerInfo.setText(t);
        String s = p.getName() + ".png";
        Image img = new Image(Main.class.getResourceAsStream(s));
        playerImage.setImage(img);
    }
}
