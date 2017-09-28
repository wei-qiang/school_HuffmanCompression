package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;



public class Controller {
    @FXML
    private Button btnSave;
    @FXML
    private Button btnLoad;
    @FXML
    private TextArea taText;

    public Controller() {
    }

    @FXML
    public void handleButtonActionSave(ActionEvent event){
        TextCompressor compressor = new TextCompressor(taText.getText());
    }

}
