package client;

import common.Message;
import gui.ChatFrame;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;


public class ClientConnection {

    public static void startChat(ObjectInputStream input,
                                 ObjectOutputStream output,
                                 ChatFrame chatFrame,
                                 String username) {

        chatFrame.getSendButton().addActionListener(e -> {

            String receiver = "ALL";

            String selectedUser = chatFrame.getUserList().getSelectedValue();

            if(selectedUser != null){
                receiver = selectedUser;
            }

            String message = chatFrame.getMessageField().getText();

            Message msg = new Message(
                    "MESSAGE",
                    username,
                    receiver,
                    message
            );

            try {
                output.writeObject(msg);
                output.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            chatFrame.getMessageField().setText("");

        });


        new Thread(() -> {

            try {

                String message;

                while((message = (String) input.readObject()) != null){

                    if(message.startsWith("USERLIST|")){

                        String users = message.substring(9);
                        String[] userArray = users.split(",");

                        chatFrame.getUserList().setListData(userArray);

                    }
                    else{

                        chatFrame.getChatArea().append(message + "\n");

                    }

                }

            } catch(IOException | ClassNotFoundException e){
                e.printStackTrace();
            }

        }).start();
    }
}
