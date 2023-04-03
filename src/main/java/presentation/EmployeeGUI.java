package presentation;

import bll.MenuItem;
import bll.Order;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * This class creates the appropriate interface when a regular employee was logged into the app
 *
 * @author Szekely Maria-Robertha
 * @see JFrame
 */


public class EmployeeGUI implements Observer {

    public static HashMap<Order, List<MenuItem>> map;
    private JFrame frame;

    public EmployeeGUI() {
        frame = new JFrame();
        map = new HashMap<>();
        Image img = Toolkit.getDefaultToolkit().getImage("src\\main\\resources\\empl.jpg");
        frame.setContentPane(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, null);
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setPreferredSize(new Dimension(620, 400));
        frame.getContentPane().setBounds(0, 0, 300, 300);
        frame.getContentPane().setLayout(null);
        frame.setTitle(String.valueOf(who.EMPLOYEE));
        frame.pack();

        ImageIcon icon = new ImageIcon("src\\main\\resources\\notif.png");
        JButton notif = new JButton(icon);
        notif.setBackground(Color.WHITE);
        notif.setBorderPainted(false);
        notif.setIcon(icon);
        notif.setFont(new Font("Impact", Font.PLAIN, 18));
        notif.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!map.isEmpty()) {
                    for (Map.Entry<Order, List<MenuItem>> entry : map.entrySet()) {
                        System.out.println(entry.getKey().hashCode() + " " + entry.getValue());
                    }
                    System.out.println();
                    new SOrder();
                } else {
                    JOptionPane.showMessageDialog(null, "There is no order");
                }

            }
        });
        notif.setBounds(0, 0, 40, 40);
        frame.getContentPane().add(notif);
    }

    public void setOrder(HashMap<Order, List<MenuItem>> map) {
        this.map = map;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void update(Observable o, Object map) {
        this.setOrder((HashMap<Order, List<MenuItem>>) map);
    }
}
