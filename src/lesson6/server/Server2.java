package lesson6.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server2 {
    ServerSocket server = null;
    Socket socket = null;
    DataInputStream in = null;
    DataOutputStream out = null;
    Scanner sc = null;

    public Server2(){
        try {
            server = new ServerSocket(8189);
            System.out.println("Server starts!");
            socket = server.accept();
            System.out.println("Client is connected!");

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            sc = new Scanner(System.in);

            Thread t1= new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String str = in.readUTF();
                            System.out.println("Message from Client: "+str);
                            if (str.equals("123")) {
                                System.out.println("Client demanded to Stop connection, please confirme (123)");
                                break;
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            Thread t2=new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String str2 = sc.nextLine();
                            out.writeUTF(str2);
                            if (str2.equals("123")) {
                                System.out.println("Connection's closing. Waiting for client confirmation");
                                break;
                            }
                        }
                    } catch (IOException e) {
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
