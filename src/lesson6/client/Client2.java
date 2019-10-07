package lesson6.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client2 {
    private final String ADDRESS = "localhost";
    private final int PORT = 8189;

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Scanner sc;

    public Client2() {
        System.out.println("Client starts");
        try {
            socket = new Socket(ADDRESS, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            sc = new Scanner(System.in);

            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String strScanner = sc.nextLine();
                            out.writeUTF(strScanner);
                            if (strScanner.equals("123")) {
                                System.out.println("We're trying to Stop! Wait for server confirmation");
                                break;
                            }
                        }
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });

            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String strServ = in.readUTF();
                            System.out.println("Message from server: " + strServ);
                            if (strServ.equals("123")) {
                                System.out.println("Server demanded to close connection! Pleae confirme (123)");
                                break;
                            }
                        }
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });

            t1.start();
            t2.start();

            try {
                t1.join();
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    private void closeConnection(){
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Session ended");
    }
}
