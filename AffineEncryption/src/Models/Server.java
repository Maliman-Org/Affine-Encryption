package Models;

//package tpsocket;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Manno
 */
public class Server {

    public static final String USED_SERVER_IP = "192.168.1.3";
    public InetAddress MY_IP;
    public static final int MY_PORT = 7777;
    public final int MAX_IN_CNX = 10;
    ServerSocket serverSocket = null;
    Socket socket = null;
    DataInputStream msg;
    public static final int senario = 1;

    public Server(int senario) {
        try {
            MY_IP = Inet4Address.getByName(USED_SERVER_IP);
            try {
                serverSocket = new ServerSocket(MY_PORT, MAX_IN_CNX, MY_IP);
                System.err.println("the server is waiting for connexions ...");
                if (senario == 1) {
                    executeStringSenario();
                } else {
                    executeIntSenario();
                }
            } catch (IllegalArgumentException argumentException) {
                System.err.println("IllegalArgumentException the chosen port : "+MY_PORT+" is not in the valid rage which is 0 - 65535");
            } catch (SecurityException se) {
                System.err.println("SecurityException the security manager of Server didnt allow the operation");
            } catch (IOException ex) {
                System.err.println("IOException while creating server soket");
            }
        } catch (UnknownHostException ex) {
            System.err.println("UnknownHostException local host coudn't be found ->SERVER_IP");
        }

    }

    public void receiveString() {
        InputStream inputStream = null;
        try {
            inputStream = socket.getInputStream();
            msg = new DataInputStream(inputStream);
            String stringMsg;
            try {
                stringMsg = msg.readUTF();
                inputStream.close();
                System.out.println("A msg is received from the client( IP= " + socket.getInetAddress().toString() + " & PORT = "
                        + socket.getPort() + " ) with content: " + stringMsg);
                closeSokets();

            } catch (IOException ex) {
                System.err.println("IOException while receiving the string to the msg");
            }
        } catch (IOException ex) {
            System.err.println("IOException while creating InputStream");
        }

    }

    public void receiveInt() {
        InputStream inputStream = null;
        try {
            inputStream = socket.getInputStream();
            msg = new DataInputStream(inputStream);
            int intMsg;
            try {
                intMsg = msg.readInt();
                inputStream.close();
                System.out.println("A msg is received from the client( IP= " + socket.getInetAddress().toString() + " & PORT = "
                        + socket.getPort() + " ) with content: " + intMsg);
                closeSokets();
            } catch (IOException ex) {
                System.err.println("IOException while receiving the int msg");
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

    public void closeSokets() {
        try {
            socket.close();
            serverSocket.close();
            socket = null;
            serverSocket = null;
            System.out.println("close sokets done");
        } catch (IOException ex) {
            System.err.println("IOException while closing the server sockets");
        }

    }

    public void executeStringSenario() {
        try {
            socket = serverSocket.accept();
            receiveString();
        } catch (IOException ex) {
            System.err.println("IOException while creating server soket");
        }
    }

    public void executeIntSenario() {
        try {
            socket = serverSocket.accept();
            receiveInt();
        } catch (IOException ex) {
            System.err.println("IOException while creating server soket");
        }
    }

    public static void main(String[] args) {
        new Server(senario);
    }
}
