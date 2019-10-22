/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package affineencryption;

import Controllers.MainFXMLController;
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
        boolean user1 = true;
        boolean local = true;
        if (user1) {
            if (local) {
                Client.USED_CLIENT_IP="192.168.56.1";
                Client.USED_SERVER_IP=Client.USED_CLIENT_IP;
                Client.SERVER_PORT=7000;
                Server.USED_SERVER_IP=Client.USED_CLIENT_IP;
                Server.MY_PORT=7007;
            }else{
                Client.USED_CLIENT_IP="192.168.43.181";
                Client.USED_SERVER_IP="192.168.43.90";
                Client.SERVER_PORT=7000;
                Server.USED_SERVER_IP=Client.USED_CLIENT_IP;
                Server.MY_PORT=7007;
            }
        } else {  
            if (local) {
                Client.USED_CLIENT_IP="192.168.56.1";
                Client.USED_SERVER_IP=Client.USED_CLIENT_IP;
                Client.SERVER_PORT=7007;
                Server.USED_SERVER_IP=Client.USED_CLIENT_IP;
                Server.MY_PORT=7000;
            }else{
                Client.USED_CLIENT_IP="192.168.43.90";
                Client.USED_SERVER_IP="192.168.43.181";
                Client.SERVER_PORT=7007;
                Server.USED_SERVER_IP=Client.USED_CLIENT_IP;
                Server.MY_PORT=7000;                
            }
        }
        MainFXMLController.isUser2=!user1;
        Runnable runnable = new Runnable() {
            public void run() {
                new Server().start();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

        launch(args);

    }

}
