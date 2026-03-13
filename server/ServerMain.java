package server;

import java.io.*;
import java.net.*;
import database.DatabaseManager;

public class ServerMain {

    public static void main(String[] args) {
        int port = 5000;
        DatabaseManager.initializeDatabase();
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                new ClientHandler(socket).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}