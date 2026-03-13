package server;

import common.Message;
import java.io.*;
import java.net.*;
import java.util.*;
import database.DatabaseManager;

public class ClientHandler extends Thread {

    private static Map<String, ClientHandler> userMap = new HashMap<>();

    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private String username;
    private boolean firstMessageReceived;

    // List of all connected clients
    private static List<ClientHandler> clients = new ArrayList<>();

    public ClientHandler(Socket socket) {
        this.socket = socket;

        try {
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());

            clients.add(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            output.writeObject(DatabaseManager.getChatHistory());

            Message msg;

            while ((msg = (Message) input.readObject()) != null) {
                String sender = msg.getSender();
                String receiver = msg.getReceiver();
                String text = msg.getContent();

                username = sender;

                userMap.put(sender, this);

                if (receiver.equals("ALL")) {

                    broadcast(sender + ": " + text);

                } else {

                    sendPrivateMessage(sender, receiver, text);

                }

                DatabaseManager.saveMessage(sender, receiver, text);
                sendUserListToAll();
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Client disconnected");
        }
    }

    private void broadcast(String message) {

        for (ClientHandler client : clients) {
            try {
                client.output.writeObject(message);
                client.output.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private String getUserList() {

        StringBuilder users = new StringBuilder();

        for (ClientHandler client : clients) {
            if (client.username != null) {
                users.append(client.username).append(",");
            }
        }

        return users.toString();
    }

    private void sendUserListToAll() {

        String users = "USERLIST|" + getUserList();

        for (ClientHandler client : clients) {
            try {
                client.output.writeObject(users);
                client.output.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendPrivateMessage(String sender, String receiver, String text) {

        ClientHandler target = userMap.get(receiver);
        ClientHandler source = userMap.get(sender);
        String formattedMessage = "(Private) " + sender + ": " + text;

        if (target != null) {
            try {
                target.output.writeObject(formattedMessage);
                target.output.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (source != null && source != target) {
            try {
                source.output.writeObject(formattedMessage);
                source.output.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
