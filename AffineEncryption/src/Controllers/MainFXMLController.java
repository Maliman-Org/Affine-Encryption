/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Client;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kika
 */
public class MainFXMLController implements Initializable {
    public static final String c="2";
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
    private Label receiverNameLabell;        

    @FXML
    private Label cryptedNoteLabe;

    @FXML
    void hideCryptedNote(MouseEvent event) {

    }

    @FXML
    void showCryptedNote(MouseEvent event) {

    }

    void controle() {
        if (!noteToSendTextField.getText().isEmpty()) {
            char[] array = noteToSendTextField.getText().toLowerCase().toCharArray();
            String note = "";
            for (char c : array) {
                if (c != ' ') {
                    if (Models.AffineEncrypter.alphabet.contains(c)) {
                        note += c;
                    }
                } else {
                    note += c;
                }
            }
            noteToSendTextField.setText(note);
            noteToSendTextField.positionCaret(note.length());
        }
    }

    @FXML
    void sendMsg(MouseEvent event) {
        if (!noteToSendTextField.getText().isEmpty()) {
            if (Client.sendAnote(noteToSendTextField.getText())) {
                noteToSendTextField.setText("");
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/Views/successSendFXML.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("note not sent");
            }
        } else {
            System.out.println("no note");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        instance = this;
        noteToSendTextField.setOnKeyReleased(e -> {
            controle();
        });
        sendNoteImgV.setOnMouseClicked(e -> {
            {
                if (!noteToSendTextField.getText().isEmpty()) {

                    if (Client.sendAnote(noteToSendTextField.getText())) {
                        try {
                            Parent root = FXMLLoader.load(getClass().getResource("/Views/successSendFXML.fxml"));
                            Scene scene = new Scene(root);
                            Stage stage = new Stage();
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        System.out.println("note not sent");
                    }
                } else {
                    System.out.println("no note");
                }
            }
        });
    }

    public void setTheLastNote(String note, String cryptedNote) {
        receivedNoteLabel.setText(note);
        cryptedNoteLabe.setText(cryptedNote);
        cryptedNoteLabe.setVisible(true);
    }

    public void init() {
        if(c.equals("2")) receiverNameLabell.setText("Malika Madene");
        Image image = new Image("http://localhost/Affine/user"+c+".jpg");
        admin_avatar_container.setFill(new ImagePattern(image));
    }

}
