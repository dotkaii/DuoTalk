package gui;

import javax.swing.*;
import java.awt.*;

public class ChatFrame extends JFrame {

    private JTextArea chatArea;
    private JTextField messageField;
    private JButton sendButton;
    private JList<String> userList;

    public ChatFrame() {

        setTitle("DuoTalk Chat");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);

        JScrollPane chatScroll = new JScrollPane(chatArea);

        userList = new JList<>();
        JScrollPane userScroll = new JScrollPane(userList);

        userScroll.setPreferredSize(new Dimension(120, 0));

        mainPanel.add(chatScroll, BorderLayout.CENTER);
        mainPanel.add(userScroll, BorderLayout.EAST);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        messageField = new JTextField();
        sendButton = new JButton("Send");

        messageField.addActionListener(e -> sendButton.doClick());
        
        bottomPanel.add(messageField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);

        add(mainPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        setVisible(true);

    }

    public JTextArea getChatArea() {
        return chatArea;
    }

    public JTextField getMessageField() {
        return messageField;
    }

    public JButton getSendButton() {
        return sendButton;
    }

    public JList<String> getUserList() {
        return userList;
    }
}
