package presentation;

import bll.Actions;
import dal.CaesarCipher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class creates the appropriate interface for a client when he wants to register into the app
 * The client must introduce a username and a password which will be used in the future to log in into the app
 * The client's username and password will be written into a file in order to use them even after closign the app
 *
 * @author Szekely Maria-Robertha
 * @see Actions
 * @see JFrame
 */

public class Register extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField userField;
    private JTextField passwordField;
    private JButton ok;

    public Register() {
        Image img = Toolkit.getDefaultToolkit().getImage("src\\main\\resources\\welcome.jpg");
        this.setContentPane(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, null);
            }
        });

        setLocationRelativeTo(null);
        setPreferredSize(new Dimension(520, 320));
        this.getContentPane().setBounds(0, 0, 300, 300);
        this.getContentPane().setLayout(null);
        pack();
        setVisible(true);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBackground(Color.BLACK);
        lblUsername.setForeground(Color.WHITE);
        lblUsername.setFont(new Font("Impact", Font.PLAIN, 20));
        lblUsername.setBounds(30, 50, 300, 30);
        this.getContentPane().add(lblUsername);

        userField = new JTextField();
        userField.setFont(new Font("Impact", Font.PLAIN, 20));
        userField.setBounds(30, 90, 100, 30);
        this.getContentPane().add(userField);
        userField.setColumns(10);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setForeground(Color.WHITE);
        lblPassword.setBackground(Color.BLACK);
        lblPassword.setFont(new Font("Impact", Font.PLAIN, 20));
        lblPassword.setBounds(30, 130, 300, 30);
        this.getContentPane().add(lblPassword);

        passwordField = new JTextField();
        passwordField.setFont(new Font("Impact", Font.PLAIN, 20));
        passwordField.setBounds(30, 170, 100, 30);
        this.getContentPane().add(passwordField);

        ok = new JButton("Create account");
        ok.setFont(new Font("Impact", Font.PLAIN, 18));
        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                CaesarCipher caesarCipher = new CaesarCipher();
                String newPass = caesarCipher.cipher(passwordField.getText(), 1);
                JOptionPane.showMessageDialog(null, "Welcome to our delivery app!\nThank you for you register :)");

                try (FileWriter fileWriter = new FileWriter("src/main/resources/UP.txt", true)) {
                    fileWriter.append("\n").append(userField.getText()).append(",").append(newPass).append(",").append("CLIENT");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                dispose();
            }
        });
        ok.setBounds(30, 220, 150, 20);
        this.getContentPane().add(ok);
    }
}
