/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author Kika
 */
public class MainFXMLController implements Initializable {

    public static MainFXMLController instance = null;
    @FXML
    private AnchorPane parent;

    @FXML
    private Circle admin_avatar_container;

    @FXML
    private JFXTextField noteToSendTextField;

    @FXML
    private ImageView sendNoteImgV;

    @FXML
    private Label receivedNoteLabel;

    @FXML
    private Label cryptedNoteLabe;

    @FXML
     void hideCryptedNote(MouseEvent event) {

    }

    @FXML
     void sendMsg(MouseEvent event) {

    }

    @FXML
    void showCryptedNote(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setTheLastNote(String note) {

    }

}
