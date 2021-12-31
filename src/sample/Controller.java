package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class Controller extends Control {

    @FXML
    TextField p1Name;
    @FXML
    TextField p2Name;

    public String getP1Name() {
        return p1Name.getText();
    }

    public String getP2Name() {
        return p2Name.getText();
    }

    @FXML
    public void switchToGame(ActionEvent event) {
//        if(p1Name.getText().equals("") || p2Name.getText().equals("")){
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            if(p1Name.getText().equals("") && !p2Name.getText().equals(""))
//                alert.setContentText("Enter player 1 name");
//            else if(!p1Name.getText().equals("") && p2Name.getText().equals(""))
//                alert.setContentText("Enter player 2 name");
//            else
//                alert.setContentText("Enter both player names");
//
//            alert.show();
//        } else{
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("gameScene.fxml"));
                Parent root = loader.load();
                Board board = loader.getController();
                board.setNames(getP1Name(), getP2Name());
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
       // }


    }
}
