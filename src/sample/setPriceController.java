package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class setPriceController {
    public clubNewPageController clubNewPageController;

    @FXML
    AnchorPane pane;

    @FXML
    Button confirmButton;

    @FXML
    TextField sellPrice;

    @FXML
    public void sell() throws Exception{
        clubNewPageController.getPrice(sellPrice.getText());
        clubNewPageController.sellPlayer2();
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.close();
    }

    public void setClubController(clubNewPageController clubNewPageController){this.clubNewPageController = clubNewPageController;}
}
