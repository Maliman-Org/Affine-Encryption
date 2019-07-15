package Models;

//package tpsocket;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 *
 * @author Manno
 */
public class Server {

    public static final String USED_SERVER_IP = "192.168.56.1";
    public InetAddress MY_IP;
    public static final int MY_PORT = 7000;//70007
    public final int MAX_IN_CNX = 10;
    ServerSocket serverSocket = null;
    Socket socket = null;
    DataInputStream msg;
    static AffineEncrypter encrypter = new AffineEncrypter();
    

    public Server() {
        try {
            MY_IP = Inet4Address.getByName(USED_SERVER_IP);
            try {
                serverSocket = new ServerSocket(MY_PORT);
            } catch (IllegalArgumentException argumentException) {
                System.err.println("IllegalArgumentException the chosen port : " + MY_PORT + " is not in the valid rage which is 0 - 65535");
            } catch (SecurityException se) {
                System.err.println("SecurityException the security manager of Server didnt allow the operation");
            } catch (IOException ex) {
                System.err.println("IOException while creating server soket");
            }
        } catch (UnknownHostException ex) {
            System.err.println("UnknownHostException local host coudn't be found ->SERVER_IP");
        }

    }
    
    public void start() {
        
        try {
            System.err.println("the server is waiting for connexions ...");
            socket = serverSocket.accept();
            receiveNote();
        } catch (IOException ex) {
           System.err.println("IOException while waiting for notes");
        }
    }

    public void receiveNote() {
        InputStream inputStream = null;
        try {
            inputStream = socket.getInputStream();
            msg = new DataInputStream(inputStream);
            String stringMsg, cryptedMsg;
            try {
                cryptedMsg = msg.readUTF();
                stringMsg = encrypter.decrypte(cryptedMsg);
                inputStream.close();
                System.out.println("A msg is received from the client( IP= " + socket.getInetAddress().toString() + " & PORT = "
                        + socket.getPort() + " ) with content: " + stringMsg);
                final String note = stringMsg;
               Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Controllers.MainFXMLController.instance.setTheLastNote(note, cryptedMsg);
                    }
                }
                );
               closeSoket();
                start();
            } catch (IOException ex) {
                System.err.println("IOException while receiving the string to the msg");
            }
        } catch (IOException ex) {
            System.err.println("IOException while creating InputStream");
        }

    }

    public void closeDataInputStream() {
        if (msg != null) {
            try {
                msg.close();
                msg = null;
                System.out.println("closeDataInputStream done");
            } catch (IOException ex) {
                System.err.println("IOException while closing the DataInputStream");
            }
        }
    }

    public void closeSoket() {
        try {
           socket.close();
           socket = null;
           System.out.println("close sokets done");

        } catch (IOException ex) {
            System.err.println("IOException while closing the server sockets");
        }

    }

 /*   public static void main(String[] args) {
        new Server().start();
    }*/

}
