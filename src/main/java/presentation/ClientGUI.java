package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class creates the appropriate interface when a client was logged into the app
 *
 * @author Szekely Maria-Robertha
 * @see JFrame
 */

public class ClientGUI extends JFrame {

    public ClientGUI(String name) {

        Image img = Toolkit.getDefaultToolkit().getImage("src\\main\\resources\\platingcc.jpg");
        this.setContentPane(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, null);
            }
        });

        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setPreferredSize(new Dimension(630, 450));
        //setResizable(false);
        this.getContentPane().setBounds(0, 0, 300, 300);
        this.getContentPane().setLayout(null);
        setTitle(String.valueOf(who.CLIENT));
        pack();

        ImageIcon icon = new ImageIcon("src\\main\\resources\\menu.jpg");

        setVisible(true);
        JButton menu = new JButton("MENU");
        menu.setIcon(icon);
        menu.setFont(new Font("Impact", Font.PLAIN, 18));
        menu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Menu(name);
            }
        });
        menu.setBounds(405, 200, 150, 200);
        this.getContentPane().add(menu);
    }
}
