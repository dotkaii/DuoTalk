package client;

import gui.LoginFrame;
import gui.ChatFrame;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

public class ClientMain {

    public static void main(String[] args) {

        LoginFrame loginFrame = new LoginFrame();

        loginFrame.getConnectButton().addActionListener(e -> {

            String username = loginFrame.getUsername();

            try {

                Socket socket = new Socket("localhost",5000);

                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

                ChatFrame chatFrame = new ChatFrame();

                loginFrame.dispose();

                ClientConnection.startChat(input, output, chatFrame, username);

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });

    }
}
