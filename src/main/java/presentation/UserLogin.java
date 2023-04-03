package presentation;

import bll.Actions;
import dal.Reader;
import start.Start;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * This class creates the appropriate interface for an user when he wants to log in into the app
 * He must introduce its own credentials and press OK
 * If he is a client, a frame designed for a client will be displayed
 * If he is an admin, a frame designed for an admin will be displayed
 * If he is an employee, a frame designed for a regular employee will be displayed
 *
 * @author Szekely Maria-Robertha
 * @see Actions
 * @see JFrame
 */

public class UserLogin extends JFrame {

    private static final long serialVersionUID = 1L;
    public static JTextField userField;
    private JTextField passwordField;
    private JButton ok;

    public UserLogin() {

        Image img = Toolkit.getDefaultToolkit().getImage("src\\main\\resources\\photo.jpg");
        this.setContentPane(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, null);
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setPreferredSize(new Dimension(320, 340));
        //setResizable(false);
        this.getContentPane().setBounds(0, 0, 300, 300);
        this.getContentPane().setLayout(null);
        pack();
        setVisible(true);


        JLabel lblNewLabel = new JLabel("Welcome!");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Impact", Font.PLAIN, 40));
        lblNewLabel.setBounds(70, 10, 273, 30);
        this.getContentPane().add(lblNewLabel);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBackground(Color.BLACK);
        lblUsername.setForeground(Color.WHITE);
        lblUsername.setFont(new Font("Impact", Font.PLAIN, 20));
        lblUsername.setBounds(110, 60, 300, 30);
        this.getContentPane().add(lblUsername);

        userField = new JTextField();
        userField.setFont(new Font("Impact", Font.PLAIN, 20));
        userField.setBounds(100, 100, 100, 30);
        this.getContentPane().add(userField);
        userField.setColumns(10);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setForeground(Color.WHITE);
        lblPassword.setBackground(Color.BLACK);
        lblPassword.setFont(new Font("Impact", Font.PLAIN, 20));
        lblPassword.setBounds(110, 140, 300, 30);
        this.getContentPane().add(lblPassword);

        passwordField = new JTextField();
        passwordField.setFont(new Font("Impact", Font.PLAIN, 20));
        passwordField.setBounds(100, 180, 100, 30);
        this.getContentPane().add(passwordField);

        ok = new JButton("OK");
        ok.setFont(new Font("Impact", Font.PLAIN, 18));
        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Reader reader = new Reader();
                    String read = reader.checkUser(userField.getText(), passwordField.getText());

                    if (read == null) {
                        JOptionPane.showMessageDialog(null, "Incorrect username or password");
                    } else {
                        switch (read) {
                            case "CLIENT":
                                new ClientGUI(userField.getText());
                                break;
                            case "ADMINISTRATOR":
                                new AdministratorGUI();
                                break;
                            case "EMPLOYEE":
                                Start.observer.getFrame().setVisible(true);
                                break;
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        ok.setBounds(115, 230, 70, 20);
        this.getContentPane().add(ok);


        ImageIcon icon = new ImageIcon("src\\main\\resources\\registerr.jpg");
        JButton register = new JButton(icon);
        register.setFont(new Font("Impact", Font.PLAIN, 18));
        register.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Register();
            }
        });
        register.setBounds(100, 260, 100, 20);
        this.getContentPane().add(register);
    }
}
