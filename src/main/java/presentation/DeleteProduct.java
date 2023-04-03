package presentation;

import bll.Actions;
import bll.MenuItem;
import start.Start;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This class creates the appropriate interface for an admin when he wants to delete an existing product from the menu
 * All products that can be deleted are showed into a JComboBox
 * The admin must choose the item from the JComboBox then press OK
 *
 * @author Szekely Maria-Robertha
 * @see JFrame
 */

public class DeleteProduct extends JFrame {

    private JButton ok;
    private JComboBox<String> products;

    public DeleteProduct() {
        Image img = Toolkit.getDefaultToolkit().getImage("src\\main\\resources\\del.jpg");
        this.setContentPane(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, null);
            }
        });

        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setPreferredSize(new Dimension(600, 300));
        //setResizable(false);
        this.getContentPane().setBounds(0, 0, 600, 300);
        this.getContentPane().setLayout(null);
        setTitle("DELETE PRODUCT");
        pack();

        ArrayList<MenuItem> list = new ArrayList<>(Start.menuitemslist.values());
        String[] smt = {};
        products = new JComboBox<>(smt);
        for (MenuItem menuItem : list) {
            products.addItem(menuItem.getTitle());
        }
        products.setBounds(150, 20, 400, 20);
        this.getContentPane().add(products);


        ok = new JButton("OK");
        ok.setOpaque(false);
        ok.setContentAreaFilled(false);
        ok.setBorderPainted(false);
        ok.setForeground(Color.BLACK);
        ok.setFont(new Font("Impact", Font.PLAIN, 20));
        ok.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Actions actions = new Actions();
                actions.deleteProduct(products);
            }
        });
        ok.setBounds(10, 20, 200, 30);
        this.getContentPane().add(ok);

        setVisible(true);
    }
}
