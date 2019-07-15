/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package affineencryption;

import Models.AffineEncrypter;
import Models.Client;
import Models.Server;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Kika
 */
public class AffineEncryptor extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/welcomeScreenFXMLDocument.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            public void run() {
                new Server().start();
            }
        };
        Thread thread=new Thread(runnable);
                thread.start();
        launch(args);

        
    }   

}
