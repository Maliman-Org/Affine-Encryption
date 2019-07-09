package Models;

//package tpsocket;
import java.io.*;
import java.net.*;

/**
 *
 * @author Manno
 */
public class Client {

    public static String USED_CLIENT_IP = "192.168.1.3";
    public static final String USED_SERVER_IP = "192.168.1.3";
    public static InetAddress MY_IP;
    public final int MY_PORT = 7779;
    public InetAddress SERVER_IP;
    public static final int SERVER_PORT = 7777;
    Socket socket = null;
    DataOutputStream msg = null;
    static AffineEncrypter encrypter = new AffineEncrypter();
    private static Client instance = new Client();

    public Client() {
        try {
            MY_IP = Inet4Address.getByName(USED_CLIENT_IP);
            try {
                SERVER_IP = Inet4Address.getByName(USED_SERVER_IP);
                try {
                    socket = new Socket(SERVER_IP, SERVER_PORT, MY_IP, MY_PORT);
                    System.out.println("Client Soket is successfully created");
                } catch (IllegalArgumentException argumentException) {
                    System.err.println("IllegalArgumentException the port is not in the valid rage which is 0 - 65535");
                } catch (BindException bindException) {
                    System.err.println("BindException coudn't assign socket to the local port " + MY_PORT + ", it must be already used by other apps");
                } catch (ConnectException connectException) {
                    System.err.println("ConnectException coudn't connect to the remote host or port because (the server doesn't provide a service on the assignd port )\n"
                            + " or ( the sever has already attended the max in cnx )");
                } catch (NoRouteToHostException noRouteToHostException) {
                    System.err.println("NoRouteToHostException there is a network error : couldn't find the remote host,\n  please check your connection, firewalls, routers configurations and retry");
                } catch (InterruptedIOException interruptedIOException) {
                    System.err.println("InterruptedIOException cnx time out");
                } catch (IOException ex) {
                    System.err.println("IOException while creating client soket");
                    ex.printStackTrace();
                }
            } catch (UnknownHostException hostServerException) {
                System.err.println("UnknownHostException Server host " + USED_SERVER_IP + " coudn't be found ->SERVER_IP");
            } catch (SecurityException se) {
                System.err.println("SecurityException the security manager of Server didnt allow the operation ->SERVER_IP");
            }
        } catch (UnknownHostException hostException) {
            System.err.println("UnknownHostException local host coudn't be found ->MY_IP");
        }
    }

    public void send(String stringMsg) {
        if (socket != null) {
            OutputStream output = null;
            try {
                output = socket.getOutputStream();
                msg = new DataOutputStream(output);
                try {
                    msg.writeUTF(stringMsg);
                    try {
                        msg.flush();
                        System.out.println("msg: " + stringMsg + " is send");
                        closeDataOutputStream();
                    } catch (IOException ex) {
                        System.err.println("IOException while sending the msg");
                    }
                } catch (IOException ex) {
                    System.err.println("IOException while assigning the string to the msg");
                }
            } catch (IOException ex) {
                System.err.println("IOException while creating OutputStream");
            }

        } else {
            System.err.println("soket is null i can't send the string msg");
        }
    }

    public void closeDataOutputStream() {
        if (msg != null) {
            try {
                msg.close();
                msg = null;
                System.out.println("closeDataOutputStream done");
            } catch (IOException ex) {
                System.err.println("IOException while closing the DataOutputStream");
            }
        }
    }

    public void closeSoket() {
        if (socket != null) {
            try {
                socket.close();
                socket = null;
                System.out.println("close soket done");
            } catch (IOException ex) {
                System.err.println("IOException while closing the client socket");
            }

        }
    }

    public static void sendAnote(String note) {
        instance.send(encrypter.encrypte(note));
    }

    public static void close() {
        instance.closeSoket();
        instance = null;
    }

}
