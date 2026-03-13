package gui;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JButton connectButton;

    public LoginFrame() {

        setTitle("DuoTalk Login");
        setSize(300,150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Enter Username:");
        label.setHorizontalAlignment(SwingConstants.CENTER);

        usernameField = new JTextField();

        connectButton = new JButton("Connect");

        add(label, BorderLayout.NORTH);
        add(usernameField, BorderLayout.CENTER);
        add(connectButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public JButton getConnectButton() {
        return connectButton;
    }
}