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
    public static boolean isUser2;
    public static MainFXMLController instance;
    @FXML
    private AnchorPane parent;

    @FXML
    private Circle avatar_container;

    @FXML
    private JFXTextField noteToSendField;

    @FXML
    private ImageView sendNoteImgVBTN;

    @FXML
    private Label receivedNoteLabel;
    
    @FXML        
    private Label receiverNameLabel;        

    @FXML
    private Label cryptedNoteLabel;
    

    @FXML
    void hideCryptedNote(MouseEvent event) {
        cryptedNoteLabel.setVisible(false);
    }

    @FXML
    void showCryptedNote(MouseEvent event) {
        cryptedNoteLabel.setVisible(true);
    }

    void controle() {
        if (!noteToSendField.getText().isEmpty()) {
            char[] array = noteToSendField.getText().toLowerCase().toCharArray();
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
            noteToSendField.setText(note);
            noteToSendField.positionCaret(note.length());
        }
    }

    @FXML
    void sendMsg(MouseEvent event) {
        if (!noteToSendField.getText().isEmpty()) {
            if (Client.sendAnote(noteToSendField.getText())) {
                noteToSendField.setText("");
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
        instance=this;
        noteToSendField.setOnKeyReleased(e -> {
            controle();
        });
        sendNoteImgVBTN.setOnMouseClicked(e -> {
            {
                if (!noteToSendField.getText().isEmpty()) {
                    if (Client.sendAnote(noteToSendField.getText())) {
                        noteToSendField.setText("");
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
        cryptedNoteLabel.setText(cryptedNote);
        cryptedNoteLabel.setVisible(true);
    }

    public void init() {
        Image image;
        if(isUser2) {
            receiverNameLabel.setText("Malika Madene");
            image = new Image("/UiImages/user2.jpg");
        }else{
            image= new Image("/UiImages/user1.jpg");
        }
        avatar_container.setFill(new ImagePattern(image));
    }

}
